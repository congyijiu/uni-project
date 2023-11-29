package com.uni.pj.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.dtos.UserLoginDto;
import com.uni.pj.dtos.UserRegisterDto;
import com.uni.pj.pojos.Users;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-11-21
 */
public interface UsersService extends IService<Users> {
    /**
     * 登录
     * @param userLoginDto
     * @return
     */
    ResponseResult login(UserLoginDto userLoginDto);

    /**
     * 注册
     * @param userRegisterDto
     * @return
     */
    ResponseResult register(UserRegisterDto userRegisterDto);

    /**
     * 获取当前登录用户信息
     * @return
     */
    ResponseResult getUserInfo();

    /**
     * 更新用户信息
     * @param users
     * @return
     */
    ResponseResult updateUserInfo(Users users);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    ResponseResult getUserInfoById(Integer id);
}
