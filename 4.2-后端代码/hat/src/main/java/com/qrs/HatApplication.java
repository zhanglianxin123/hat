package com.qrs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"com.qrs.mapper"})
public class HatApplication {

    public static void main(String[] args) {
        SpringApplication.run(HatApplication.class, args);
    }

}
