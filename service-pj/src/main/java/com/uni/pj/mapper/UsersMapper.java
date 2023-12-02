package com.uni.pj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uni.pj.dynamic.dtos.PageDto;
import com.uni.pj.dynamic.vos.PageVo;
import com.uni.pj.users.dtos.UserFollowsPageDto;
import com.uni.pj.users.pojo.Users;
import com.uni.pj.users.vos.UserFollowsPageVos;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2023-11-21
 */
public interface UsersMapper extends BaseMapper<Users> {

    /**
     * 自定义sql分页
     * @param page
     * @return
     */
    IPage<UserFollowsPageVos> selectUserFollowsPage(@Param("page") IPage<UserFollowsPageVos> page, @Param("pageDto")UserFollowsPageDto userFollowsPageDto);

}
