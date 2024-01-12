package com.uni.pj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @author congyijiu
 * @create 2023-11-21-10:55
 */
@EnableAsync
@SpringBootApplication
@EnableWebSocket
@MapperScan(basePackages = "com.uni.pj.mapper")
public class uniPjApplication {
    public static void main(String[] args) {
        SpringApplication.run(uniPjApplication.class, args);
    }
}
