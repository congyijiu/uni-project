package com.uni.pj.dynamic.vos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uni.pj.dynamic.pojos.DynamicComments;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author congyijiu
 * @create 2024-01-12-15:50
 */
@Data
public class AdminDynamicCommentPageVo {

    private Integer commentId;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime commentDate;

    private String dynamicTitle;

    private String commentUserName;

}
