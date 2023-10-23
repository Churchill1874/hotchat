package com.ent.hotchat.tools;

import com.ent.hotchat.common.exception.TokenException;
import com.ent.hotchat.pojo.manage.vo.Token;
import com.ent.hotchat.service.EhcacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * token工具类
 */
@Component
public class TokenTools {

    private static EhcacheService ehcacheService;

    //因为static修饰成员变量不支持自动注入 所以以下面方式给静态成员注入
    @Autowired
    public void setEhcacheService(EhcacheService ehcacheService) {
        TokenTools.ehcacheService = ehcacheService;
    }

    /**
     * 获取用户token
     *
     * @return
     */
    public static Token getToken() {
        Token token = ehcacheService.getTokenCache().get(HttpTools.getHeaderToken(), Token.class);
        if (token == null) {
            throw new TokenException();
        }
        return token;
    }

    /**
     * 获取账号可以为空
     * @return
     */
    public static String getAccountMayNull(){
        Token token = ehcacheService.getTokenCache().get(HttpTools.getHeaderToken(), Token.class);
        if (token != null){
            return token.getAccount();
        }
        return null;
    }

}
