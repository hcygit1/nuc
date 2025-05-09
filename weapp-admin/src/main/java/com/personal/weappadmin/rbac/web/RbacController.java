package com.personal.weappadmin.rbac.web;/*
 *Created by LLP on 2023年07月10
 */

import com.alibaba.fastjson.JSONObject;
import com.personal.weappadmin.core.controller.BaseAuthController;
import com.personal.weappadmin.exception.OV;
import com.personal.weappadmin.rbac.model.RbacRole;
import com.personal.weappadmin.rbac.service.RbacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * @author author
 * @ClassName UserController
 * @Description TODO
 * @time 2023/7/10 20:35
 **/
@RestController
@RequestMapping("/rbac")
public class RbacController extends BaseAuthController {


    @Autowired
    RbacService rbacService;

    /**
    * @author: author
    * @Date: 2023/7/16
    * @Description: 获取路由资源
    * @Param: []
    * @return: java.lang.Object
    */
    @GetMapping("/rbacResoure")
    public Object rbacResoure(){
        return new OV().success(rbacService.rbacResoure());
    }


    /**
    * @author: author
    * @Date: 2023/7/16
    * @Description: 获取角色资源
    * @Param: []
    * @return: java.lang.Object
    */
    @GetMapping("/rbacRoles")
    public Object rbacRoles(){
        return new OV().success(rbacService.rbacRoles());
    }


    /**
    * @author: author
    * @Date: 2023/7/16
    * @Description: 添加路由资源
    * @Param: [jsonObject]
    * @return: java.lang.Object
    */
    @PostMapping("/addResoure")
    public Object addResoure(@RequestBody JSONObject jsonObject){
        rbacService.addResoure(jsonObject);
        return new OV().success();
    }

    /**
     * @author: author
     * @Date: 2023/7/16
     * @Description: 删除路由资源
     * @Param: [jsonObject]
     * @return: java.lang.Object
     */
    @PostMapping("/removeResoure")
    public Object removeResoure(@RequestBody JSONObject jsonObject){
        rbacService.removeResoure(jsonObject);
        return new OV().success();
    }
    /**
     * @author: author
     * @Date: 2023/7/16
     * @Description: 修改路由资源
     * @Param: [jsonObject]
     * @return: java.lang.Object
     */
    @PostMapping("/updateResource")
    public Object updateResource(@RequestBody JSONObject jsonObject){
        rbacService.updateResource(jsonObject);
        return new OV().success();
    }
    /**
     * @author: author
     * @Date: 2023/7/16
     * @Description: 添加角色
     * @Param: [jsonObject]
     * @return: java.lang.Object
     */
    @PostMapping("/addRole")
    public Object addRole(@RequestBody RbacRole rbacRole){
        rbacService.addRole(rbacRole,getLoginUserPm());
        return new OV().success();
    }

    /**
     * @author: author
     * @Date: 2023/7/16
     * @Description: 删除角色
     * @Param: [jsonObject]
     * @return: java.lang.Object
     */
    @PostMapping("/removeRole")
    public Object removeRole(@RequestBody RbacRole rbacRole){
        rbacService.removeRole(rbacRole);
        return new OV().success();
    }

    /**
     * @author: author
     * @Date: 2023/7/16
     * @Description: 修改角色
     * @Param: [jsonObject]
     * @return: java.lang.Object
     */
    @PostMapping("/updateRole")
    public Object updateRole(@RequestBody RbacRole rbacRole){
        rbacService.updateRole(rbacRole);
        return new OV().success();
    }


    /**
     * @author: author
     * @Date: 2023/7/16
     * @Description: 根据角色获取该角色的资源
     * @Param: [jsonObject]
     * @return: java.lang.Object
     */
    @GetMapping("/getResourceIdsByRoleId")
    public Object getResourceIdsByRoleId(@RequestParam("roleId") Long  roleId){
        return new OV().success(rbacService.getResourceIdsByRoleId(roleId));
    }

    /**
     * @author: author
     * @Date: 2023/7/16
     * @Description: 为角色添加资源
     * @Param: [jsonObject]
     * @return: java.lang.Object
     */
    @PostMapping("/addroleResouce")
    public Object addroleResouce(@RequestBody JSONObject jsonObject){
        rbacService.addroleResouce(jsonObject,getLoginUserPm());
        return new OV().success();
    }
  /**
  * @author: author
  * @Date: 2023/7/17
  * @Description: 查询用户当前拥有的角色id
  * @Param: [userId]
  * @return: java.lang.Object
  */
    @GetMapping("/getRoleByUserId")
    public Object getRoleByUserId(@RequestParam("userId") Long  userId){

        return new OV().success( rbacService.getRoleByUserId(userId));
    }


    /**
    * @author: author
    * @Date: 2023/7/17
    * @Description: 增加用户权限
    * @Param: [jsonObject]
    * @return: java.lang.Object
    */
    @PostMapping("/addRoleOfUser")
    public Object addRoleOfUser(@RequestBody JSONObject jsonObject){
        rbacService.addRoleOfUser(jsonObject,getLoginUserPm());
        return new OV().success();
    }

}
