package com.personal.weappadmin.rbac.model;


import com.personal.weappadmin.config.model.AliasModel;

import java.util.List;

public class UserMenu implements AliasModel {
    private Long id;
    private String name;
    private Long parentId;
    private String iconStyle;
    private String jumpPath;
    private String description;
    private Integer sort;
    //子菜单
    private List<RbacResource> subMenu ;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJumpPath() {
        return jumpPath;
    }

    public void setJumpPath(String jumpPath) {
        this.jumpPath = jumpPath;
    }

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getIconStyle() {
        return iconStyle;
    }

    public void setIconStyle(String iconStyle) {
        this.iconStyle = iconStyle;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<RbacResource> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<RbacResource> subMenu) {
        this.subMenu = subMenu;
    }
}
