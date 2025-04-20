package com.ent.hotchat.service.serviceimpl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.ent.hotchat.common.constant.SystemConstant;
import com.ent.hotchat.pojo.resp.token.LoginToken;
import com.ent.hotchat.service.EhcacheService;
import com.ent.hotchat.common.exception.DataException;
import com.ent.hotchat.common.tools.GenerateTools;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 该类对ehcache.xml配置文件里面已经配置的缓存容器进行实现获取，方便使用
 */
@Slf4j
@Service
public class EhcacheServiceImpl implements EhcacheService {
    //验证码
    final String CAPTCHA_CODE = "captcha_code";
    //管理员登录token
    final String ADMIN_TOKEN = "admin_token";

    final String ANCHOR_LIST = "anchor_list";
    //管理员登录token
    final String ANCHOR_MAP = "anchor_map";

    @Autowired
    private CacheManager cacheManager;


    @Override
    public Cache<String, Set<String>> captchaCodeCache() {
        return cacheManager.getCache(CAPTCHA_CODE, String.class, (Class<Set<String>>) (Class<?>) Set.class);
    }


    @Override
    public String getVC( Integer limitCount, String remarks) {
        //添加频繁点击校验 3秒内点击超过30次 检查警告日志 如果该ip已经存在警告则拉黑 不存在则新加警告日志
        //todo 待更新新校验黑名单
        //this.checkIp3SecondsClick(limitCount, remarks);

        //获取验证码
        String codeImageStream = null;
        String code = null;
        try {
            code = GenerateTools.getCaptchaText(5);
            codeImageStream = GenerateTools.getCaptchaImage(code);
        } catch (IOException e) {
            log.error("生成验证码异常:{}", e.getMessage());
            throw new DataException(e.getMessage());
        }


        Set<String> set = captchaCodeCache().get(SystemConstant.CAPTCHA_CODE);
        if (CollectionUtils.isEmpty(set)){
            set = new HashSet<>();
        }
        set.add(code);

        captchaCodeCache().put(SystemConstant.CAPTCHA_CODE, set);
        return codeImageStream;
    }


    @Override
    public Cache<String, LoginToken> adminTokenCache() {
        return cacheManager.getCache(ADMIN_TOKEN, String.class, LoginToken.class);
    }

    @Override
    public Cache<String, List> anchorListCache() {
        return cacheManager.getCache(ANCHOR_LIST, String.class, List.class);
    }

    @Override
    public Cache<String, Map> anchorMapCache() {
        return cacheManager.getCache(ANCHOR_MAP, String.class, Map.class);
    }


}
