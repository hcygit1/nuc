package com.personal.weappadmin.rbac.model;




import com.personal.weappadmin.config.model.AliasModel;

import java.util.Date;

public class RbacUserRole implements AliasModel {
    Long id;
    Long userId;
    Long roleId;
    Long opaId;
    Boolean deleted;
    Date createTime;
    Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
