package com.personal.weappadmin.rbac.model;

import com.personal.weappadmin.config.model.AliasModel;

import java.util.Date;

public class RbacRole implements AliasModel {
    private Long id;
    private String name;
    private String description;
    private Long opaId;
    private Boolean deleted;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOpaId() {
        return opaId;
    }

    public void setOpaId(Long opaId) {
        this.opaId = opaId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
