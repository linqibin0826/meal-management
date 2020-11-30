package com.shusi.modules;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.shusi"})
public class MenuManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(MenuManagementApplication.class, args);
    }

}
