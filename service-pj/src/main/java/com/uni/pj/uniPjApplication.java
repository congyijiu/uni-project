package com.uni.pj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author congyijiu
 * @create 2023-11-21-10:55
 */
@EnableAsync
@SpringBootApplication
@MapperScan(basePackages = "com.uni.pj.mapper")
public class uniPjApplication {
    public static void main(String[] args) {
        SpringApplication.run(uniPjApplication.class, args);
    }
}
