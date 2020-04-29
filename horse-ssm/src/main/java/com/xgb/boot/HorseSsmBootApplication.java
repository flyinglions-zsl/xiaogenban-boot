package com.xgb.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.xgb.boot")
@MapperScan("com.xgb.boot.mapper")
public class HorseSsmBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HorseSsmBootApplication.class,args);
    }
}
