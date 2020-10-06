package com.guet.internship.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class HealthyReport implements Serializable {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "体温")
    private Float bodyTemperature;

    @ApiModelProperty(value = "位置")
    private String location;

    @ApiModelProperty(value = "是否健康 ")
    private String isHealth;

    @ApiModelProperty(value = "当地是否是疫情区域")
    private String isLocalCov;

    @ApiModelProperty(value = "学生学号")
    private String studentId;

    @ApiModelProperty(value = "管理员账号ID")
    private String adminId;

    @ApiModelProperty(value = "上报时间")
    private Date reportDate;


    private String name; //学生姓名

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

    public Float getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(Float bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIsHealth() {
        return isHealth;
    }

    public void setIsHealth(String isHealth) {
        this.isHealth = isHealth;
    }

    public String getIsLocalCov() {
        return isLocalCov;
    }

    public void setIsLocalCov(String isLocalCov) {
        this.isLocalCov = isLocalCov;
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

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public String toString() {
        return "HealthyReport{" +
                "id=" + id +
                ", bodyTemperature=" + bodyTemperature +
                ", location='" + location + '\'' +
                ", isHealth='" + isHealth + '\'' +
                ", isLocalCov='" + isLocalCov + '\'' +
                ", studentId='" + studentId + '\'' +
                ", adminId='" + adminId + '\'' +
                ", reportDate=" + reportDate +
                ", name='" + name + '\'' +
                '}';
    }
}