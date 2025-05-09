package com.personal.weappadmin.config.mybatisConfig;

import org.springframework.stereotype.Component;

@Component
public @interface MyBatisRepository {
    String value() default "";
}
