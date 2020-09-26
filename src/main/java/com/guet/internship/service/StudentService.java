package com.guet.internship.service;

import com.guet.internship.condition.*;
import com.guet.internship.mbg.model.*;
import com.guet.internship.mbg.model.Class;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by 欲隐君。 on 2020/8/24
 *
 * 学生service
 */
public interface StudentService {

    /**
     *  修改个人资料
     * @param account
     * @param student
     * @return
     */
    Integer updatePersonal(String account, Student student);


    /**
     * 根据用户名获取学生
     * @param username 用户名
     * @return 学生对象
     */
    Student getStudentByUsername(String username);

    /**
     * 根据用户账号获取student
     * @param account 账号（学号）
     * @return student对象
     */
    Student getStudentByAccount(String account);

    /**
     *  登录功能
     * @param account 账号
     * @param password 密码
     * @param userType
     * @return token
     */
    String login(String account, String password, String userType);

    /**
     *  可根据条件查询自主实习列表
     * @return
     * @param internshipCondition 查询条件
     * @param pageNum
     * @param pageSize
     */
    List<Internship> selectInternship(InternshipCondition internshipCondition, Integer pageNum, Integer pageSize);

    int updateStudent(String account, Student student);

    List<Document> selectDocument(DocumentCondition documentCondition, Integer pageNum, Integer pageSize);

    int insertHealthyReport(HealthyReport healthyReport);

    HealthyReport selectHealthyData(String account);

    int uploadFile(MultipartFile file, Document document);

    List<Class> selectClasses(ClassCondition classCondition, Integer pageNum, Integer pageSize);

    int insertSign(String account);

    List<Accept> selectAccepts(AcceptCondition acceptCondition, Integer pageNum, Integer pageSize);

    Notice selectNotice(Integer noticeId);

    List<Notice> selectNotices(NoticeCondition noticeCondition);

    int updateAccept(Integer id,Accept accept);
}
