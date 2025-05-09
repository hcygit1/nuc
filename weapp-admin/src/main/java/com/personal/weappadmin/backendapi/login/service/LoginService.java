package com.personal.weappadmin.backendapi.login.service;/*
 *Created by LLP on 2023年07月10
 */

import com.alibaba.fastjson.JSONObject;
import com.personal.weappadmin.backendapi.user.model.User;
import com.personal.weappadmin.backendapi.user.repo.UserRepo;
import com.personal.weappadmin.constant.Constants;
import com.personal.weappadmin.exception.MyException;
import com.personal.weappadmin.rbac.service.RbacService;
import com.personal.weappadmin.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author author
 * @ClassName LoginService
 * @Description TODO
 * @time 2023/7/10 20:43
 **/

@Service
public class LoginService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    RbacService rbacService;

    public Object login(User user, HttpServletRequest request){
        JSONObject result=new JSONObject();
        User user1=userRepo.getByMobile(user.getMobile());
        if(user1==null||!user.getPassword().equals(user1.getPassword())){
            throw new MyException("用户名或密码有误");
        }
        result.put("token", JwtUtils.pmUsergenerateToken(user1));
        result.put("userInfo",user1);
        JSONObject permission=rbacService.getPermissionList(user1);
        result.put("permissionList",permission.getJSONArray("permissionList"));
        result.put("per",permission.getJSONArray("per"));
        result.put("loginState",true);
        request.getSession().setMaxInactiveInterval(24*60*60);
        request.getSession().setAttribute(Constants.SESSION_USER_PM,user1);
        return result;
    }


    public void loginOut(HttpServletRequest request){
        //清除session
        request.getSession().removeAttribute(Constants.SESSION_USER_PM);
    }
}
