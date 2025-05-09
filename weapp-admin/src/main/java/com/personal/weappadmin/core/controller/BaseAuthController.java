package com.personal.weappadmin.core.controller;




import com.personal.weappadmin.backendapi.user.model.User;
import com.personal.weappadmin.constant.Constants;
import com.personal.weappadmin.web.weappLogin.model.WeappUser;


public class BaseAuthController extends BaseController {



    //===客户端登录用户基础信息
    public WeappUser getLoginUser(){
        WeappUser user = (WeappUser)getSession().getAttribute(Constants.SESSION_USER);
        if(user == null){
            return null;
        }
        return user;
    }

    //===管理端登录用户基础信息
    public User getLoginUserPm(){
        User user = (User)getSession().getAttribute(Constants.SESSION_USER_PM);
        if(user == null){
            return null;
        }
        return user;
    }

}