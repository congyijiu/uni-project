package com.uni.pj.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.mapper.DynamicTypesMapper;
import com.uni.pj.dynamic.pojos.DynamicTypes;
import com.uni.pj.service.DynamicTypesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2023-11-21
 */
@Service
public class DynamicTypesServiceImpl extends ServiceImpl<DynamicTypesMapper, DynamicTypes> implements DynamicTypesService {


    @Override
    public ResponseResult adminPageList(Integer page, Integer size) {
        //1.校验参数
        if (page == null || page < 1) {
            page = 1;
        }
        if (size == null || size < 1) {
            size = 10;
        }
        //2.查询
        Page<DynamicTypes> page1 = new Page<>(page, size);
        Page<DynamicTypes> dynamicTypesPage = page(page1, null);
        //3.返回
        return ResponseResult.okResult(dynamicTypesPage);
    }
}
