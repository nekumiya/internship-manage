package com.guet.internship.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Class implements Serializable {
    @ApiModelProperty(value = "主键，班级编号")
    private Integer id;

    @ApiModelProperty(value = "班级名称")
    private String className;

    @ApiModelProperty(value = "年级")
    private String grade;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "班级描述")
    private String description;

    @ApiModelProperty(value = "班级容量")
    private Integer count;

    @ApiModelProperty(value = "管理员账号")
    private String adminId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
        sb.append(", className=").append(className);
        sb.append(", grade=").append(grade);
        sb.append(", major=").append(major);
        sb.append(", description=").append(description);
        sb.append(", count=").append(count);
        sb.append(", adminId=").append(adminId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}