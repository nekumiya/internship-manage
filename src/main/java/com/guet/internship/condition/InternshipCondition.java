package com.guet.internship.condition;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 欲隐君。 on 2020/8/24
 */
public class InternshipCondition {

    private Integer id;
    private String grade;
    private Integer count;
    private String adminId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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
}
