package com.uni.pj.ws.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2024-01-09-14:39
 */
@Data
@Schema(name = "MsgDetailPageDto", description = "消息详情分页Dto")
public class MsgDetailPageDto {

    /**
     * 接收用户id
     */
    @Schema(description = "接收用户id")
    private Integer acceptUserId;

    /**
     * 页码
     */
    @Schema(description = "页码")
    private Integer index;

    /**
     * 每页大小
     */
    @Schema(description = "每页大小")
    private Integer size;
}
