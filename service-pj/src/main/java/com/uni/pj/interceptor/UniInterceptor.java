package com.uni.pj.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.common.enums.AppHttpCodeEnum;
import com.uni.pj.utils.AppJwtUtil;
import com.uni.pj.utils.AppThreadLocalUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author congyijiu
 * @create 2023-11-25-12:51
 */
@Slf4j
public class UniInterceptor implements HandlerInterceptor {

    /**
     * 请求处理之前进行调用（Controller方法调用之前）
     * @param request
     * @param response
     * @param handler
     * @return true表示放行，false表示拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("----------------------------------------");
        log.info("进入拦截器");

        //获取请求url
        String requestURI = request.getRequestURI();
        log.info("requestURI: " + requestURI);

        //拦截器取到请求先进行判断，如果是OPTIONS请求，则放行
        if("OPTIONS".equals(request.getMethod().toUpperCase())) {
            System.out.println("Method:OPTIONS");
            return true;
        }
        String token = request.getHeader("token");
        if(token == null || "".equals(token)){
            log.info("token为空");
            //封装返回结果
            ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.TOKEN_REQUIRE);
            String jsonObjectStr = JSONObject.toJSONString(responseResult);
            //返回给前端
            returnJson(response, jsonObjectStr);
            return false;
        }

        try {
            Claims claimsBody = AppJwtUtil.getClaimsBody(token);

            //是否是过期
            int result = AppJwtUtil.verifyToken(claimsBody);
            if(result == 1 || result  == 2){
                log.info("token过期");
                //封装返回结果
                ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.TOKEN_EXPIRE);
                String jsonObjectStr = JSONObject.toJSONString(responseResult);
                //返回给前端
                returnJson(response, jsonObjectStr);
                return false;
            }
            Object userId = claimsBody.get("id");
            AppThreadLocalUtil.setAppUserId(Integer.parseInt(userId + ""));
            log.info("userId: " + userId);

        } catch (Exception e) {
            e.printStackTrace();
            //封装返回结果
            ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.TOKEN_INVALID);
            String jsonObjectStr = JSONObject.toJSONString(responseResult);
            //返回给前端
            returnJson(response, jsonObjectStr);
            return false;
        }
        log.info("token验证通过");
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     * @param request
     * @param response
     * @param handler
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("----------------------------------------");
    }

    /**
     * 返回json数据给前端
     * @param response
     * @param json
     * @throws Exception
     */
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


