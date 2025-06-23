package com.ent.hotchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ent.hotchat.entity.SystemConfig;

public interface SystemConfigService extends IService<SystemConfig> {
    SystemConfig getByKey(String configKey);
}
