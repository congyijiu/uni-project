package com.uni.pj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author congyijiu
 * @create 2023-11-21-10:55
 */
@EnableAsync
@SpringBootApplication
@MapperScan("com.uni.pj.mapper")
@CrossOrigin(origins = "*", maxAge = 3600)
public class uniPjApplication {
    public static void main(String[] args) {
        SpringApplication.run(uniPjApplication.class, args);
    }
}
