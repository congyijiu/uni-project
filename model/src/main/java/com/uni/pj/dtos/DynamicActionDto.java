package com.uni.pj.dtos;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author congyijiu
 * @create 2023-11-28-11:05
 */
@Data
public class DynamicActionDto {
    /**
     * 行为目标的唯一标识符（例如，被点赞或被收藏的动态ID）
     */
    private Integer dynamicId;

    /**
     * 是否点赞,0表示没有点赞,1表示点赞
     */
    private Integer islike;

    /**
     * 是否收藏,0表示没有收藏,1表示收藏
     */
    private Integer isfavorite;
}
