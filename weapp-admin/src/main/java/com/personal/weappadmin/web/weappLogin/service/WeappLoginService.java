package com.personal.weappadmin.web.weappLogin.service;/*
 *Created by LLP on 2024年01月04
 */

import com.alibaba.fastjson.JSONObject;
import com.personal.weappadmin.constant.Constants;
import com.personal.weappadmin.core.service.CrudService;
import com.personal.weappadmin.exception.MyException;
import com.personal.weappadmin.utils.GetOpenId;
import com.personal.weappadmin.utils.GmailSender;
import com.personal.weappadmin.utils.JwtUtils;
import com.personal.weappadmin.web.weappLogin.model.WeappUser;
import com.personal.weappadmin.web.weappLogin.repo.WeappUserRepo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author author
 * @ClassName UserService
 * @Description TODO
 * @time 2024/1/4 14:48
 **/
@Service
public class WeappLoginService extends CrudService<WeappUserRepo, WeappUser,Long> {
    @Autowired
    GetOpenId getOpenId;
    @Value("${static.domain}")
    private String domain;
    @Autowired
    GmailSender gmailSender;


    private static final String LOGIN_STATE="1";
    private static Logger logger= LoggerFactory.getLogger(WeappLoginService.class);
    private static SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");




    public Object login(String mobile,String password ,HttpServletRequest request){
        JSONObject res=new JSONObject();
        if(StringUtils.isEmpty(mobile)){
            throw new MyException("手机号不能为空");
        }
        if(StringUtils.isEmpty(password)){
            throw new MyException("密码不能为空");
        }
        if (!CheckMobilePhoneNum(mobile)){
            throw new MyException("手机号格式不正确");
        }
        if (password.length()<6){
            throw new MyException("密码长度不能低于6位");
        }

        WeappUser weappUser=curdRepo.getByMobile(mobile);
        if(weappUser==null){
            throw new MyException("用户未注册");
        }
        if(!password.equals(weappUser.getPassword())){
            throw new MyException("账户名或密码错误");
        }
        res.put("token", JwtUtils.weappgenerateToken(weappUser));
        res.put("userInfo", weappUser);
        request.getSession().setAttribute(Constants.SESSION_USER,weappUser);
        return res;
    }
    public Object register(String mobile,String password,String nickName,String imageUrl){
        if(StringUtils.isEmpty(mobile)){
            throw new MyException("手机号不能为空");
        }
        if(StringUtils.isEmpty(password)){
            throw new MyException("密码不能为空");
        }
        if(StringUtils.isEmpty(nickName)){
            throw new MyException("昵称不能为空");
        }
        if(StringUtils.isEmpty(imageUrl)){
            throw new MyException("头像不能为空");
        }
        if (!CheckMobilePhoneNum(mobile)){
            throw new MyException("手机号格式不正确");
        }
        if (password.length()<6){
            throw new MyException("密码长度不能低于6位");
        }
        JSONObject res=new JSONObject();
        WeappUser weappUser=curdRepo.getByMobile(mobile);
        if(weappUser!=null){
            throw new MyException("该用户已存在");
        }
        weappUser=new WeappUser();
        weappUser.setMobile(mobile);
        weappUser.setNickName(nickName);
        weappUser.setImageUrl(imageUrl);
        weappUser.setPassword(password);
        curdRepo.add(weappUser);
        return res;
    }

    public void loginout(HttpServletRequest request){
        request.getSession().removeAttribute(Constants.SESSION_USER);
        request.getSession().invalidate();
    }

    public Object getLoginUserInfo(Long id){
        JSONObject result=new JSONObject();
        result.put("userInfo",curdRepo.get(id));
        return result;
    }

    public void updateInfo(Long userId,String imageUrl,String nickName){
        curdRepo.updateInfo(userId,imageUrl,nickName);
    }
    public void updatedata(WeappUser weappUser){
        curdRepo.update(weappUser);
    }
    public boolean CheckMobilePhoneNum(String phoneNum) {
        String regex = "^(1[3-9]\\d{9}$)";
        if (phoneNum.length() == 11) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phoneNum);
            if (m.matches()) {
                return true;
            }
        }
        return false;
    }
    public Object getOpenId(String code,HttpServletRequest request){
        JSONObject result=new JSONObject();
        Map<String, String> map= getOpenId.getWxOpenIdByCodeAndAppidAndSecret(code);
        WeappUser weappUser=curdRepo.getByOpenId(map.get("openId"));
        if(weappUser==null){
            weappUser=new WeappUser();
            weappUser.setImageUrl(domain+"touxiang.jpg");
            weappUser.setNickName("用户"+System.currentTimeMillis());
            curdRepo.add(weappUser);
        }
        result.put("openId",map.get("openId"));
        result.put("token", JwtUtils.weappgenerateToken(weappUser));
        request.getSession().setAttribute(Constants.SESSION_USER,weappUser);
        return result;
    }
    public void sendEmail(String mobile,String email){
        WeappUser weappUser=curdRepo.getByMobile(mobile);
        if(weappUser==null){
            throw new MyException("用户信息不匹配");
        }
        if(!email.equals(weappUser.getMail())){
            throw new MyException("用户信息不匹配");
        }
        gmailSender.sendEmail(weappUser.getMail(),weappUser.getPassword());
    }
    public Object findAllUser(String mobile){
      return curdRepo.findAllUser(mobile);
    }
}
