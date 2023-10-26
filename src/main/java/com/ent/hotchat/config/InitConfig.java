package com.ent.hotchat.config;

import com.ent.hotchat.common.constant.enums.AdminRoleEnum;
import com.ent.hotchat.entity.Administrators;
import com.ent.hotchat.service.AdministratorService;
import com.ent.hotchat.tools.CodeTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Slf4j
@Component
public class InitConfig {

    //超管管理员账号
    private static final String SUPER_ADMIN_ACCOUNT = "administrator";

    private static final String PASSWORD = "111111a";

    @Autowired
    private AdministratorService administratorService;

    //获取创建机器人开关
    @Value("${init.create.bot}")
    private boolean createBot;

    /**
     * 项目启动时运行方法
     */
    @PostConstruct
    private void run() {
        log.info("获取创建机器人开关配置:{}", createBot);
        Administrators administrators = administratorService.findByAccount(SUPER_ADMIN_ACCOUNT);
        if (administrators == null){
            administrators = new Administrators();
            administrators.setCreateTime(LocalDateTime.now());
            administrators.setName("超级管理员");
            administrators.setPassword(CodeTools.md5AndSalt(PASSWORD));
            administrators.setAccount(SUPER_ADMIN_ACCOUNT);
            administrators.setRoleType(AdminRoleEnum.SUPER_ADMIN);
            administratorService.save(administrators);
        }
    }

}
