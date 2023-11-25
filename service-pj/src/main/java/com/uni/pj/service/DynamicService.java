package com.uni.pj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.dtos.PageDto;
import com.uni.pj.pojos.Dynamic;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-11-21
 */
public interface DynamicService extends IService<Dynamic> {

    ResponseResult mypage(PageDto pageDto);

    ResponseResult detail(Long id);
}
