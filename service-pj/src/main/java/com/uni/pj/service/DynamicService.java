package com.uni.pj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.dynamic.dtos.AdminDynamicListDto;
import com.uni.pj.dynamic.dtos.DynamicPublishDto;
import com.uni.pj.dynamic.dtos.FavoritePageDto;
import com.uni.pj.dynamic.dtos.PageDto;
import com.uni.pj.dynamic.pojos.Dynamic;
import org.springframework.scheduling.annotation.Async;

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

    @Async
    void updateDynamicCount(Integer dynamicId, Integer type, Integer count);

    ResponseResult publish(DynamicPublishDto publishDto);

    ResponseResult favoritePage(FavoritePageDto pageDto);

    /**
     * 管理界面获取动态列表
     * @return
     */
    ResponseResult getDynamicList();

    ResponseResult getAdminDynamicList(Integer page, Integer size, AdminDynamicListDto adminDynamicListDto);

    ResponseResult adminDetail(Long id);

    ResponseResult top10();
}
