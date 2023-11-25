package com.uni.pj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.dtos.UserLoginDto;
import com.uni.pj.dtos.UserRegisterDto;
import com.uni.pj.mapper.UsersMapper;
import com.uni.pj.pojos.Users;
import com.uni.pj.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uni.pj.utils.AppJwtUtil;
import com.uni.pj.utils.MD5Utils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-11-21
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Override
    public ResponseResult login(UserLoginDto userLoginDto) {
        //1.校验参数
        if (userLoginDto == null || userLoginDto.getUsername() == null || userLoginDto.getPassword() == null) {
            return ResponseResult.errorResult(400,"参数错误");
        }

        //2.根据用户名查询用户
        String username = userLoginDto.getUsername();
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername, username);
        Users users = this.getOne(wrapper);

        //3.判断用户是否存在
        if (users == null) {
            return ResponseResult.errorResult(400,"用户不存在");
        }

        //4.判断密码是否正确
        String encode = MD5Utils.encode(userLoginDto.getPassword());
        userLoginDto.setPassword(encode);
        if (users.getPassword().equals(userLoginDto.getPassword())) {
            String token = AppJwtUtil.getToken(Long.valueOf(users.getId()));
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            map.put("username", users.getUsername());
            return ResponseResult.okResult(map);
        }



        return ResponseResult.errorResult(400,"密码错误");
    }

    @Override
    public ResponseResult register(UserRegisterDto userRegisterDto) {
        //1.校验参数
        if (userRegisterDto == null || userRegisterDto.getUsername() == null || userRegisterDto.getPassword() == null) {
            return ResponseResult.errorResult(400,"参数错误");
        }
        //2.根据用户名查询用户
        String username = userRegisterDto.getUsername();
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername, username);
        Users users = this.getOne(wrapper);
        //3.判断用户是否存在
        if (users != null) {
            return ResponseResult.errorResult(400,"用户已存在");
        }
        //4.判断两次密码是否一致
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getPasswordAgain())) {
            return ResponseResult.errorResult(400,"两次密码不一致");
        }
        //5.md5加密
        String encode = MD5Utils.encode(userRegisterDto.getPassword());
        userRegisterDto.setPassword(encode);

        //4.保存用户
        Users user = new Users();
        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(userRegisterDto.getPassword());
        this.save(user);
        String token = AppJwtUtil.getToken(Long.valueOf(user.getId()));
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("username", user.getUsername());


        return ResponseResult.okResult(map);
    }
}
