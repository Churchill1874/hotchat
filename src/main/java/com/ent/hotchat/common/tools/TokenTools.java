package com.ent.hotchat.common.tools;

import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.common.exception.DataException;
import com.ent.hotchat.common.exception.TokenException;
import com.ent.hotchat.pojo.resp.token.LoginToken;
import com.ent.hotchat.service.EhcacheService;
import com.ent.hotchat.common.tools.HttpTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * token工具类
 */
@Slf4j
@Component
public class TokenTools {

    private static EhcacheService ehcacheService;

    //因为static修饰成员变量不支持自动注入 所以以下面方式给静态成员注入
    @Autowired
    public void setEhcacheService(EhcacheService ehcacheService) {
        TokenTools.ehcacheService = ehcacheService;
    }


    /**
     * 获取管理员登录信息
     * @return
     */
    public static LoginToken getLoginToken(boolean needCheck) {
        String headerToken = HttpTools.getHeaderToken();
        if (StringUtils.isBlank(headerToken)){
            //如果要求在请求头里的token-id不能为空 要校验令牌
            if (needCheck){
                throw new TokenException();
            } else {
                return null;
            }
        }

        LoginToken loginToken = ehcacheService.adminTokenCache().get(headerToken);
        if (loginToken == null) {
            throw new TokenException();
        }

        if (loginToken.getStatus() == StatusEnum.DISABLE){
            throw new DataException("账号已禁用,请联系平台管理员");
        }
        return loginToken;
    }


    /**
     * 获取昵称
     * @return
     */
    public static String getNickName () {
        return getLoginToken(true).getNickName();
    }

    /**
     * 获取登录账号
     * @return
     */
    public static String getUserName() {
        return getLoginToken(true).getUserName();
    }

    /**
     * 获取所属代理账号
     * @return
     */
    public static String getProxyAccount() {
        return getLoginToken(true).getProxyName();
    }

}
