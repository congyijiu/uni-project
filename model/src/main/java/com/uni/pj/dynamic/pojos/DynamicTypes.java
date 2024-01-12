package com.uni.pj.dynamic.pojos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2023-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dynamic_types")
public class DynamicTypes implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型唯一标识符，自动增加
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型名称，最大长度50
     */
    private String typeName;

    /**
     * 类型描述，最大长度255
     */
    private String description;

    /**
     * 是否为默认类型，布尔值，0表示否，1表示是
     */
    private Integer isDefault;

    /**
     * 类型状态，0表示未激活，1表示激活
     */
    private Integer status;

    /**
     * 排序顺序，整数值
     */
    private Integer sortOrder;

    /**
     * 类型创建时间，默认为当前时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;


}
