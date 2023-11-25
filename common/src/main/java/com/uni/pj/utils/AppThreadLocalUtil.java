package com.uni.pj.utils;


import com.uni.pj.pojos.Users;

/**
 * @author congyijiu
 * @create 2023-10-05-17:21
 */
public class AppThreadLocalUtil {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setAppUser(Long userId) {
        threadLocal.set(userId);
    }

    public static Long getAppUser() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
