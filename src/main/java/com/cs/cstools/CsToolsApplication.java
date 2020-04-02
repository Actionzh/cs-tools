package com.cs.cstools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cs.cstools.mapper")
public class CsToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsToolsApplication.class, args);
    }

}
