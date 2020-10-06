package com.guet.internship.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Sign implements Serializable {
    @ApiModelProperty(value = "主键，考勤编号")
    private Integer id;

    @ApiModelProperty(value = "签到时间")
    private Date signIn;

    @ApiModelProperty(value = "学生学号")
    private String studentId;

    @ApiModelProperty(value = "管理员编号ID")
    private String adminId;

    private String name;  //学生姓名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return "Sign{" +
                "id=" + id +
                ", signIn=" + signIn +
                ", studentId='" + studentId + '\'' +
                ", adminId='" + adminId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}