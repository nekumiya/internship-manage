package com.guet.internship.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Document implements Serializable {
    @ApiModelProperty(value = "主键，上传文档编号")
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "文件类型（后缀名）")
    private String documentType;

    @ApiModelProperty(value = "文件类别：1.  申请表 2. 实习报告 3. 评价表")
    private Integer documentCategory;

    @ApiModelProperty(value = "上传时间")
    private Date uploadTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "保存路径")
    private String path;

    @ApiModelProperty(value = "文件状态： 1、 待审核 2、审核通过 3、审核不通过 ")
    private Integer status;

    @ApiModelProperty(value = "学生学号")
    private String studentId;

    @ApiModelProperty(value = "管理员账号")
    private String adminId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Integer getDocumentCategory() {
        return documentCategory;
    }

    public void setDocumentCategory(Integer documentCategory) {
        this.documentCategory = documentCategory;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", documentType=").append(documentType);
        sb.append(", documentCategory=").append(documentCategory);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", path=").append(path);
        sb.append(", status=").append(status);
        sb.append(", studentId=").append(studentId);
        sb.append(", adminId=").append(adminId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}