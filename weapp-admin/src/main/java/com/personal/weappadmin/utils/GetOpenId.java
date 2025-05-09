package com.personal.weappadmin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.personal.weappadmin.exception.MyException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetOpenId {
    private static Logger logger= LoggerFactory.getLogger(GetOpenId.class);

    public static String WIN_XIN_USER_OPEN_ID_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=CODE&grant_type=authorization_code";

    public  Map<String, String> getWxOpenIdByCodeAndAppidAndSecret(String code) {
       Map<String, String> map = new HashMap<>();
//        String requestUrl = WIN_XIN_USER_OPEN_ID_URL.replace("APPID",appId).replace("SECRET",secret).replace("CODE", code);
//        String json = HttpClientUtil.sendGet(requestUrl);
//        logger.error("获取微信openId结果  code：{},appid:{},secret:{},result:{}",code,appId,secret,json);
//        if(StringUtils.isEmpty(json)){
//            throw new MyException("获取微信用户信息失败");
//        }
//        JSONObject jsonObject = JSON.parseObject(json);
//        if(!jsonObject.containsKey("openid")){
//            throw new MyException("获取微信用户信息失败");
//        }
//        map.put("openId", jsonObject.getString("openid"));
//        map.put("unionId", jsonObject.getString("unionid"));
//        map.put("sessionKey", jsonObject.getString("session_key"));
        return map;
    }
}
