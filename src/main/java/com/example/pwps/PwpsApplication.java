package com.example.pwps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.pwps.mapper")
public class PwpsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PwpsApplication.class, args);
    }

}
