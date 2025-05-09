package com.personal.weappadmin.web.weappLogin.web;/*
 *Created by LLP on 2024年01月04
 */

import com.personal.weappadmin.core.controller.BaseAuthController;
import com.personal.weappadmin.exception.OV;
import com.personal.weappadmin.web.weappLogin.model.WeappUser;
import com.personal.weappadmin.web.weappLogin.service.WeappLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @ClassName LoginController
 * @Description TODO
 * @time 2024/1/4 14:46
 **/
@RestController
@RequestMapping("/weapp/login")
public class WeappLoginController extends BaseAuthController {
    @Autowired
    WeappLoginService weappLoginService;


    /*登录*/
    @GetMapping("/login")
    public Object login(@RequestParam("mobile") String mobile,@RequestParam("password") String password){
        return new OV().success(weappLoginService.login(mobile,password,getRequest()));
    }

    /*登录*/
    @PostMapping("/add")
    public Object add(@RequestBody WeappUser weappUser){
        weappLoginService.add(weappUser);
        return new OV().success();
    }
    /*注册*/
    @GetMapping("/register")
    public Object register(@RequestParam("mobile") String mobile,@RequestParam("password") String password,
                           @RequestParam("nickName") String nickName,@RequestParam("imageUrl") String imageUrl){
        return new OV().success(weappLoginService.register(mobile,password,nickName,imageUrl));
    }
    /*退出登录*/
   @GetMapping("/loginout")
    public Object loginout(){
       weappLoginService.loginout(getRequest());
        return new OV().success();
    }

    @GetMapping("/getLoginUserInfo")
    public Object getLoginUserInfo(){
        return new OV().success( weappLoginService.getLoginUserInfo(getLoginUser().getId()));
    }
    @GetMapping("/updateInfo")
    public Object updateInfo(@RequestParam(required = false ,value = "imageUrl") String imageUrl,@RequestParam(required = false ,value ="nickName") String nickName){
        weappLoginService.updateInfo(getLoginUser().getId(),imageUrl,nickName);
        return new OV().success();
    }
      @PostMapping("/update")
    public Object update(@RequestBody WeappUser weappUser){
        weappLoginService.updatedata(weappUser);
        return new OV().success();
    }

    @GetMapping("/getOpenId")
    public Object getOpenId(@RequestParam("code") String code){
        return new OV().success(weappLoginService.getOpenId(code,getRequest()));
    }



    @GetMapping("/sendEmail")
    public Object sendEmail(@RequestParam("mobile") String mobile,@RequestParam("email") String email){
        weappLoginService.sendEmail(mobile,email);
        return new OV().success();
    }



    @GetMapping("/findAllUser")
    public Object findAllUser(@RequestParam("mobile") String mobile){
        return new OV().success(weappLoginService.findAllUser(mobile));
    }



}
