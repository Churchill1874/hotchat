package com.ent.hotchat.common.aspect;

import com.ent.hotchat.common.constant.enums.AdminRoleEnum;
import com.ent.hotchat.common.exception.AuthException;
import com.ent.hotchat.pojo.manage.vo.Token;
import com.ent.hotchat.tools.TokenTools;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class SuperAdminCheck {

    //定位切面的目标 是一个注解
    @Pointcut("@annotation(com.ent.hotchat.common.annotation.SuperAdminLoginCheck)")
    public void superAdminLoginCheck() {

    }

    @Before("superAdminLoginCheck()")
    public void beforeCut(JoinPoint joinPoint) {
        Token token = TokenTools.getToken();
        if (token.getRoleType() != AdminRoleEnum.SUPER_ADMIN) {
            log.error("校验异常,账号{}无超级管理权限", token.getAccount() + token.getName());
            throw new AuthException();
        }
    }


}
