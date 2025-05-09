package com.personal.weappadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.personal.weappadmin")
@SpringBootApplication(scanBasePackages = {"com.personal.weappadmin"})
public class WeappAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeappAdminApplication.class, args);
    }

}
