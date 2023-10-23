package com.ent.hotchat.service;

import org.springframework.cache.Cache;

/**
 * 缓存服务
 */
public interface EhcacheService {

    /**
     * 获取token缓存容器
     *
     * @return
     */
    Cache getTokenCache();

    /**
     * 获取3秒锁缓存容器
     *
     * @return
     */
    Cache get3SecondLockCache();

    /**
     * 获取图形验证码缓存容器
     *
     * @return
     */
    Cache captchaCache();

    /**
     * 获取短信缓存容器
     *
     * @return
     */
    Cache getSmsVerificationCodeCache();

    /**
     * 黑名单缓存容器
     *
     * @return
     */
    Cache getBlacklistCache();

    /**
     * 校验ip 3秒内频繁点击超过指定次数
     *
     * @param limitCount
     * @return
     */
    boolean checkIp3SecondsClick(Integer limitCount, String remarks);

}
