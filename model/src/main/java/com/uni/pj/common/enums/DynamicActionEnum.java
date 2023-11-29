package com.uni.pj.common.enums;

/**
 * @author congyijiu
 * @create 2023-11-28-11:24
 */
public enum DynamicActionEnum {

        LIKE(1,"点赞"),
        FAVORITE(2,"收藏"),
        Comment(3,"取消点赞");

        int code;
        String desc;

        DynamicActionEnum(int code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode(){
            return code;
        }

        public String getDesc(){
            return desc;
        }
}
