package com.guet.internship.controller;

import com.guet.internship.common.api.CommonPage;
import com.guet.internship.common.api.CommonResult;
import com.guet.internship.condition.*;
import com.guet.internship.dto.StudentUserDetails;
import com.guet.internship.dto.UserLoginParam;
import com.guet.internship.mbg.model.*;
import com.guet.internship.mbg.model.Class;
import com.guet.internship.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.elasticsearch.common.Strings;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.*;

/**
 * Created by 欲隐君。 on 2020/8/24
 */
@Api(tags = "StudentController",description = "实习学生接口")
@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;  //Authorization
    @Value("${jwt.tokenHead}")
    private String tokenHead;  //Bearer

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @ApiOperation("登录接口，登录后返回token")
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Valid @RequestBody UserLoginParam userLoginParam, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                System.out.println("fieldError = " + fieldError);
            }
            return CommonResult.validateFailed("用户名或密码为空");
        }

        String token = studentService.login(userLoginParam.getAccount(), userLoginParam.getPassword());

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

        String account = getStudentUserDetails().getStudent().getAccount();
        Student student = studentService.getStudentByAccount(account);

        return CommonResult.success(student);

    }

    @ApiOperation("修改个人资料")
    @RequestMapping(value = "/modify.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePersonal(@RequestBody Student student){
        CommonResult commonResult;
        String account = getStudentUserDetails().getStudent().getAccount();
        Integer count = studentService.updatePersonal(account, student);

        if (count == 1) {
            commonResult = CommonResult.success(student);
            LOGGER.debug("修改个人信息成功 ： {}",student);
        }else {
            commonResult = CommonResult.failed("修改信息失败");
            LOGGER.debug("修改个人信息失败 ： {}",student);
        }

        return commonResult;

    }

    @ApiOperation("学生选择实习身份")
    @RequestMapping(value = "/selectIdentify.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult selectIdentify(@RequestBody Student student){
        CommonResult commonResult;
        String account = getStudentUserDetails().getStudent().getAccount();
        int count = studentService.updateStudent(account, student);
        if (count == 1) {
            commonResult = CommonResult.success(student);
            LOGGER.debug("updateStudent success:{}", student);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateStudent failed:{}", student);
        }
        return commonResult;

    }


    @ApiOperation("查询实习列表接口")
    @RequestMapping(value = "/searchInternshipList.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult searchInternship(InternshipCondition internshipCondition,
                                         @RequestParam(value = "pageNum",defaultValue = "1")
                                         @ApiParam("页码") Integer pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "5")
                                         @ApiParam("每页数量") Integer pageSize){
        List<Internship> internships = studentService.selectInternship(internshipCondition,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(internships),"操作成功");
    }

    @ApiOperation("查询班级列表接口")
    @RequestMapping(value = "/searchClassesList.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult searchClasses(ClassCondition classCondition,
                                      @RequestParam(value = "pageNum",defaultValue = "1")
                                      @ApiParam("页码") Integer pageNum,
                                      @RequestParam(value = "pageSize",defaultValue = "5")
                                      @ApiParam("每页数量") Integer pageSize){
        List<Class> classList = studentService.selectClasses(classCondition,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(classList),"操作成功");
    }

    @ApiOperation("实习生选择实习年级或选择班级")
    @RequestMapping(value = "/intoGroup.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult selectInternship( @RequestBody Student student){
        CommonResult commonResult;
        String account = getStudentUserDetails().getStudent().getAccount();
        int count = studentService.updateStudent(account, student);
        if (count == 1) {
            commonResult = CommonResult.success(student);
            LOGGER.debug("updateStudent success:{}", student);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateStudent failed:{}", student);
        }
        return commonResult;
    }


    @ApiOperation("每日健康上报(页面加载数据)")
    @RequestMapping(value = "/showHealthyData.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult showHealthyData(){
        String account = getStudentUserDetails().getStudent().getAccount();
        HealthyReport healthyReport = studentService.selectHealthyData(account);
        return CommonResult.success(healthyReport,"操作成功");
    }

    @ApiOperation("每日健康上报(提交数据)")
    @RequestMapping(value = "/healthyReport.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult healthyReport(@RequestBody HealthyReport healthyReport){
        CommonResult commonResult;
        int count = studentService.insertHealthyReport(healthyReport);
        if (count == 1){
            commonResult = CommonResult.success(healthyReport);
            LOGGER.debug("create healthyReport success:{}",healthyReport);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("create healthyReport failed:{}",healthyReport);
        }
        return commonResult;
    }

    @ApiOperation("考勤签到")
    @RequestMapping(value = "/sign.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult sign(){
        CommonResult commonResult;
        String account = getStudentUserDetails().getStudent().getAccount();
        int count = studentService.insertSign(account);
        if (count == 1){
            commonResult = CommonResult.success("操作成功");
        } else {
            commonResult = CommonResult.failed("操作失败");
        }
        return commonResult;
    }


    @ApiOperation("查看上传文件状态")
    @RequestMapping(value = "/checkStatus.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult checkStatus(DocumentCondition documentCondition,
                                    @RequestParam(value = "pageNum",defaultValue = "1")
                                    @ApiParam("页码") Integer pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "5")
                                    @ApiParam("每页数量") Integer pageSize){
        List<Document> documents = studentService.selectDocument(documentCondition,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(documents),"操作成功");
    }

    @ApiOperation("上传文件")
    @RequestMapping(value = "/uploadFile.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult uploadFile(@RequestParam("file") MultipartFile file, Document document){
        CommonResult commonResult;

        String account = getStudentUserDetails().getStudent().getAccount();
        document.setStudentId(account);
        if (file.isEmpty()){
            return CommonResult.failed("上传失败，给文件为空！！！");
        }
        int count = studentService.uploadFile(file,document);
        if (count == 1){
            commonResult = CommonResult.success("上传文件成功");
            LOGGER.debug("upload applicationForm success");
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("upload applicationForm failed");
        }
        return commonResult;

    }

    @ApiOperation("获取未读通知列表")
    @RequestMapping(value = "getNoticesList.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getNoticesList(AcceptCondition acceptCondition,
                                       @RequestParam(value = "pageNum",defaultValue = "1")
                                       @ApiParam("页码") Integer pageNum,
                                       @RequestParam(value = "pageSize",defaultValue = "5")
                                       @ApiParam("每页数量") Integer pageSize){

        CommonResult commonResult = null;
        List<Accept> accepts = studentService.selectAccepts(acceptCondition,pageNum,pageSize);
        ArrayList<Notice> notices = new ArrayList<>();
        int count = accepts.size();
        for (Accept accept : accepts) {
            Notice notice = studentService.selectNotice(accept.getNoticeId());
            notice.setAcceptId(accept.getId());
            notices.add(notice);
        }

//        HashMap<String, Object> result = new HashMap<>();
//        result.put("data",notices);
//        result.put("count",count);

        return commonResult.success(CommonPage.restPage(notices),"操作成功");

    }

    @ApiOperation("查询通知")
    @RequestMapping(value = "read.do",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult read(NoticeCondition noticeCondition){

        if (noticeCondition.getAcceptId() != null && noticeCondition.getAcceptId() != 0){
            Accept accept = new Accept();
            accept.setCreatedAt(new Date());
            accept.setReadAt(new Date());
            accept.setStatus(1);
            studentService.updateAccept(noticeCondition.getAcceptId(),accept);
        }

        List<Notice> notices = studentService.selectNotices(noticeCondition);
        return CommonResult.success(notices,"操作成功");
    }


    private static StudentUserDetails  getStudentUserDetails(){
        StudentUserDetails studentUserDetails = null;
        //获取用户认证信息对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 认证信息可能为空，因此需要进行判断。
        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof StudentUserDetails){
                studentUserDetails = (StudentUserDetails) principal;
            }
        }
        return studentUserDetails;
    }

}
