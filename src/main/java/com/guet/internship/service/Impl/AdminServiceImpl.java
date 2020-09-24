package com.guet.internship.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guet.internship.common.utils.JwtTokenUtil;
import com.guet.internship.common.utils.StringsUtils;
import com.guet.internship.condition.*;
import com.guet.internship.dao.DocumentDao;
import com.guet.internship.mbg.mapper.*;
import com.guet.internship.mbg.model.*;
import com.guet.internship.mbg.model.Class;
import com.guet.internship.service.AdminService;
//import org.jodconverter.DocumentConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 欲隐君。 on 2020/8/20
 */
@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private DocumentDao documentDao;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private InternshipMapper internshipMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private HealthyReportMapper healthyReportMapper;

    @Autowired
    private SignMapper signMapper;

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private AcceptMapper acceptMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 第一步：转换器直接注入
//    @Autowired
//    private DocumentConverter converter;
//
//    @Autowired
//    private HttpServletResponse response;


    @Override
    public Admin getAdminByUsername(String username) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andNameEqualTo(username);

        List<Admin> adminList = adminMapper.selectByExample(adminExample);

        if (adminList != null && adminList.size() > 0){
            return  adminList.get(0);
        }
        return null;
    }

    @Override
    public Admin getAdminByAccount(String account) {
        return adminMapper.selectByPrimaryKey(account);
    }

    @Override
    public Admin login(String account, String password) {
        Admin admin = adminMapper.selectByPrimaryKey(account);
        if (admin == null){
            return null;
        }

        if(!password.equals(admin.getPassword())){
            return null;
        }

        return admin;
    }

    @Override
    public Integer updatePersonal(String account, Admin admin) {
        admin.setAccount(account);
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public Integer createStudent(Student student) {
        return studentMapper.insertSelective(student);
    }

    @Override
    public Integer createInternship(Internship internship) {
        return internshipMapper.insertSelective(internship);
    }

    @Override
    public Integer createClass(Class classes) {
        return classMapper.insertSelective(classes);
    }

    @Override
    public List<Class> selectClass(ClassCondition condition, Integer pageNum, Integer pageSize) {
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
    public Integer deleteClass(Integer id) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        if (id != null && id != 0){
            criteria.andClassIdEqualTo(id);
        }
        Student student = new Student();
        student.setClassId(0);
        studentMapper.updateByExampleSelective(student,example);   //将一行中某几个属性更新,而不改变其他的值,而updateByExample将整条数据都更新,如果对象中没有值的属性,就自动设置为null.
        return classMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteInternship(Integer id) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        if (id != null && id != 0){
            criteria.andInternshipIdEqualTo(id);
        }
        Student student = new Student();
        student.setInternshipId(0);
        studentMapper.updateByExampleSelective(student,example);
        return internshipMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Sign> selectSign(SignCondition condition, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        SignExample example = new SignExample();
        SignExample.Criteria criteria = example.createCriteria();
        if (condition.getId() != null && condition.getId() != 0){
            criteria.andIdEqualTo(condition.getId());
        }
        if(null != condition.getSignIn()) {
            criteria.andSignInEqualTo(condition.getSignIn());
        }
        if (StringsUtils.isNotEmpty(condition.getStudentId())){
            criteria.andStudentIdEqualTo(condition.getStudentId());
        }
        if (StringsUtils.isNotEmpty(condition.getAdminId())){
            criteria.andAdminIdEqualTo(condition.getAdminId());
        }
        example.setOrderByClause("sign_in DESC");
        return signMapper.selectByExample(example);
    }

    @Override
    public List<Student> selectStudents(StudentCondition condition, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        if(StringsUtils.isNotEmpty(condition.getAccount())){
            criteria.andAccountEqualTo(condition.getAccount());
        }
        if(StringsUtils.isNotEmpty(condition.getName())){
            criteria.andNameLike("%" + condition.getName() + "%");
        }
        if(StringsUtils.isNotEmpty(condition.getSex())){
            criteria.andSexEqualTo(condition.getSex());
        }
        if(StringsUtils.isNotEmpty(condition.getIdentify())){
            criteria.andIdentifyLike("%" + condition.getIdentify() + "%");
        }
        if(StringsUtils.isNotEmpty(condition.getPhone())){
            criteria.andPhoneLike("%" + condition.getPhone() + "%");
        }
        if(StringsUtils.isNotEmpty(condition.getMailbox())){
            criteria.andMailboxLike("%" + condition.getMailbox() + "%");
        }
        if(StringsUtils.isNotEmpty(condition.getLocation())){
            criteria.andLocationLike("%" + condition.getLocation() + "%");
        }
        if(StringsUtils.isNotEmpty(condition.getMajor())){
            criteria.andMajorLike("%" + condition.getMajor() + "%");
        }
        if(StringsUtils.isNotEmpty(condition.getGrade())){
            criteria.andGradeEqualTo(condition.getGrade());
        }
        if(StringsUtils.isNotEmpty(condition.getClassName())){
            criteria.andClassNameLike("%" + condition.getClassName() + "%");
        }
        if(StringsUtils.isNotEmpty(condition.getAdminId())){
            criteria.andAdminIdEqualTo(condition.getAdminId());
        }
        if (condition.getScore() != null){
            criteria.andScoreEqualTo(condition.getScore());
        }
        if (condition.getInternshipId() != null && condition.getInternshipId() != 0){
            criteria.andInternshipIdEqualTo(condition.getInternshipId());
        }
        if (condition.getClassId() != null && condition.getClassId() != 0){
            criteria.andClassIdEqualTo(condition.getClassId());
        }
        return studentMapper.selectByExample(example);
    }

    @Override
    public Integer deleteStudent(String account) {
        return studentMapper.deleteByPrimaryKey(account);
    }

    @Override
    public List<Document> selectDocumentsByStudentId(String studentId) {
        return documentDao.selectDocumentsByStudentId(studentId);
    }

    @Override
    public Integer createNotice(Notice notice) {
        return noticeMapper.insertSelective(notice);
    }

    @Override
    public Integer createAccept(Accept accept) {
        return acceptMapper.insertSelective(accept);
    }

    @Override
    public Integer updateClass(Integer id, Class classes) {
        classes.setId(id);
        return classMapper.updateByPrimaryKeySelective(classes);
    }

    @Override
    public Integer updateInternship(Integer id, Internship internship) {
        internship.setId(id);
        return internshipMapper.updateByPrimaryKeySelective(internship);
    }

    @Override
    public List<Student> selectStudents(StudentCondition condition) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        if(StringsUtils.isNotEmpty(condition.getAccount())){
            criteria.andAccountEqualTo(condition.getAccount());
        }
        if(StringsUtils.isNotEmpty(condition.getName())){
            criteria.andNameLike("%" + condition.getName() + "%");
        }
        if(StringsUtils.isNotEmpty(condition.getSex())){
            criteria.andSexEqualTo(condition.getSex());
        }
        if(StringsUtils.isNotEmpty(condition.getIdentify())){
            criteria.andIdentifyLike("%" + condition.getIdentify() + "%");
        }
        if(StringsUtils.isNotEmpty(condition.getPhone())){
            criteria.andPhoneLike("%" + condition.getPhone() + "%");
        }
        if(StringsUtils.isNotEmpty(condition.getMailbox())){
            criteria.andMailboxLike("%" + condition.getMailbox() + "%");
        }
        if(StringsUtils.isNotEmpty(condition.getLocation())){
            criteria.andLocationLike("%" + condition.getLocation() + "%");
        }
        if(StringsUtils.isNotEmpty(condition.getMajor())){
            criteria.andMajorLike("%" + condition.getMajor() + "%");
        }
        if(StringsUtils.isNotEmpty(condition.getGrade())){
            criteria.andGradeEqualTo(condition.getGrade());
        }
        if(StringsUtils.isNotEmpty(condition.getClassName())){
            criteria.andClassNameLike("%" + condition.getClassName() + "%");
        }
        if(StringsUtils.isNotEmpty(condition.getAdminId())){
            criteria.andAdminIdEqualTo(condition.getAdminId());
        }
        if (condition.getScore() != null){
            criteria.andScoreEqualTo(condition.getScore());
        }
        if (condition.getInternshipId() != null && condition.getInternshipId() != 0){
            criteria.andInternshipIdEqualTo(condition.getInternshipId());
        }
        if (condition.getClassId() != null && condition.getClassId() != 0){
            criteria.andClassIdEqualTo(condition.getClassId());
        }
        return studentMapper.selectByExample(example);
    }

    @Override
    public List<HealthyReport> selectHealthyReport(HealthyReportCondition condition, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        HealthyReportExample example = new HealthyReportExample();
        HealthyReportExample.Criteria criteria = example.createCriteria();
        if (condition.getId() != null && condition.getId() != 0){
            criteria.andIdEqualTo(condition.getId());
        }
        if (condition.getBodyTemperature() != null && condition.getBodyTemperature() != 0){
            criteria.andBodyTemperatureGreaterThanOrEqualTo(condition.getBodyTemperature());
        }
        if (StringsUtils.isNotEmpty(condition.getLocation())){
            criteria.andLocationLike("%" + condition.getLocation() + "%");
        }
        if (StringsUtils.isNotEmpty(condition.getIsHealth())){
            criteria.andIsHealthEqualTo(condition.getIsHealth());
        }
        if (StringsUtils.isNotEmpty(condition.getIsLocalCov())){
            criteria.andIsLocalCovEqualTo(condition.getIsLocalCov());
        }
        if (StringsUtils.isNotEmpty(condition.getStudentId())){
            criteria.andStudentIdEqualTo(condition.getStudentId());
        }
        if (StringsUtils.isNotEmpty(condition.getAdminId())){
            criteria.andAdminIdEqualTo(condition.getAdminId());
        }
        if(null != condition.getReportDate()) {
            criteria.andReportDateEqualTo(condition.getReportDate());
        }
        example.setOrderByClause("report_date DESC");
        return healthyReportMapper.selectByExample(example);

    }

    @Override
    public List<Document> selectDocument(DocumentCondition condition) {
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
    public List<Document> selectApplication(DocumentCondition condition, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return documentDao.selectApplication(condition);
    }

    @Override
    public Integer updateApplication(Integer id, Document document) {
        document.setId(id);
        return documentMapper.updateByPrimaryKeySelective(document);
    }

    @Override
    public int downloadFile(Integer id, HttpServletRequest request, HttpServletResponse response) {
        Document document = documentMapper.selectByPrimaryKey(id);
        String path = document.getPath();
        //path.replace("\\","\\\\");
        String[] split = path.split("/");
        String filename = split[6]+"_"+split[5]+split[7].substring(split[7].lastIndexOf("."));
        if(filename != null){
            File file = new File(path);
            if (file.exists()){
                //response.setContentType("application/octet-stream");
                //这个属性表示响应的内容是通过字节流的方式进行传输的
                response.setHeader("content-type", "application/octet-stream;charset=utf-8");

                //响应头中添加这个，设置下载文件默认的名称
                try {
                    response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;

                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();

                    int i = bis.read(buffer);
                    while(i != -1){
                        os.write(buffer,0,i);
                        i = bis.read(buffer);
                    }
                    System.out.println("download successfully!!!");
                    return 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("download failed!!!");
                    return  0;
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return 0;
    }


//    @Override
//    public String openFile(Integer id) {
//        File file = new File("E:\\internship_manage\\openFile\\toPdfFileTest.docx");//需要转换的文件
//        try {
//            File newFile = new File("E:\\internship_manage\\openFile\\pdf");//转换之后文件生成的地址
//            if (!newFile.exists()) {
//                newFile.mkdirs();
//            }
//            //文件转化
//            converter.convert(file).to(new File("E:\\internship_manage\\openFile\\pdf\\hello.pdf")).execute();
//            //使用response,将pdf文件以流的方式发送的前端
//            ServletOutputStream outputStream = response.getOutputStream();
//            InputStream in = new FileInputStream(new File("E:\\internship_manage\\openFile\\pdf\\hello.pdf"));// 读取文件
//            // copy文件
//            int i = IOUtils.copy(in, outputStream);
//            System.out.println(i);
//            in.close();
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "This is to pdf";
//    }
}
