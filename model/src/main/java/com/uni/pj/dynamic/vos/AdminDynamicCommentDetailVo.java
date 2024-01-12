package com.uni.pj.dynamic.vos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author congyijiu
 * @create 2024-01-12-16:26
 */
@Data
public class AdminDynamicCommentDetailVo {

    private Integer commentId;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime commentDate;

    private String dynamicTitle;

    private String commentUserName;

}
