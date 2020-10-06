package com.guet.internship.service;


import com.guet.internship.condition.*;
import com.guet.internship.mbg.model.*;
import com.guet.internship.mbg.model.Class;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 欲隐君。 on 2020/8/20
 *
 * 后台管理员Service
 */
public interface AdminService {

    /**
     * 根据用户名获取后台管理员
     * @param username 用户名
     * @return 管理员对象
     */
    Admin getAdminByUsername(String username);

    /**
     * 根据用户账号获取admin
     * @param account 管理员账号
     * @return
     */
    Admin getAdminByAccount(String account);

    /**
     * 注册功能
     */
    //Administrator register(Administrator administrator);


    /**
     * 登录功能
     * @param account 账号
     * @param password 密码
     * @param userType 用户登录类型
     * @return 生成的JWT token
     */
    String login(String account, String password, String userType);

    /**
     *  修改个人信息
     * @param account 当前用户的账号
     * @param admin   修改后的用户对象
     * @return 返回值： 1 -> 表示操作成功
     */
    Integer updatePersonal(String account, Admin admin);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    //List<> getPermissionList(String account);

    /**
     *  添加学生信息
     * @param student 学生信息
     * @return 返回值： 1 -> 表示操作成功
     */
    Integer createStudent(Student student);

    /**
     *  添加自主实习
     * @param internship
     * @return
     */
    Integer createInternship(Internship internship);

    /**
     *  查询自主实习列表
     * @param internshipCondition 查询条件
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Internship> selectInternship(InternshipCondition internshipCondition, Integer pageNum, Integer pageSize);

    /**
     * 根据id删除实习
     * @param id 实习主键id
     * @return
     */
    Integer deleteInternship(Integer id);

    /**
     * 根据条件查询健康上报情况
     * @param healthyReportCondition 查询条件的封装类
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<HealthyReport> selectHealthyReport(HealthyReportCondition healthyReportCondition, Integer pageNum, Integer pageSize);

    List<Document> selectDocument(DocumentCondition documentCondition);

    List<Document> selectApplication(DocumentCondition documentCondition, Integer pageNum, Integer pageSize);

    Integer updateApplication(Integer id, Document document);

    int downloadFile(Integer id, HttpServletRequest request, HttpServletResponse response);

    //String openFile(Integer id);

    Integer createClass(Class classes);

    List<Class> selectClass(ClassCondition classCondition, Integer pageNum, Integer pageSize);

    Integer deleteClass(Integer id);

    List<Sign> selectSign(SignCondition signCondition, Integer pageNum, Integer pageSize);

    List<Student> selectStudents(StudentCondition studentCondition, Integer pageNum, Integer pageSize);

    Integer deleteStudent(String account);

    List<Document> selectDocumentsByStudentId(String studentId);

    Integer createNotice(Notice notice);

    Integer createAccept(Accept accept);

    Integer updateClass(Integer id, Class classes);

    Integer updateInternship(Integer id, Internship internship);

    List<Student> selectStudents(StudentCondition studentCondition);

    Student selectStudentById(String studentId);
}
