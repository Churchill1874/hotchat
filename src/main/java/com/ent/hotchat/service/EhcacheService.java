package com.ent.hotchat.service;


import com.ent.hotchat.pojo.resp.token.LoginToken;
import org.ehcache.Cache;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EhcacheService {
    /**
     * 获取验证码缓存容器
     * @return
     */
    Cache<String, Set<String>> captchaCodeCache();

    /**
     * 获取验证码 并设置每3秒的限制请求次数 和提示语
     * @param limitCount
     * @param remarks
     * @return
     */
    String getVC( Integer limitCount, String remarks);

    /**
     * 管理员登录token
     * @return
     */
    Cache<String, LoginToken> adminTokenCache();

    /**
     * 主播列表信息list
     * @return
     */
    Cache<String, List> anchorListCache();

    /**
     * 主播列表信息map
     * @return
     */
    Cache<String, Map> anchorMapCache();
}
