package com.xgb.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.xgb.boot")
@MapperScan("com.xgb.boot.mapper")
public class HorseSsmBootApplication {
}
