package com.uni.pj.utils;


import com.uni.pj.pojos.Users;

/**
 * @author congyijiu
 * @create 2023-10-05-17:21
 */
public class AppThreadLocalUtil {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void setAppUserId(Integer userId) {
        threadLocal.set(userId);
    }

    public static Integer getAppUserId() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
