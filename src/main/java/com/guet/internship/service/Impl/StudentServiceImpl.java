package com.guet.internship.service.Impl;


import com.github.pagehelper.PageHelper;
import com.guet.internship.common.utils.JwtTokenUtil;
import com.guet.internship.common.utils.StringsUtils;
import com.guet.internship.condition.*;
import com.guet.internship.dto.CommonUserDetails;
import com.guet.internship.mbg.mapper.*;
import com.guet.internship.mbg.model.*;
import com.guet.internship.mbg.model.Class;
import com.guet.internship.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 欲隐君。 on 2020/8/24
 */
@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private InternshipMapper internshipMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private HealthyReportMapper healthyReportMapper;

    @Autowired
    private SignMapper signMapper;

    @Autowired
    private AcceptMapper acceptMapper;

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private AdminMapper adminMapper;

//    @Autowired
//    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;



    @Autowired
    @Qualifier("studentDetailsService")
    private UserDetailsService studentDetailsService;
    @Autowired
    @Qualifier("adminDetailsService")
    private UserDetailsService adminDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Integer updatePersonal(String account, Student student) {
        student.setAccount(account);
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public Student getStudentByUsername(String username) {
        StudentExample example = new StudentExample();
        example.createCriteria().andNameEqualTo(username);

        List<Student> studentList = studentMapper.selectByExample(example);

        if (studentList != null && studentList.size() > 0){
            return  studentList.get(0);
        }
        return null;
    }

    @Override
    public Student getStudentByAccount(String account) {
        return studentMapper.selectByPrimaryKey(account);
    }

    @Override
    public String login(String account, String password, String userType) {
        String token = null;
        UserDetails userDetails = null;
        try {
            if (userType.equals(CommonUserDetails.STUDENT_CLASS_TYPE_CODE) || userType.equals(CommonUserDetails.STUDENT_INTERNSHIP_TYPE_CODE)){
                Student student = studentMapper.selectByPrimaryKey(account);
                userDetails = studentDetailsService.loadUserByUsername(student.getName());
            } else if(userType.equals(CommonUserDetails.ADMIN_TYPE_CODE)) {
                Admin admin = adminMapper.selectByPrimaryKey(account);
                userDetails = adminDetailsService.loadUserByUsername(admin.getName());
            }

            //这里暂时不对密码进行加密校验
//            if (!passwordEncoder.matches(password,userDetails.getPassword())){
//                throw new BadCredentialsException("密码错误！！！");
//            }

            if(!password.equals(userDetails.getPassword())){
                throw new BadCredentialsException("密码错误！！！");
            }

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        }catch (AuthenticationException e){

            LOGGER.warn("登录异常:{}",e.getMessage());
        }
        return token;
    }

    @Override
    public List<Class> selectClasses(ClassCondition condition, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        ClassExample example = new ClassExample();
        ClassExample.Criteria criteria = example.createCriteria();
        if (condition.getId() != null && condition.getId() != 0){
            criteria.andIdEqualTo(condition.getId());
        }
        if (StringsUtils.isNotEmpty(condition.getClassName())){
            criteria.andClassNameLike("%" + condition.getClassName() + "%");
        }
        if (StringsUtils.isNotEmpty(condition.getGrade())){
            criteria.andGradeEqualTo(condition.getGrade());
        }
        if (StringsUtils.isNotEmpty(condition.getMajor())){
            criteria.andMajorLike("%" + condition.getMajor() + "%");
        }
        if (StringsUtils.isNotEmpty(condition.getDescription())){
            criteria.andDescriptionLike("%" + condition.getDescription() + "%");
        }
        if (condition.getCount() != null && condition.getCount() != 0){
            criteria.andCountEqualTo(condition.getCount());
        }
        if (StringsUtils.isNotEmpty(condition.getAdminId())){
            criteria.andAdminIdEqualTo(condition.getAdminId());
        }
        return classMapper.selectByExample(example);
    }

    @Override
    public List<Internship> selectInternship(InternshipCondition condition, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        InternshipExample example = new InternshipExample();
        InternshipExample.Criteria criteria = example.createCriteria();
        if (condition.getId() != null && condition.getId() != 0){
            criteria.andIdEqualTo(condition.getId());
        }
        if (StringsUtils.isNotEmpty(condition.getGrade())){
            criteria.andGradeEqualTo(condition.getGrade());
        }
        if (condition.getCount() != null && condition.getCount() != 0){
            criteria.andCountEqualTo(condition.getCount());
        }
        if (StringsUtils.isNotEmpty(condition.getAdminId())){
            criteria.andAdminIdEqualTo(condition.getAdminId());
        }

        return internshipMapper.selectByExample(example);
    }

    @Override
    public int updateStudent(String account, Student student) {
        student.setAccount(account);
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public List<Document> selectDocument(DocumentCondition condition, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        DocumentExample example = new DocumentExample();
        DocumentExample.Criteria criteria = example.createCriteria();
        if (condition.getId() != null && condition.getId() != 0){
            criteria.andIdEqualTo(condition.getId());
        }
        if (StringsUtils.isNotEmpty(condition.getTitle())){
            criteria.andTitleLike("%" + condition.getTitle() + "%");
        }
        if (StringsUtils.isNotEmpty(condition.getDescription())){
            criteria.andDescriptionLike("%" + condition.getDescription() + "%");
        }
        if (StringsUtils.isNotEmpty(condition.getDocumentType())){
            criteria.andDocumentTypeEqualTo(condition.getDocumentType());
        }
        if (condition.getDocumentCategory() != null && condition.getDocumentCategory() != 0){
            criteria.andDocumentCategoryEqualTo(condition.getDocumentCategory());
        }
        if (null != condition.getUploadTime()){
            criteria.andUploadTimeEqualTo(condition.getUploadTime());
        }
        if (null != condition.getUpdateTime()){
            criteria.andUpdateTimeEqualTo(condition.getUpdateTime());
        }
        if (StringsUtils.isNotEmpty(condition.getPath())){
            criteria.andPathEqualTo(condition.getPath());
        }
        if (condition.getStatus() != null && condition.getStatus() != 0){
            criteria.andStatusEqualTo(condition.getStatus());
        }
        if (StringsUtils.isNotEmpty(condition.getStudentId())){
            criteria.andStudentIdEqualTo(condition.getStudentId());
        }
        if (StringsUtils.isNotEmpty(condition.getAdminId())){
            criteria.andAdminIdEqualTo(condition.getAdminId());
        }
        example.setOrderByClause("update_time DESC");
        return documentMapper.selectByExample(example);
    }

    @Override
    public int insertHealthyReport(HealthyReport healthyReport) {
        Student student = getStudentByAccount(healthyReport.getStudentId());
        healthyReport.setAdminId(student.getAdminId());
        healthyReport.setReportDate(new Date());
        return healthyReportMapper.insertSelective(healthyReport);
    }

    @Override
    public int insertSign(String account) {
        Sign sign = new Sign();
        Student student = getStudentByAccount(account);
        sign.setSignIn(new Date());
        sign.setAdminId(student.getAdminId());
        sign.setStudentId(account);
        return signMapper.insertSelective(sign);
    }

    @Override
    public List<Accept> selectAccepts(AcceptCondition condition, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        AcceptExample example = new AcceptExample();
        AcceptExample.Criteria criteria = example.createCriteria();
        if (condition.getId() != null && condition.getId() != 0){
            criteria.andIdEqualTo(condition.getId());
        }
        if (condition.getNoticeId() != null && condition.getNoticeId() != 0){
            criteria.andNoticeIdEqualTo(condition.getNoticeId());
        }
        if (condition.getStatus() != null){
            criteria.andStatusEqualTo(condition.getStatus());
        }
        if (StringsUtils.isNotEmpty(condition.getStudentId())){
            criteria.andStudentIdEqualTo(condition.getStudentId());
        }
        if (null != condition.getCreatedAt()){
            criteria.andCreatedAtEqualTo(condition.getCreatedAt());
        }
        if (null != condition.getReadAt()){
            criteria.andReadAtEqualTo(condition.getReadAt());
        }
        return acceptMapper.selectByExample(example);
    }

    @Override
    public Notice selectNotice(Integer noticeId) {
        return noticeMapper.selectByPrimaryKey(noticeId);
    }

    @Override
    public List<Notice> selectNotices(NoticeCondition condition) {
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();
        if (condition.getId() != null && condition.getId() != 0){
            criteria.andIdEqualTo(condition.getId());
        }
        if (StringsUtils.isNotEmpty(condition.getTitle())){
            criteria.andTitleLike("%" + condition.getTitle() + "%");
        }
        if (StringsUtils.isNotEmpty(condition.getContent())){
            criteria.andContentLike("%" + condition.getContent() + "%");
        }
        if (StringsUtils.isNotEmpty(condition.getImage())){
            criteria.andImageEqualTo(condition.getImage());
        }
        if (condition.getType() != null && condition.getType() != 0){
            criteria.andTypeEqualTo(condition.getType());
        }
        if (null != condition.getSendDate()){
            criteria.andSendDateEqualTo(condition.getSendDate());
        }
        if (StringsUtils.isNotEmpty(condition.getAdminId())){
            criteria.andAdminIdEqualTo(condition.getAdminId());
        }
        if (condition.getClassId() != null && condition.getClassId() != 0){
            criteria.andClassIdEqualTo(condition.getClassId());
        }
        return noticeMapper.selectByExample(example);

    }

    @Override
    public int updateAccept(Integer id,Accept accept) {
        accept.setId(id);
        return acceptMapper.updateByPrimaryKeySelective(accept);
    }


    @Override
    public HealthyReport selectHealthyData(String account) {
        HealthyReportExample example = new HealthyReportExample();
        example.createCriteria().andStudentIdEqualTo(account);
        example.setOrderByClause("report_date DESC");
        List<HealthyReport> healthyReports = healthyReportMapper.selectByExample(example);

        if (healthyReports!=null && healthyReports.size() >0){
            return healthyReports.get(0);
        }
        return null;
    }

    @Override
    public int uploadFile(MultipartFile file, Document document) {

        Student student = getStudentByAccount(document.getStudentId());

        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
        String filePath = "/root/internship_manage/uploadFile/test/";
        if (document.getDocumentCategory() == 1){
            filePath = filePath + "Internship_Application/" + student.getAccount() + "/";
        }
        if (document.getDocumentCategory() == 2){
            filePath = filePath + "Internship_Assessment/" + student.getAccount() + "/";
        }
        if (document.getDocumentCategory() == 3){
            filePath = filePath + "Internship_Report/" + student.getAccount() + "/";
        }

        final String FILE_NAME = student.getAccount() + "_" + UUID.randomUUID().toString()+ fileName.substring(fileName.lastIndexOf("."));

        File dest = new File(filePath+FILE_NAME);

        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();   //新建文件夹
        }

        document.setDocumentType(fileType);
        document.setUploadTime(new Date());
        document.setUpdateTime(new Date());
        document.setPath(filePath+FILE_NAME);
        document.setStatus(1);
        document.setAdminId(student.getAdminId());

        documentMapper.insertSelective(document);
        try {
            file.transferTo(dest);
            LOGGER.debug("文件上传成功：{}",dest);
            return 1;
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return 0;
    }
}
