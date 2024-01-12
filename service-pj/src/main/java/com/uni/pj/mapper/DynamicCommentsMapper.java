package com.uni.pj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uni.pj.dynamic.dtos.AdminDynamicCommentsPageDto;
import com.uni.pj.dynamic.dtos.DynamicCommentsPageDto;
import com.uni.pj.dynamic.pojos.DynamicComments;
import com.uni.pj.dynamic.vos.AdminDynamicCommentDetailVo;
import com.uni.pj.dynamic.vos.AdminDynamicCommentPageVo;
import com.uni.pj.dynamic.vos.DynamicCommentPageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2023-11-26
 */
@Mapper
public interface DynamicCommentsMapper extends BaseMapper<DynamicComments> {

    /**
     * 动态评论分页
     * @param page
     * @return
     */
    IPage<DynamicCommentPageVo> dcPage(@Param("page") IPage<DynamicCommentPageVo> page,@Param("dcPageDto") DynamicCommentsPageDto dynamicCommentsPageDt, @Param("userId") Integer userId);

    /**
     * 评论回复分页
     * @param page
     * @return
     */
    IPage<DynamicCommentPageVo> dcrPage(@Param("page") IPage<DynamicCommentPageVo> page,@Param("dcPageDto") DynamicCommentsPageDto dynamicCommentsPageDto, @Param("userId") Integer userId);

    Page<AdminDynamicCommentPageVo> adminPageList(Page<AdminDynamicCommentPageVo> page1, AdminDynamicCommentsPageDto pageDto);

    AdminDynamicCommentDetailVo selectDetailById(Long id);
}
