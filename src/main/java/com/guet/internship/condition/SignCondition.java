package com.guet.internship.condition;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by 欲隐君。 on 2020/9/1
 */
public class SignCondition {

    private Integer id;
    private Date signIn;
    private Date startSign;
    private Date endSign;
    private String studentId;
    private String adminId;
    private String startTime;
    private String endTime;
    private Integer classId;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Date getStartSign() {
        return startSign;
    }

    public void setStartSign(Date startSign) {
        this.startSign = startSign;
    }

    public Date getEndSign() {
        return endSign;
    }

    public void setEndSign(Date endSign) {
        this.endSign = endSign;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSignIn() {
        return signIn;
    }

    public void setSignIn(Date signIn) {
        this.signIn = signIn;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
