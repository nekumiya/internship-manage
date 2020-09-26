package com.guet.internship.controller;

import com.guet.internship.common.api.CommonPage;
import com.guet.internship.common.api.CommonResult;
import com.guet.internship.condition.*;
import com.guet.internship.dto.CommonUserDetails;
import com.guet.internship.dto.UserLoginParam;
import com.guet.internship.mbg.model.*;
import com.guet.internship.mbg.model.Class;
import com.guet.internship.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by 欲隐君。 on 2020/8/20
 */

@Api(tags = "AdminController",description = "管理员接口")
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;  //Authorization
    @Value("${jwt.tokenHead}")
    private String tokenHead;  //Bearer



    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @ApiOperation("登录接口,登录以后返回token")
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Valid @RequestBody  UserLoginParam userLoginParam, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                System.out.println("fieldError = " + fieldError);
            }
            return CommonResult.validateFailed("用户名或密码为空");
        }

        String token = adminService.login(userLoginParam.getAccount(), userLoginParam.getPassword(),userLoginParam.getUserType());

        if (token == null){
            return  CommonResult.validateFailed("用户名或密码错误！！！");
        }

        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);

        return CommonResult.success(tokenMap,"登录成功！！！");

    }


    @ApiOperation("根据账号查询个人资料")
    @RequestMapping(value = "/personal.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getPersonal(){

        String account = getCommonUserDetails().getAccount();

        Admin admin = adminService.getAdminByAccount(account);

        return CommonResult.success(admin);

    }


    @ApiOperation("修改个人资料")
    @RequestMapping(value = "/modify.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePersonal( @RequestBody Admin admin){
        CommonResult commonResult;
        String account = getCommonUserDetails().getAccount();
        Integer count = adminService.updatePersonal(account, admin);

        if (count == 1) {
            commonResult = CommonResult.success(admin);
            LOGGER.debug("修改个人信息成功 ： {}",admin);
        }else {
            commonResult = CommonResult.failed("修改信息失败");
            LOGGER.debug("修改个人信息失败 ： {}",admin);
        }

        return commonResult;

    }


    @ApiOperation("导入学生名单，文件格式必须是 .xl 或 .xlsx")
    @RequestMapping(value = "/excelImport.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult uploadExc(@RequestParam("file")MultipartFile file) throws IOException {
        String account = getCommonUserDetails().getAccount();
        if (file.isEmpty()){
            return CommonResult.failed("文件为空!");
        }

        //获取文件名
        String fileName = file.getOriginalFilename();

        //获取文件格式
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!fileType.equals("xls") && !fileType.equals("xlsx")){
            return CommonResult.failed("文件格式不对，文件格式必须是 .xl 或 .xlsx");
        }

        //读取文件
        InputStream inputStream = file.getInputStream();

        //获取操作excel的实例workbook
        Workbook workbook=null;
        //03及以下excel版本
        if(fileType.equals("xls")){
            workbook=new HSSFWorkbook(inputStream);
        }
        //07及以上excel版本
        if(fileType.equals("xlsx")){
            workbook = new XSSFWorkbook(inputStream);
        }

        Sheet sheet = workbook.getSheetAt(0);  //第一个excel表格工作簿
        //实体类集合
        List<Student> studentList = new ArrayList<>();

        //从第二行开始
        for(int i = 1; i <= sheet.getLastRowNum() ; i++) {  //循环将表格内容添加到数据库中
            Row row = sheet.getRow(i);

            Student studentData = new Student();

            for (int j=0;j<8;j++){
                row.getCell(j).setCellType(CellType.STRING);  //设置每个单元格读取的类型为String
            }

            studentData.setAccount(row.getCell(0).getStringCellValue());  //第一列
            studentData.setName(row.getCell(1).getStringCellValue());
            studentData.setSex(row.getCell(2).getStringCellValue());
            studentData.setPhone(row.getCell(3).getStringCellValue());
            studentData.setMailbox(row.getCell(4).getStringCellValue());
            studentData.setMajor(row.getCell(5).getStringCellValue());
            studentData.setGrade(row.getCell(6).getStringCellValue());
            studentData.setClassName(row.getCell(7).getStringCellValue());

            //data.setId(Integer.valueOf((int) row.getCell(0).getNumericCellValue()));
            //data.setName(row.getCell(1).getStringCellValue());
            //SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            //data.setCreateDate(df.parse(df.format(HSSFDateUtil.getJavaDate(row.getCell(2).getNumericCellValue()))));
            studentList.add(studentData);
        }

        //TODO 从前端传来的token中获取当前登录用户的ID,而不是从前端传来用户ID

        for (Student student : studentList) {
            student.setPassword("123456");
            student.setAdminId(account);
            student.setScore(Float.valueOf(0));
            student.setClassId(0);
            student.setInternshipId(0);
            adminService.createStudent(student);
        }

        return CommonResult.success(studentList,"操作成功");
    }


    @ApiOperation("按年级新建自主实习")
    @RequestMapping(value = "/createInternship.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createInternship(@RequestBody Internship internship){
        CommonResult commonResult;
        String account = getCommonUserDetails().getAccount();
        internship.setAdminId(account);
        Integer count = adminService.createInternship(internship);

        if (count == 1) {
            commonResult = CommonResult.success(internship);
            LOGGER.debug("添加自主实习成功 ： {}",internship);
        }else {
            commonResult = CommonResult.failed("修改信息失败");
            LOGGER.debug("添加自主实习失败 ： {}",internship);
        }
        return commonResult;
    }

    @ApiOperation("新建实习班级")
    @RequestMapping(value = "/createClass.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createClass(@RequestBody Class classes){
        CommonResult commonResult;
        String account = getCommonUserDetails().getAccount();
        classes.setAdminId(account);
        Integer count = adminService.createClass(classes);

        if (count == 1) {
            commonResult = CommonResult.success(classes,"操作成功");
            LOGGER.debug("添加实习班级成功 ： {}",classes);
        }else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("添加实习班级失败 ： {}",classes);
        }
        return commonResult;
    }

    @ApiOperation("查询自主实习列表")
    @RequestMapping(value = "/searchInternship.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult searchInternship(InternshipCondition internshipCondition,
                                         @RequestParam(value = "pageNum",defaultValue = "1")
                                         @ApiParam("页码") Integer pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "5")
                                         @ApiParam("每页数量") Integer pageSize){
        List<Internship> internships = adminService.selectInternship(internshipCondition,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(internships),"操作成功");
    }

    @ApiOperation("查询实习班级列表")
    @RequestMapping(value = "/searchClass.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult searchClass( ClassCondition classCondition,
                                    @RequestParam(value = "pageNum",defaultValue = "1")
                                    @ApiParam("页码") Integer pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "5")
                                    @ApiParam("每页数量") Integer pageSize){
        List<Class> classes = adminService.selectClass(classCondition,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(classes),"操作成功");
    }

    @ApiOperation("修改实习班级信息")
    @RequestMapping(value = "/updateClass.do/{id}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateClass(@PathVariable("id") Integer id,@RequestBody Class classes){
        CommonResult commonResult;
        Integer count = adminService.updateClass(id, classes);

        if (count == 1) {
            commonResult = CommonResult.success(classes);
            LOGGER.debug("修改实习班级信息成功 ： {}",classes);
        }else {
            commonResult = CommonResult.failed("修改信息失败");
            LOGGER.debug("修改实习班级信息失败 ： {}",classes);
        }

        return commonResult;
    }

    @ApiOperation("修改自主实习信息")
    @RequestMapping(value = "/updateInternship.do/{id}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateInternship(@PathVariable("id") Integer id,@RequestBody Internship internship){
        CommonResult commonResult;
        Integer count = adminService.updateInternship(id, internship);

        if (count == 1) {
            commonResult = CommonResult.success(internship);
            LOGGER.debug("修改自主实习信息成功 ： {}",internship);
        }else {
            commonResult = CommonResult.failed("修改信息失败");
            LOGGER.debug("修改自主实习信息失败 ： {}",internship);
        }

        return commonResult;
    }

    @ApiOperation("按年级删除自主实习")
    @RequestMapping(value = "/deleteInternship.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteInternship(@RequestParam("ids") List<Integer> ids){
        CommonResult commonResult;
        Integer count=0;
        for (Integer id : ids) {
            count = adminService.deleteInternship(id);
        }

        if (count == 1) {
            commonResult = CommonResult.success("删除成功");
        }else {
            commonResult = CommonResult.failed("删除失败");
        }
        return commonResult;
    }

    @ApiOperation("删除实习班级")
    @RequestMapping(value = "/deleteClass.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteClass(@RequestParam("ids") List<Integer> ids){
        CommonResult commonResult;
        Integer count=0;
        for (Integer id : ids) {
            count = adminService.deleteClass(id);
        }

        if (count == 1) {
            commonResult = CommonResult.success("删除成功");
        }else {
            commonResult = CommonResult.failed("删除失败");
        }
        return commonResult;
    }

    @ApiOperation("查询健康上报情况")
    @RequestMapping(value = "/healthyReport.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult checkHealthyReport(HealthyReportCondition healthyReportCondition,
                                           @RequestParam(value = "pageNum",defaultValue = "1")
                                           @ApiParam("页码") Integer pageNum,
                                           @RequestParam(value = "pageSize",defaultValue = "5")
                                           @ApiParam("每页数量") Integer pageSize){

        List<HealthyReport> healthyReports = adminService.selectHealthyReport(healthyReportCondition,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(healthyReports),"操作成功");
    }

    @ApiOperation("查询考勤情况")
    @RequestMapping(value = "/getSign.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getSign(SignCondition signCondition,
                                @RequestParam(value = "pageNum",defaultValue = "1")
                                @ApiParam("页码") Integer pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "5")
                                @ApiParam("每页数量") Integer pageSize){

        List<Sign> signs = adminService.selectSign(signCondition,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(signs),"操作成功");
    }

    @ApiOperation("查询学生列表")
    @RequestMapping(value = "/searchStudent.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult searchStudent(StudentCondition studentCondition,
                                      @RequestParam(value = "pageNum",defaultValue = "1")
                                      @ApiParam("页码") Integer pageNum,
                                      @RequestParam(value = "pageSize",defaultValue = "5")
                                      @ApiParam("每页数量") Integer pageSize){
        List<Student> studentList = adminService.selectStudents(studentCondition,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(studentList),"操作成功");
    }

    @ApiOperation("新增学生信息")
    @RequestMapping(value = "/createStudent.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createStudent(@RequestBody Student student){
        CommonResult commonResult;
        String account = getCommonUserDetails().getAccount();
        student.setAdminId(account);
        Integer count = adminService.createStudent(student);
        if (count == 1) {
            commonResult = CommonResult.success(student,"操作成功");
            LOGGER.debug("添加学生信息成功 ： {}",student);
        }else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("添加学生信息失败 ： {}",student);
        }
        return commonResult;
    }

    @ApiOperation("删除学生信息")
    @RequestMapping(value = "/deleteStudent.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteStudent(@RequestParam("accounts") List<String> accounts){
        CommonResult commonResult;
        Integer count=0;
        for (String account : accounts) {
            count = adminService.deleteStudent(account);
        }

        if (count == 1) {
            commonResult = CommonResult.success("删除成功");
        }else {
            commonResult = CommonResult.failed("删除失败");
        }
        return commonResult;
    }

    @ApiOperation("查询申请表列表")
    @RequestMapping(value = "/getApplicationList.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getApplicationList(DocumentCondition documentCondition,
                                           @RequestParam(value = "pageNum",defaultValue = "1")
                                           @ApiParam("页码") Integer pageNum,
                                           @RequestParam(value = "pageSize",defaultValue = "5")
                                           @ApiParam("每页数量") Integer pageSize){
        List<Document> documents = adminService.selectApplication(documentCondition,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(documents),"操作成功");
    }

    @ApiOperation("审核实习申请")
    @RequestMapping(value = "/checkApplication.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult checkApplication(@RequestParam("id") Integer id,@RequestParam("status") Integer status){
        CommonResult commonResult;
        Document document = new Document();
        document.setStatus(status);
        Integer count = adminService.updateApplication(id,document);

        if (count == 1) {
            commonResult = CommonResult.success("操作成功");
        }else {
            commonResult = CommonResult.failed("操作失败");
        }

        return commonResult;
    }

    @ApiOperation("下载文件")
    @RequestMapping(value = "/downloadFile.do/{id}",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public CommonResult downloadFile(@PathVariable("id") Integer id,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        CommonResult commonResult;
        int count = adminService.downloadFile(id, request, response);

        if (count == 1) {
            commonResult = CommonResult.success("操作成功");
        }else {
            commonResult = CommonResult.failed("操作失败");
        }

        return commonResult;
    }

//    @ApiOperation("预览文件")
//    @RequestMapping(value = "/openFile.do/{id}",method = RequestMethod.GET)
//    @ResponseBody
//    public String openFile(@PathVariable("id") Integer id){
//
//        return adminService.openFile(id);
//    }

    @ApiOperation("根据学生学号查询该学生上传的文件信息")
    @RequestMapping(value = "/getFiles.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getFiles(@RequestParam("studentId") String studentId){
        List<Document> documentList = adminService.selectDocumentsByStudentId(studentId);
        return CommonResult.success(documentList,"操作成功");
    }

    @ApiOperation("发布通知")
    @RequestMapping(value = "/publish.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult publish(@RequestBody Notice notice){

        CommonResult commonResult;
        String account = getCommonUserDetails().getAccount();
        notice.setSendDate(new Date());
        notice.setAdminId(account);
        Integer count =  adminService.createNotice(notice);

        StudentCondition studentCondition = new StudentCondition();
        studentCondition.setClassId(notice.getClassId());
        List<Student> studentList = adminService.selectStudents(studentCondition);

        for (Student student : studentList) {
            Accept accept = new Accept();
            accept.setNoticeId(notice.getId());
            accept.setStatus(0);
            accept.setStudentId(student.getAccount());
            adminService.createAccept(accept);
        }

        if (count == 1) {
            commonResult = CommonResult.success(notice,"操作成功");
            LOGGER.debug("添加通知消息成功 ： {}",notice);
        }else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("添加通知消息失败 ： {}",notice);
        }

        return commonResult;
    }


    private static CommonUserDetails getCommonUserDetails(){
        CommonUserDetails commonUserDetails = null;
        //获取用户认证信息对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 认证信息可能为空，因此需要进行判断。
        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof CommonUserDetails){
                commonUserDetails = (CommonUserDetails) principal;
            }
        }
        return commonUserDetails;
    }
}
