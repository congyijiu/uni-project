package com.uni.pj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.common.enums.AppHttpCodeEnum;
import com.uni.pj.users.dtos.UserFollowsPageDto;
import com.uni.pj.users.dtos.UserLoginDto;
import com.uni.pj.users.dtos.UserRegisterDto;
import com.uni.pj.mapper.UsersMapper;
import com.uni.pj.users.pojo.Users;
import com.uni.pj.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uni.pj.users.vos.UserFollowsPageVos;
import com.uni.pj.utils.AppJwtUtil;
import com.uni.pj.utils.AppThreadLocalUtil;
import com.uni.pj.utils.MD5Utils;
import com.uni.pj.users.vos.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
@Slf4j
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Override
    public ResponseResult login(UserLoginDto userLoginDto) {

        log.info("用户登录");

        //1.校验参数
        if (userLoginDto == null || userLoginDto.getUsername() == null || userLoginDto.getPassword() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.根据用户名查询用户
        String username = userLoginDto.getUsername();
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername, username);
        Users users = this.getOne(wrapper);

        //3.判断用户是否存在
        if (users == null) {
            log.info("用户登录失败");
            return ResponseResult.errorResult(AppHttpCodeEnum.AP_USER_DATA_NOT_EXIST);
        }

        //4.判断密码是否正确
        String encode = MD5Utils.encode(userLoginDto.getPassword());
        userLoginDto.setPassword(encode);
        if (users.getPassword().equals(userLoginDto.getPassword())) {
            String token = AppJwtUtil.getToken(Long.valueOf(users.getId()));
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            map.put("username", users.getUsername());

            log.info("用户:{}",map.get("username"));
            log.info("用户登录成功");
            return ResponseResult.okResult(map);
        }

        log.info("用户登录失败");

        return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
    }

    @Override
    public ResponseResult register(UserRegisterDto userRegisterDto) {

        log.info("用户注册");

        //1.校验参数
        if (userRegisterDto == null || userRegisterDto.getUsername() == null || userRegisterDto.getPassword() == null) {
            log.info("用户注册失败");
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.根据用户名查询用户
        String username = userRegisterDto.getUsername();
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername, username);
        Users users = this.getOne(wrapper);

        //3.判断用户是否存在
        if (users != null) {
            log.info("用户注册失败");
            log.info("--------------------------------------");
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST);
        }

        //4.md5加密
        String encode = MD5Utils.encode(userRegisterDto.getPassword());
        userRegisterDto.setPassword(encode);

        //5.保存用户
        Users user = new Users();
        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(userRegisterDto.getPassword());
        this.save(user);
        String token = AppJwtUtil.getToken(Long.valueOf(user.getId()));
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("username", user.getUsername());


        log.info("用户:{}",map.get("username"));
        log.info("用户注册成功");
        log.info("--------------------------------------");

        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult getUserInfo() {

        Integer appUserId = AppThreadLocalUtil.getAppUserId();

        Users users = this.getById(appUserId);

        if(users == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }

        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(users,userInfoVo);

        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateUserInfo(Users users) {
        //1.校验参数
        if (users == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.获取用户id
        Integer appUserId = AppThreadLocalUtil.getAppUserId();
        //3.根据id查询用户
        Users user = this.getById(appUserId);

        if(user == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }

        //4.修改用户信息
        Users users1 = new Users();

        //5.设置用户id
        users1.setId(appUserId);
        //5.1修改用户密码
        if (users.getPassword()!= "" && users.getPassword()!=null){
            String encode = MD5Utils.encode(users.getPassword());
            users1.setPassword(encode);
        }
        //5.2修改用户头像
        if (users.getAvatarUrl() != "" && users.getAvatarUrl() != null){
            users1.setAvatarUrl(user.getAvatarUrl());
        }
        //5.3修改用户性别
        if (users.getGender() != null){
            users1.setGender(users.getGender());
        }
        //5.4修改用户爱好
        if (users.getHobbies() != "" && users.getHobbies() != null){
            users1.setHobbies(users.getHobbies());
        }
        //5.5修改用户电话号码
        if (users.getPhoneNumber() != "" && users.getPhoneNumber() != null){
            users1.setPhoneNumber(users.getPhoneNumber());
        }
        //5.6修改用户个性签名
        if (users.getSignature() != "" && users.getSignature() != null){
            users1.setSignature(users.getSignature());
        }
        //5.7修改用户名
        if (users.getUsername() != "" && users.getUsername() != null){
            Users one = this.getOne(new LambdaQueryWrapper<Users>().eq(Users::getUsername, users.getUsername()));
            if (one != null){
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST);
            }
            users1.setUsername(users.getUsername());
        }
        //5.8修改用户邮箱
        if (users.getEmail() != "" && users.getEmail() != null){
            users1.setEmail(users.getEmail());
        }

        //6.更新用户信息
        this.updateById(users1);

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult getUserInfoById(Integer id) {
        Users users = this.getById(id);
        if(users == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(users,userInfoVo);

        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult getUserFollowsList(UserFollowsPageDto userFollowsPageDto) {
        //1.校验参数
        if (userFollowsPageDto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        Integer pageIndex = userFollowsPageDto.getPageIndex();
        Integer pageSize = userFollowsPageDto.getPageSize();
        if (pageIndex == null || pageSize == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.分页查询用户关注列表
        Page<UserFollowsPageVos> page = new Page<>(pageIndex, pageSize);
        baseMapper.selectUserFollowsPage(page,userFollowsPageDto);

        return ResponseResult.okResult(page);
    }
}
