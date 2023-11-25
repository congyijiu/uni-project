package com.uni.pj.interceptor;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.uni.pj.utils.AppJwtUtil;
import com.uni.pj.utils.AppThreadLocalUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author congyijiu
 * @create 2023-11-25-12:51
 */
@Slf4j
public class UniInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
        log.info("进入拦截器");
        String token = request.getHeader("token");
        if(token == null){
            log.info("token为空");
            response.setStatus(401);
            return false;
        }

        try {
            Claims claimsBody = AppJwtUtil.getClaimsBody(token);

            Object userId = claimsBody.get("id");

            AppThreadLocalUtil.setAppUser(Long.parseLong(userId + ""));

            //是否是过期
            int result = AppJwtUtil.verifyToken(claimsBody);
            if(result == 1 || result  == 2){
                response.setStatus(401);
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return true;
    }
}
