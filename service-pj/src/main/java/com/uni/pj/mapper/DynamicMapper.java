package com.uni.pj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uni.pj.dynamic.dtos.PageDto;
import com.uni.pj.dynamic.pojos.Dynamic;
import com.uni.pj.dynamic.vos.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2023-11-21
 */
@Mapper
public interface DynamicMapper extends BaseMapper<Dynamic> {
    /**
     * 自定义sql分页
     * @param page
     * @return
     */
    IPage<PageVo> selectMyPage(@Param("page") IPage<PageVo> page, @Param("pageDto") PageDto pageDto, @Param("userId") Integer userId);

}
