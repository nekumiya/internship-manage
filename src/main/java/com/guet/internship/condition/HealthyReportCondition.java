package com.guet.internship.condition;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by 欲隐君。 on 2020/8/24
 */
public class HealthyReportCondition {

    private Integer id;
    private Float bodyTemperature;
    private String location;
    private String isHealth;
    private String isLocalCov;
    private String studentId;
    private String adminId;
    private Date reportDate;

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


}
