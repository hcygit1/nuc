package com.personal.weappadmin.backendapi.login.web;/*
 *Created by LLP on 2023年07月10
 */

import com.personal.weappadmin.backendapi.login.service.LoginService;
import com.personal.weappadmin.backendapi.user.model.User;
import com.personal.weappadmin.core.controller.BaseAuthController;
import com.personal.weappadmin.exception.OV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author author
 * @ClassName UserController
 * @Description TODO
 * @time 2023/7/10 20:35
 **/
@RestController
@RequestMapping("/")
public class LoginController extends BaseAuthController {


    @Autowired
    LoginService loginService;


    /**
    * @author: author
    * @Date: 2023/7/11
    * @Description: 登录接口   查询是否有该用户   如果有密码是否正确
    * @Param: [user]
    * @return: java.lang.Object
    */
    @PostMapping("/login")
    public Object login(@RequestBody User user){

        return new OV().success(loginService.login(user,getRequest()));
    }

    @PostMapping("/loginOut")
    public Object loginOut(){
        loginService.loginOut(getRequest());
        return new OV().success();
    }
}
