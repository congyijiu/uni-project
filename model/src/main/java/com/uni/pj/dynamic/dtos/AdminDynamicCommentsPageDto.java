package com.uni.pj.dynamic.dtos;

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

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
