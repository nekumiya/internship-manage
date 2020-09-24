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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", signIn=").append(signIn);
        sb.append(", studentId=").append(studentId);
        sb.append(", adminId=").append(adminId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}