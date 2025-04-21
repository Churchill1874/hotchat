package com.ent.hotchat.config;

import com.ent.hotchat.common.constant.enums.RoleTypeEnum;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.common.tools.CodeTools;
import com.ent.hotchat.common.tools.GenerateTools;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.service.SystemClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Slf4j
@Component
public class InitConfig {

    @Autowired
    private SystemClientService systemClientService;



    //超管管理员账号
    private static final String SUPER_ADMIN_ACCOUNT = "admin";

    private static final String PASSWORD = "111111a";

    //获取创建机器人开关
    @Value("${init.create.bot}")
    private boolean createBot;


    /**
     * 项目启动时运行方法
     */
    @PostConstruct
    private void run() {
        Account account = systemClientService.findByAccount(SUPER_ADMIN_ACCOUNT);

        if (account == null){
            account = new Account();
            account.setUserName(SUPER_ADMIN_ACCOUNT);
            account.setNickName("超级管理员");
            account.setStatus(StatusEnum.NORMAL);
            account.setRoleType(RoleTypeEnum.ADMIN);
            String salt = GenerateTools.getUUID();
            account.setSalt(salt);
            account.setCreateTime(LocalDateTime.now());
            account.setCreateName("系统初始化");
            account.setPassword(CodeTools.md5AndSalt(PASSWORD, salt));
            account.setContactType("wechat");
            account.setContact("...");
            systemClientService.add(account);
            log.info("初始化了超级管理员");
        }
    }


}
