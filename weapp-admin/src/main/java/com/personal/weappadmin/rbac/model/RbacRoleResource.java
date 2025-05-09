package com.personal.weappadmin.rbac.model;


import com.personal.weappadmin.config.model.AliasModel;

import java.util.Date;

public class RbacRoleResource implements AliasModel {
    private Long id;
    private Long roleId;
    private Long resourceId;
    private Long opaId;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getOpaId() {
        return opaId;
    }

    public void setOpaId(Long opaId) {
        this.opaId = opaId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RoleResource{");
        sb.append("id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", opaId=").append(opaId);
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}
