package com.guet.internship.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Student implements Serializable {
    @ApiModelProperty(value = "主键，学生账号（学号）")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "身份类型")
    private String identify;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String mailbox;

    @ApiModelProperty(value = "实习所在地")
    private String location;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "年级")
    private String grade;

    @ApiModelProperty(value = "班级")
    private String className;

    @ApiModelProperty(value = "管理员ID")
    private String adminId;

    @ApiModelProperty(value = "实习成绩")
    private Float score;

    @ApiModelProperty(value = "实习年级ID")
    private Integer internshipId;

    @ApiModelProperty(value = "实习班级ID")
    private Integer classId;

    private static final long serialVersionUID = 1L;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(Integer internshipId) {
        this.internshipId = internshipId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", account=").append(account);
        sb.append(", password=").append(password);
        sb.append(", name=").append(name);
        sb.append(", sex=").append(sex);
        sb.append(", identify=").append(identify);
        sb.append(", phone=").append(phone);
        sb.append(", mailbox=").append(mailbox);
        sb.append(", location=").append(location);
        sb.append(", major=").append(major);
        sb.append(", grade=").append(grade);
        sb.append(", className=").append(className);
        sb.append(", adminId=").append(adminId);
        sb.append(", score=").append(score);
        sb.append(", internshipId=").append(internshipId);
        sb.append(", classId=").append(classId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}