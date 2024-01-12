package com.uni.pj.dynamic.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author congyijiu
 * @create 2024-01-12-15:40
 */
@Data
public class AdminDynamicCommentsPageDto {

    private String dynamicKeyWord;

    private String commentKeyWord;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

}
