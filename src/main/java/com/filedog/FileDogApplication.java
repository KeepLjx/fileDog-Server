package com.filedog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FileDogApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileDogApplication.class, args);
    }

}
