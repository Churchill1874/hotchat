package com.ent.hotchat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ent.hotchat.entity.SystemConfig;
import com.ent.hotchat.pojo.req.commonconfig.CommonConfigPage;
import com.ent.hotchat.pojo.req.commonconfig.CommonConfigUpdate;
import com.ent.hotchat.pojo.req.commonconfig.CommonconfigAdd;

public interface CommonConfigService extends IService<SystemConfig> {
    IPage<SystemConfig> queryPage(CommonConfigPage dto);

    void add(CommonconfigAdd dto);

    void update(CommonConfigUpdate dto);

    String getValueByKey(String key);
}
