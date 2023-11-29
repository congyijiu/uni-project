package com.uni.pj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.common.enums.AppHttpCodeEnum;
import com.uni.pj.common.enums.DynamicActionEnum;
import com.uni.pj.dtos.PageDto;
import com.uni.pj.mapper.DynamicMapper;
import com.uni.pj.pojos.Dynamic;
import com.uni.pj.pojos.UserDynamicActions;
import com.uni.pj.pojos.UserFollows;
import com.uni.pj.service.DynamicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uni.pj.service.UserFollowsService;
import com.uni.pj.utils.AppThreadLocalUtil;
import com.uni.pj.vos.DynamicDetailVo;
import com.uni.pj.vos.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-11-21
 */
@Service
@Slf4j
public class DynamicServiceImpl extends ServiceImpl<DynamicMapper, Dynamic> implements DynamicService {


    @Autowired
    private UserDynamicActionsServiceImpl userDynamicActionsService;

    @Autowired
    private UserFollowsService userFollowsService;


    /**
     * 分页查询
     * @param pageDto
     * @return
     */
    @Override
    public ResponseResult mypage(PageDto pageDto) {

        log.info("分页查询动态");

        //判断参数是否为空
        if(pageDto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if(pageDto.getPageIndex() == null || pageDto.getPageSize() == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        //获取pageDto参数
        Integer pageIndex = pageDto.getPageIndex();
        Integer pageSize = pageDto.getPageSize();
        Integer type = pageDto.getType();
        String keyWord = pageDto.getKeyWord();
        log.info("pageIndex:{},pageSize:{},type:{},keyWord:{}",pageIndex,pageSize,type,keyWord);

        //如果type==1，查询所有动态
        if(type == 1 || type == 0){
            pageDto.setType(null);
        }

        //创建分页对象
        Page<PageVo> page = new Page<>(pageIndex, pageSize);

        //获取当前用户id，查询当前用户是否点赞
        Integer userId = AppThreadLocalUtil.getAppUserId();

        //执行分页查询
        IPage<PageVo> dynamicPage = baseMapper.selectMyPage(page,pageDto,userId);


        log.info("分页查询动态成功");
        log.info("--------------------------------------");

        return ResponseResult.okResult(dynamicPage);
    }

    @Override
    public ResponseResult detail(Long id) {

        //1判断参数是否为空
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        log.info("查询动态详情");

        //2执行查询
        Dynamic dynamic = this.getById(id);

        //3判断查询结果是否为空
        if (dynamic == null) {
            log.info("查询动态详情失败");
            log.info("--------------------------------------");
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }

        //4.获取当前用户id，查询当前用户是否点赞,收藏，关注
        Integer userId = AppThreadLocalUtil.getAppUserId();
        UserDynamicActions actions = userDynamicActionsService.selectactions(id, userId);
        UserFollows userFollows = userFollowsService.selectFollows(dynamic.getUserId(),userId);

        //将查询结果封装到vo中
        DynamicDetailVo dynamicDetailVo = new DynamicDetailVo();
        BeanUtils.copyProperties(dynamic,dynamicDetailVo);
        dynamicDetailVo.setImageUrlList(dynamic.getImageUrl());
        if(actions != null){
            dynamicDetailVo.setIsLike(actions.getIslike());
            dynamicDetailVo.setIsFavorite(actions.getIsfavorite());
        }
        if(userFollows != null){
            dynamicDetailVo.setIsFollow(1);
        }




        log.info("查询动态详情成功");
        log.info("--------------------------------------");

        return ResponseResult.okResult(dynamicDetailVo);
    }


    /**
     * 异步更新动态点赞数，收藏数，评论数
     * @param
     * @return
     */
    @Async
    @Override
    public void updateDynamicCount(Integer dynamicId, Integer type, Integer count){
        Dynamic dynamic = this.getById(dynamicId);
        if(type == DynamicActionEnum.LIKE.getCode()){
            dynamic.setLikeCount(dynamic.getLikeCount()+count);
        }else if(type == DynamicActionEnum.FAVORITE.getCode()){
            dynamic.setFavoriteCount(dynamic.getFavoriteCount()+count);
        }else if(type == DynamicActionEnum.Comment.getCode()){
            dynamic.setCommentCount(dynamic.getCommentCount()+count);
        }
        this.updateById(dynamic);
    }
}
