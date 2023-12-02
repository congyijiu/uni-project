package com.uni.pj.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.common.enums.AppHttpCodeEnum;
import com.uni.pj.utils.AppJwtUtil;
import com.uni.pj.utils.AppThreadLocalUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author congyijiu
 * @create 2023-11-25-12:51
 */
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class UniInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
        log.info("----------------------------------------");
        log.info("进入拦截器");
        //拦截器取到请求先进行判断，如果是OPTIONS请求，则放行
        if("OPTIONS".equals(request.getMethod().toUpperCase())) {
            System.out.println("Method:OPTIONS");
            return true;
        }
        String token = request.getHeader("token");
        if(token == null || "".equals(token)){
            log.info("token为空");
            ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.TOKEN_REQUIRE);
            String jsonObjectStr = JSONObject.toJSONString(responseResult);
            returnJson(response, jsonObjectStr);
            return false;
        }

        try {
            Claims claimsBody = AppJwtUtil.getClaimsBody(token);

            //是否是过期
            int result = AppJwtUtil.verifyToken(claimsBody);
            if(result == 1 || result  == 2){
                log.info("token过期");
                ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.TOKEN_EXPIRE);
                String jsonObjectStr = JSONObject.toJSONString(responseResult);
                returnJson(response, jsonObjectStr);
                return false;
            }
            Object userId = claimsBody.get("id");
            AppThreadLocalUtil.setAppUserId(Integer.parseInt(userId + ""));
            log.info("userId: " + userId);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.TOKEN_INVALID);
            String jsonObjectStr = JSONObject.toJSONString(responseResult);
            returnJson(response, jsonObjectStr);
            return false;
        }
        log.info("token验证通过");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("----------------------------------------");
    }

    private void returnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            log.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}


