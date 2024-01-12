package com.uni.pj.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.dynamic.pojos.DynamicTypes;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-11-21
 */
public interface DynamicTypesService extends IService<DynamicTypes> {

    ResponseResult adminPageList(Integer page, Integer size);
}
