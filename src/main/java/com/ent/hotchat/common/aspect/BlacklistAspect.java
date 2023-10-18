package com.ent.hotchat.common.aspect;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.ent.hotchat.common.exception.DataException;
import com.ent.hotchat.common.tools.HttpTools;
import com.ent.hotchat.entity.Blacklist;
import com.ent.hotchat.service.BlacklistService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Aspect
@Component
public class BlacklistAspect {

    @Autowired
    private BlacklistService blacklistService;

    @Pointcut("execution(* com.ent.hotchat.controller.manage.*.*(..))")
    public void blacklistPointCut() {
    }

    @Before("blacklistPointCut()")
    public void beforeExecute() {
        String ip = HttpTools.getIp();
        List<Blacklist> list = blacklistService.findByIp(ip);
        if (CollectionUtils.isNotEmpty(list)){
            throw new DataException("ip已被限制,请联系管理员");
        }
    }

}
