package com.personal.weappadmin.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class AuthConfig implements WebMvcConfigurer {
    @Bean
    public MyInterceptor authInterceptor() {
        return new MyInterceptor();
    }

//    @Autowired
//    MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/weapp/login/login")
                .excludePathPatterns("/weapp/login/add")
                .excludePathPatterns("/weapp/login/register")
                .excludePathPatterns("/weapp/login/sendEmail")
                .excludePathPatterns("/user/addUser")
                .excludePathPatterns("/uploadFile/upload")
                .excludePathPatterns("/bookStock/upload")
                .excludePathPatterns("/uploadFile/signpdf")
                .excludePathPatterns("/weapp/login/loginByOpenId")
                .excludePathPatterns("/weapp/login/hasLoginByOpenId")
                .excludePathPatterns("/weapp/login/getOpenId")
                .excludePathPatterns("/login")
                .excludePathPatterns("/upload/**");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper=new UrlPathHelper();
        urlPathHelper.setAlwaysUseFullPath(true);
        configurer.setUrlPathHelper(urlPathHelper);
    }
}
