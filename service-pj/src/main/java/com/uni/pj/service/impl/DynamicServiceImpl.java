package com.uni.pj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.dtos.PageDto;
import com.uni.pj.mapper.DynamicMapper;
import com.uni.pj.pojos.Dynamic;
import com.uni.pj.service.DynamicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uni.pj.vos.DynamicDetailVo;
import com.uni.pj.vos.PageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-11-21
 */
@Service
public class DynamicServiceImpl extends ServiceImpl<DynamicMapper, Dynamic> implements DynamicService {




    /**
     * 分页查询
     * @param pageDto
     * @return
     */
    @Override
    public ResponseResult mypage(PageDto pageDto) {

        //判断参数是否为空
        if(pageDto == null){
            return ResponseResult.errorResult(400,"参数不能为空");
        }
        if(pageDto.getPageIndex() == null || pageDto.getPageSize() == null){
            return ResponseResult.errorResult(400,"分页参数不能为空");
        }
        //获取pageDto参数
        Integer pageIndex = pageDto.getPageIndex();
        Integer pageSize = pageDto.getPageSize();
        Integer type = pageDto.getType();
        String keyWord = pageDto.getKeyWord();

        //如果type==1，查询所有动态
        if(type == 1){
            type = null;
        }

        //创建分页对象
        Page<PageVo> page = new Page<>(pageIndex, pageSize);


        //执行分页查询
        IPage<PageVo> dynamicPage = baseMapper.selectMyPage(page,keyWord,type);

        return ResponseResult.okResult(dynamicPage);
    }

    @Override
    public ResponseResult detail(Long id) {
        Dynamic dynamic = this.getById(id);
        DynamicDetailVo dynamicDetailVo = new DynamicDetailVo();
        BeanUtils.copyProperties(dynamic,dynamicDetailVo);
        dynamicDetailVo.setImageUrlList(dynamic.getImageUrl());
        //todo:查询评论数，点赞数，收藏数

//        List<Dynamic> list = this.list();
//        for (Dynamic dynamic : list) {
//            String imageUrl = dynamic.getImageUrl();
//            String[] split = imageUrl.split(",");
//            String newImageUrl = "";
//            for (String s : split) {
//                int startIndex = s.lastIndexOf("/") + 1;
//                int endIndex = s.indexOf("!", startIndex);
//                String result = s.substring(startIndex, endIndex);
//                String newUrl = "https://sns-img-hw.xhscdn.com/"+result+"?imageView2/2/w/1080/format/webp";
//                newImageUrl += newUrl + ",";
//            }
//            if (newImageUrl.endsWith(",")) {
//                newImageUrl = newImageUrl.substring(0, newImageUrl.length() - 1);
//            }else {
//                newImageUrl = newImageUrl.substring(0, newImageUrl.length());
//            }
//            dynamic.setImageUrl(newImageUrl);
//            this.updateById(dynamic);
//        }




        return ResponseResult.okResult(dynamicDetailVo);
    }
}
