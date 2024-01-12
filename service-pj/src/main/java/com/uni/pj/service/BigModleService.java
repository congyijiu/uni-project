package com.uni.pj.service;

/**
 * @author congyijiu
 * @create 2024-01-15-10:47
 */
public interface BigModleService {

    /**
     * 发送问题给大模型
     *
     * @param question 问题
     * @return 大模型返回的答案
     */
    String sendQuestion(String question,Integer userId);
}
