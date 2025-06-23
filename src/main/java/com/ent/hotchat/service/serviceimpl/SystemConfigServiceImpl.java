package com.ent.hotchat.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ent.hotchat.entity.SystemConfig;
import com.ent.hotchat.mapper.SystemConfigMapper;
import com.ent.hotchat.service.SystemConfigService;
import org.springframework.stereotype.Service;

@Service
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements SystemConfigService {

    @Override
    public SystemConfig getByKey(String configKey) {
        QueryWrapper<SystemConfig> wrapper=new QueryWrapper<>();
        wrapper.lambda()
                .eq(SystemConfig::getConfigKey,configKey);
        SystemConfig systemConfig = getOne(wrapper);
        return systemConfig;
    }
}
