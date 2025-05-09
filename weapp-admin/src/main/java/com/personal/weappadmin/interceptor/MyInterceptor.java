package com.personal.weappadmin.interceptor;

import com.alibaba.fastjson.JSON;
import com.personal.weappadmin.backendapi.user.model.User;
import com.personal.weappadmin.constant.Constants;
import com.personal.weappadmin.exception.AuthorizedException;
import com.personal.weappadmin.exception.OV;
import com.personal.weappadmin.utils.JwtUtils;
import com.personal.weappadmin.web.weappLogin.model.WeappUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class MyInterceptor  extends HandlerInterceptorAdapter {

    public final static String CONTEXT_TOKEN="Authorization";
    //支持的请求头
    private static final String ALLOWED_HEADERS = "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,access_token,accessToken,SOURCE";
    private static final String ALLOWED_METHODS = "POST, GET, OPTIONS, DELETE, PUT";
    private static final String ALLOWED_EXPOSE = "*";
    private static final String SOURCE = "SOURCE";
    private static final String MAX_AGE = "3600";
    private static final Logger logger = LoggerFactory.getLogger(HandlerInterceptorAdapter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", ALLOWED_METHODS);
        response.setHeader("Access-Control-Max-Age", MAX_AGE);
        response.setHeader("Access-Control-Allow-Headers", ALLOWED_HEADERS);
        response.setHeader("Access-Control-Expose-Headers", ALLOWED_EXPOSE);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String method = request.getMethod();
        if (!method.equals("OPTIONS")) {
            if("pm".equals(request.getHeader(SOURCE))){
                String token = request.getHeader(CONTEXT_TOKEN);
                User user=null;
                try{
                    user= JwtUtils.pmUsergetUser(token);
                }catch (Exception e){
                    throw new AuthorizedException("token失效 请重新登录");
                }
                request.getSession().setAttribute(Constants.SESSION_USER_PM,user);
            }else {
                String token = request.getHeader(CONTEXT_TOKEN);
                WeappUser user=null;
                try{
                    user= JwtUtils.weappgetUser(token);
                }catch (Exception e){
                    throw new AuthorizedException("token失效 请重新登录");
                }
                request.getSession().setAttribute(Constants.SESSION_USER,user);
            }

        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
            throws Exception {
        logger.info("==afterCompletion==");
    }
}
