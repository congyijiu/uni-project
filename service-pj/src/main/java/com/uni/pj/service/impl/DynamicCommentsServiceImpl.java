package com.uni.pj.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.common.enums.AppHttpCodeEnum;
import com.uni.pj.dynamic.dtos.AdminDynamicCommentsPageDto;
import com.uni.pj.dynamic.dtos.DynamicCommentsAddDto;
import com.uni.pj.dynamic.dtos.DynamicCommentsPageDto;
import com.uni.pj.dynamic.vos.AdminDynamicCommentDetailVo;
import com.uni.pj.dynamic.vos.AdminDynamicCommentPageVo;
import com.uni.pj.dynamic.vos.AdminDynamicDetailVo;
import com.uni.pj.mapper.DynamicCommentsMapper;
import com.uni.pj.dynamic.pojos.DynamicComments;
import com.uni.pj.users.pojo.Users;
import com.uni.pj.service.DynamicCommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uni.pj.service.UsersService;
import com.uni.pj.utils.AppThreadLocalUtil;
import com.uni.pj.dynamic.vos.DynamicCommentPageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2023-11-26
 */
@Service
@Slf4j
public class DynamicCommentsServiceImpl extends ServiceImpl<DynamicCommentsMapper, DynamicComments> implements DynamicCommentsService {


    @Autowired
    private UsersService usersService;


    /**
     * 添加动态评论
     *
     * @param dynamicCommentsAddDto
     * @return
     */
    @Override
    public ResponseResult addDynamicComments(DynamicCommentsAddDto dynamicCommentsAddDto) {

        log.info("添加动态评论");

        //1.校验参数
        if (dynamicCommentsAddDto == null) {
            log.info("添加动态评论失败");
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //1.1校验评论内容
        if (dynamicCommentsAddDto.getContent() == null || dynamicCommentsAddDto.getContent().length() < 1) {
            log.info("添加动态评论失败");
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.获取回复用户信息
        Users replyUser = null;
        //2.1.回复二级评论下的某个评论
        if (dynamicCommentsAddDto.getToCommentId() != null) {
            DynamicComments comments = this.getById(dynamicCommentsAddDto.getToCommentId());
            Integer replyUserId = comments.getUserId();
            replyUser = usersService.getById(replyUserId);
        }
        //2.2.回复某个一级评论
        else if (dynamicCommentsAddDto.getTopCommentId() != null) {
            DynamicComments comments = this.getById(dynamicCommentsAddDto.getTopCommentId());
            Integer replyUserId = comments.getUserId();
            replyUser = usersService.getById(replyUserId);
        }


        //3.添加评论
        DynamicComments dynamicComments = new DynamicComments();
        BeanUtils.copyProperties(dynamicCommentsAddDto, dynamicComments);
        dynamicComments.setUserId(AppThreadLocalUtil.getAppUserId());
        //3.2.如果该评论回复的是某个用户,则需要设置回复用户信息
        if (replyUser != null) {
            dynamicComments.setReplyUserId(replyUser.getId());
            dynamicComments.setReplyUsername(replyUser.getUsername());
        }
        this.save(dynamicComments);


        log.info("添加动态评论成功");

        //4.更新顶层评论的回复数
        if (dynamicCommentsAddDto.getTopCommentId() != null) {
            //更新顶层评论的回复数
            this.updateReplyCount(dynamicCommentsAddDto.getTopCommentId());
        }

        return ResponseResult.okResult(dynamicComments);
    }


    /**
     * 分页查询动态评论
     *
     * @param dynamicCommentsPageDto
     * @return
     */
    @Override
    public ResponseResult DynamicCommentsPage(DynamicCommentsPageDto dynamicCommentsPageDto) {

        log.info("分页查询动态评论");

        //1.校验参数
        if (dynamicCommentsPageDto == null) {
            log.info("分页查询动态评论失败");
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.判断是查询动态评论还是动态评论的回复
        if (dynamicCommentsPageDto.getType() == 1) {
            //2.1查询动态评论
            return this.dcPage(dynamicCommentsPageDto);
        } else if (dynamicCommentsPageDto.getType() == 2) {
            //2.2查询动态评论的回复
            return this.dcrPage(dynamicCommentsPageDto);
        }

        log.info("分页查询动态评论失败");
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
    }


    /**
     * 查询动态评论的回复
     *
     * @param dynamicCommentsPageDto
     * @return
     */
    private ResponseResult dcrPage(DynamicCommentsPageDto dynamicCommentsPageDto) {

        log.info("查询动态评论的回复");

        //1.校验分页参数
        if (dynamicCommentsPageDto.getPageIndex() == null || dynamicCommentsPageDto.getPageIndex() < 1) {
            dynamicCommentsPageDto.setPageIndex(1);
        }
        if (dynamicCommentsPageDto.getPageSize() == null || dynamicCommentsPageDto.getPageSize() < 1) {
            dynamicCommentsPageDto.setPageSize(10);
        }

        Integer pageSize = dynamicCommentsPageDto.getPageSize();
        Integer pageIndex = dynamicCommentsPageDto.getPageIndex();
        Integer dynamicId = dynamicCommentsPageDto.getDynamicId();

        log.info("pageIndex:{},pageSize:{},dynamicId:{}", pageIndex, pageSize, dynamicId);

        //2.查询动态评论的回复
//        Page<DynamicComments> commentsPage = new Page<>(pageIndex, pageSize);
//        LambdaQueryWrapper<DynamicComments> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(DynamicComments::getDynamicId,dynamicCommentsPageDto.getDynamicId())
//               .eq(DynamicComments::getTopCommentId,dynamicCommentsPageDto.getToCommentId())
//               .eq(DynamicComments::getToCommentId,dynamicCommentsPageDto.getToCommentId())
//               .orderByDesc(DynamicComments::getCommentDate);
//        Page<DynamicComments> page = this.page(commentsPage, wrapper);

        Page<DynamicCommentPageVo> page = new Page<>(pageIndex, pageSize);

        //3.获取用户id
        Integer userId = AppThreadLocalUtil.getAppUserId();

        baseMapper.dcrPage(page, dynamicCommentsPageDto, userId);

        log.info("查询动态评论的回复成功");

        return ResponseResult.okResult(page);
    }

    /**
     * 查询动态评论
     *
     * @param dynamicCommentsPageDto
     * @return
     */
    private ResponseResult dcPage(DynamicCommentsPageDto dynamicCommentsPageDto) {

        log.info("查询动态评论");

        //1.校验分页参数
        if (dynamicCommentsPageDto.getPageIndex() == null || dynamicCommentsPageDto.getPageIndex() < 1) {
            dynamicCommentsPageDto.setPageIndex(1);
        }
        if (dynamicCommentsPageDto.getPageSize() == null || dynamicCommentsPageDto.getPageSize() < 1) {
            dynamicCommentsPageDto.setPageSize(10);
        }
        Integer pageSize = dynamicCommentsPageDto.getPageSize();
        Integer pageIndex = dynamicCommentsPageDto.getPageIndex();
        Integer dynamicId = dynamicCommentsPageDto.getDynamicId();

        log.info("pageIndex:{},pageSize:{},dynamicId:{}", pageIndex, pageSize, dynamicId);

        //2.查询动态评论
//        Page<DynamicComments> commentsPage = new Page<>(pageIndex, pageSize);
//        LambdaQueryWrapper<DynamicComments> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(DynamicComments::getDynamicId,dynamicCommentsPageDto.getDynamicId())
//               .isNull(DynamicComments::getTopCommentId)
//               .orderByDesc(DynamicComments::getCommentDate);
//        Page<DynamicComments> page = this.page(commentsPage, wrapper);

        Page<DynamicCommentPageVo> page = new Page<>(pageIndex, pageSize);
        //3.获取用户id
        Integer userId = AppThreadLocalUtil.getAppUserId();
        baseMapper.dcPage(page, dynamicCommentsPageDto, userId);

        log.info("查询动态评论成功");

        return ResponseResult.okResult(page);
    }


    @Async
    public void updateReplyCount(Integer topCommentId) {
        DynamicComments byId = this.getById(topCommentId);
        if (byId != null) {
            byId.setReplyCount(byId.getReplyCount() + 1);
            this.updateById(byId);
        }
    }

    /**
     * 后台分页查询动态评论
     *
     * @param page
     * @param size
     * @param pageDto
     * @return
     */
    @Override
    public ResponseResult adminPageList(Integer page, Integer size, AdminDynamicCommentsPageDto pageDto) {
        //1.校验参数
        if (page == null || page < 1) {
            page = 1;
        }
        if (size == null || size < 1) {
            size = 10;
        }
        //2.查询
        Page<AdminDynamicCommentPageVo> page1 = new Page<>(page, size);
        Page<AdminDynamicCommentPageVo> dynamicCommentsPage = baseMapper.adminPageList(page1, pageDto);
        //3.返回
        return ResponseResult.okResult(dynamicCommentsPage);
    }

    @Override
    public ResponseResult adminDetail(Long id) {
        //1.校验参数
        if (id == null || id < 1) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.查询
        AdminDynamicCommentDetailVo dynamicCommentDetailVo = baseMapper.selectDetailById(id);
        return ResponseResult.okResult(dynamicCommentDetailVo);
    }

}
