package com.personal.weappadmin.backendapi.user.web;/*
 *Created by LLP on 2023年07月10
 */

import com.alibaba.fastjson.JSONObject;
import com.personal.weappadmin.core.controller.BaseAuthController;
import com.personal.weappadmin.exception.OV;
import com.personal.weappadmin.backendapi.user.model.User;
import com.personal.weappadmin.backendapi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author author
 * @ClassName UserController
 * @Description TODO
 * @time 2023/7/10 20:35
 **/
@RestController
@RequestMapping("/user")
public class UserController extends BaseAuthController {

    @Autowired
    UserService userService;

    /**
     * @author: author
     * @Date: 2023/7/16
     * @Description: 获取所有有效用户
     * @Param: []
     * @return: java.lang.Object
     */
    @GetMapping("/findAllUser")
    public Object findAllUser(){
        return new OV().success(userService.findAllUser());
    }

    /**
     * @author: author
     * @Date: 2023/7/16
     * @Description: 获取所有用户  包括无效用户
     * @Param: []
     * @return: java.lang.Object
     */
    @GetMapping("/findAllUserIncludeUnEable")
    public Object findAllUserIncludeUnEable(
            @RequestParam(value = "mobile",required = false) String mobile,
            @RequestParam(value = "loginName",required = false) String loginName,
            @RequestParam(value = "age",required = false) String age,
            @RequestParam(value = "sex",required = false) String sex,
            @RequestParam(value = "address",required = false) String address

    ){
        return new OV().success(userService.findAllUserIncludeUnEable(mobile,loginName,age,sex,address,getPageBounds()));
    }


   @PostMapping("/updatePassWord")
    public Object updatePassWord(@RequestBody JSONObject jsonObject){
       userService.updatePassWord(jsonObject,getLoginUserPm(),getRequest());
       return new OV().success();
    }

   @PostMapping("/update")
    public Object update(@RequestBody User user){
       userService.update(user);
       return new OV().success();
    }


   @PostMapping("/deleted")
    public Object deleted(@RequestBody User user){
       userService.deleted(user.getId(),getLoginUser().getId());
       return new OV().success();
    }

   @PostMapping("/addUser")
    public Object addUser(@RequestBody User user){
       userService.addUser(user);
       return new OV().success();
    }


}
