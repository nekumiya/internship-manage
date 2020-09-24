package com.guet.internship.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {
    @ApiModelProperty(value = "主键，通知编号")
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "通知类型： 1. 班级通知 2. 个人消息")
    private Integer type;

    @ApiModelProperty(value = "发送时间")
    private Date sendDate;

    @ApiModelProperty(value = "管理员ID")
    private String adminId;

    @ApiModelProperty(value = "实习班级ID")
    private Integer classId;

    private Integer AcceptId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAcceptId() {
        return AcceptId;
    }

    public void setAcceptId(Integer acceptId) {
        AcceptId = acceptId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", type=" + type +
                ", sendDate=" + sendDate +
                ", adminId='" + adminId + '\'' +
                ", classId=" + classId +
                ", AcceptId=" + AcceptId +
                '}';
    }
}