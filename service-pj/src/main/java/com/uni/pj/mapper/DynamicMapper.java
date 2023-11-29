package com.uni.pj.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.uni.pj.dtos.PageDto;
import com.uni.pj.pojos.Dynamic;
import com.uni.pj.vos.PageVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2023-11-21
 */
public interface DynamicMapper extends BaseMapper<Dynamic> {
    /**
     * 自定义sql分页
     * @param page
     * @return
     */
    IPage<PageVo> selectMyPage(@Param("page") IPage<PageVo> page, @Param("pageDto") PageDto pageDto, @Param("userId") Integer userId);

}
