package com.uni.pj.controller.admin;


import com.uni.pj.common.ResponseResult;
import com.uni.pj.common.enums.AppHttpCodeEnum;
import com.uni.pj.service.UsersService;
import com.uni.pj.users.dtos.UserLoginDto;
import com.uni.pj.users.dtos.UserPageDto;
import com.uni.pj.users.pojo.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 后台登录登出
 * </p>
 */
@Tag(name = "后台用户管理接口")
@RestController
@RequestMapping("/admin/system/index")
public class AdminUserController {

    @Autowired
    private UsersService usersService;


    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "登录")
    public ResponseResult login(@RequestBody UserLoginDto userLoginDto) {
        //1.校验参数
        if (userLoginDto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }


        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();
        if("admin".equals(username)){
            return usersService.login(userLoginDto);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/info")
    @Operation(summary = "获取用户信息")
    public ResponseResult info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("name", "admin");
        map.put("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        return ResponseResult.okResult(map);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public ResponseResult logout() {
        return ResponseResult.okResult("success");
    }

    /**
     * 获取用户列表
     */
    @GetMapping("/list/{index}/{size}")
    @Operation(summary = "获取用户列表")
    public ResponseResult list(@PathVariable("index") Integer index, @PathVariable("size") Integer size,
                               @RequestParam String keyword) {
        return usersService.getUserList(index, size, keyword);
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/info/{id}")
    @Operation(summary = "获取用户信息")
    public ResponseResult info(@PathVariable("id") Integer id) {
        return usersService.getUserInfoById(id);
    }

    /**
     * 修改用户信息
     */
    @PutMapping("/info")
    @Operation(summary = "修改用户信息")
    public ResponseResult updateUserInfo(@RequestBody Users users) {
        return usersService.adminupdateUserInfo(users);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除用户")
    public ResponseResult delete(@PathVariable("id") Integer id) {
        return usersService.removeById(id) ? ResponseResult.okResult("删除成功") : ResponseResult.errorResult(5001, "删除失败");
    }

    /**
     * 批量删除用户
     */
    @DeleteMapping("/batchRemove")
    @Operation(summary = "批量删除用户")
    public ResponseResult batchRemove(@RequestBody Integer[] ids) {
        return usersService.removeByIds(Arrays.asList(ids)) ? ResponseResult.okResult("删除成功") : ResponseResult.errorResult(5001, "删除失败");
    }

}