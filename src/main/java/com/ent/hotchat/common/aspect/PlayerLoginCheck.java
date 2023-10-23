package com.ent.hotchat.common.aspect;

import com.ent.hotchat.tools.TokenTools;
import com.ent.hotchat.pojo.manage.vo.Token;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PlayerLoginCheck {

    //定位切面的目标 是一个注解
    @Pointcut("@annotation(com.ent.hotchat.common.annotation.PlayerLoginCheck)")
    public void playerLoginCheck() {

    }

    @Before("playerLoginCheck()")
    public void beforeCut(JoinPoint joinPoint) {
        Token token = TokenTools.getToken();
/*        if (token.getRole() != RoleEnum.PLAYER.getCode() || token.getStatus() == UserStatusEnum.DISABLE.getCode()) {
            throw new AuthException();
        }*/
    }

/*    @After("loginCheck()")
    public void afterCut(){

    }*/


}
