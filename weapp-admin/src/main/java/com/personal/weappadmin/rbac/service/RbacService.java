package com.personal.weappadmin.rbac.service;/*
 *Created by LLP on 2023年07月12
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.personal.weappadmin.backendapi.user.model.User;
import com.personal.weappadmin.core.service.CrudService;
import com.personal.weappadmin.exception.MyException;
import com.personal.weappadmin.rbac.model.RbacResource;
import com.personal.weappadmin.rbac.model.RbacRole;
import com.personal.weappadmin.rbac.model.RbacUserRole;
import com.personal.weappadmin.rbac.model.UserMenu;
import com.personal.weappadmin.rbac.repo.RbacResourceRepo;
import com.personal.weappadmin.rbac.repo.RbacRoleRepo;
import com.personal.weappadmin.rbac.repo.RbacRoleResourceRepo;
import com.personal.weappadmin.rbac.repo.RbacUserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author author
 * @ClassName RbacService
 * @Description TODO
 * @time 2023/7/12 10:09
 **/

@Service
public class RbacService extends CrudService<RbacResourceRepo, RbacResource,Long> {

    @Autowired
    RbacRoleRepo rbacRoleRepo;
    @Autowired
    RbacRoleResourceRepo rbacRoleResourceRepo;
    @Autowired
    RbacUserRoleRepo rbacUserRoleRepo;

    /*查询用户权限列表*/
    public JSONObject getPermissionList(User user){
        JSONObject result=new JSONObject();
        //现查询一级菜单
        List<UserMenu> parentsList=curdRepo.selectParentMenu(user.getId());
        //根据一级菜查询二级菜单
        for (UserMenu u :parentsList) {
            List<RbacResource> menu=curdRepo.selectChildrenMenuPer(user.getId(),u.getId());
            u.setSubMenu(menu);
        }
        JSONArray per=new JSONArray();
        for (UserMenu userMenu:parentsList) {
            for (RbacResource ss:userMenu.getSubMenu()) {
                per.add(ss.getJumpPath());
            }
        }
        result.put("permissionList",parentsList);
        result.put("per",per);
        return result;

    }


    public Object rbacResoure(){
        JSONArray jsonArray=new JSONArray();
        JSONObject result=new JSONObject();
        UserMenu topNode=curdRepo.getTopNode();
        result.put("id",topNode.getId());
        result.put("label",topNode.getName());
        result.put("description",topNode.getDescription());
        result.put("iconStyle",topNode.getIconStyle());
        result.put("jumpPath",topNode.getJumpPath());
        result.put("level","1");
        List<Map<String,Object>> middle=curdRepo.getChildrenByParentNode(topNode.getId());
        for (Map<String,Object> map:middle) {
            List<Map<String,Object>> children=curdRepo.getChildrenByParentNode(Long.parseLong(map.get("id").toString()));
            for (Map<String,Object> map1:children) {
                map1.put("level","3");
            }
            map.put("children",children);
            map.put("level","2");
        }
        result.put("children",middle);
        jsonArray.add(result);
        return jsonArray;
    }

         public Object rbacRoles(){
           return rbacRoleRepo.selectRoles();
         }


    public void addResoure(JSONObject jsonObject){

        RbacResource rbacResource=new RbacResource();
        rbacResource.setName(jsonObject.getString("name"));
        rbacResource.setDescription(jsonObject.getString("description"));
        rbacResource.setIconStyle(jsonObject.getString("iconStyle"));
        rbacResource.setJumpPath(jsonObject.getString("jumpPath"));
        rbacResource.setSort(jsonObject.getInteger("sort"));
        rbacResource.setParentId(jsonObject.getLong("parentId"));
        curdRepo.add(rbacResource);
    }


    @Transactional(rollbackFor = Exception.class)
    public void removeResoure(JSONObject jsonObject){

        Long parentId=jsonObject.getLong("id");
        RbacResource rbacResource=curdRepo.get(parentId);
        if(rbacResource==null||rbacResource.getParentId()==0){
            throw new MyException("数据有误");
        }

        curdRepo.deletedById(parentId);
        //删除子节点
        curdRepo.deletedByParentId(parentId);

    }

    public void updateResource(JSONObject jsonObject){
        RbacResource rbacResource=curdRepo.get(jsonObject.getLong("id"));
        if(rbacResource==null){
            throw new MyException("资源不存在");
        }
        rbacResource.setJumpPath(jsonObject.getString("jumpPath"));
        rbacResource.setDescription(jsonObject.getString("description"));
        rbacResource.setSort(jsonObject.getInteger("sort"));
        rbacResource.setIconStyle(jsonObject.getString("iconStyle"));
        rbacResource.setName(jsonObject.getString("label"));
        curdRepo.update(rbacResource);
    }

   public void addRole(RbacRole rbacRole, User user){
     rbacRole.setOpaId(user.getId());
     rbacRoleRepo.add(rbacRole);
    }

   public void removeRole(RbacRole rbacRole){
        RbacRole rbacRole1=rbacRoleRepo.get(rbacRole.getId());
       rbacRole1.setDeleted(true);
     rbacRoleRepo.update(rbacRole1);
    }

   public void updateRole(RbacRole rbacRole){
     rbacRoleRepo.update(rbacRole);
    }


    public Object getResourceIdsByRoleId(Long roleId){
       // List<String> longs= rbacRoleResourceRepo.getResourceIdsByRoleId(roleId);
        return rbacRoleResourceRepo.getResourceIdsByRoleId(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addroleResouce(JSONObject jsonObject,User user){
        rbacRoleResourceRepo.deletedOriginalResource(jsonObject.getLong("roleId"));
        //先删除原有资源
        List<Long> parentIds=(List<Long>)curdRepo.findParentIds(jsonObject.getObject("listIds",List.class));
        List<Long> selectIds = JSONArray.parseArray(jsonObject.getObject("listIds",List.class).toString(),Long.class);
        selectIds.addAll(parentIds);
        selectIds=selectIds.stream()
                .distinct()
                .collect(Collectors.toList());
        selectIds=selectIds.stream().filter(f -> !(String.valueOf(f).equals("1")||String.valueOf(f).equals("0"))).collect(Collectors.toList());
        rbacRoleResourceRepo.addRoleResource(jsonObject.getLong("roleId"),selectIds,user.getId());
    }


    public Object getRoleByUserId(Long userId){
        return  rbacUserRoleRepo.getRoleByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addRoleOfUser(JSONObject jsonObject,User user){
        //{"userId":_this.userItem.id,"roleId":_this.currentSelectId}
        //先删除当前角色
        rbacUserRoleRepo.deleteByUserId(jsonObject.getLong("userId"));
        //再添加新的角色
        RbacUserRole rbacUserRole=new RbacUserRole();
        rbacUserRole.setUserId(jsonObject.getLong("userId"));
        rbacUserRole.setRoleId(jsonObject.getLong("roleId"));
        rbacUserRole.setOpaId(user.getId());
        rbacUserRoleRepo.add(rbacUserRole);

    }

}
