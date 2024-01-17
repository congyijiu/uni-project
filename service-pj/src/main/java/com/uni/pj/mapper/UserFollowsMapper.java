package com.uni.pj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uni.pj.users.pojo.UserFollows;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2023-11-28
 */
@Mapper
public interface UserFollowsMapper extends BaseMapper<UserFollows> {

    void autoFollowBot(Integer id);
}
