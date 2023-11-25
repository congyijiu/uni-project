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

    ResponseResult login(UserLoginDto userLoginDto);

    ResponseResult register(UserRegisterDto userRegisterDto);
}
