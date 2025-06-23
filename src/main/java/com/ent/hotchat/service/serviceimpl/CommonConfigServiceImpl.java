package com.ent.hotchat.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ent.hotchat.common.exception.DataException;
import com.ent.hotchat.entity.SystemConfig;
import com.ent.hotchat.mapper.CommonConfigMapper;
import com.ent.hotchat.pojo.req.commonconfig.CommonConfigPage;
import com.ent.hotchat.pojo.req.commonconfig.CommonConfigUpdate;
import com.ent.hotchat.pojo.req.commonconfig.CommonconfigAdd;
import com.ent.hotchat.service.CommonConfigService;
import org.springframework.stereotype.Service;

@Service
public class CommonConfigServiceImpl extends ServiceImpl<CommonConfigMapper, SystemConfig>  implements CommonConfigService {
    @Override
    public IPage<SystemConfig> queryPage(CommonConfigPage dto) {
        return null;
    }

    @Override
    public void add(CommonconfigAdd dto) {

    }

    @Override
    public void update(CommonConfigUpdate dto) {

    }

    @Override
    public String getValueByKey(String key) {
        QueryWrapper<SystemConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(SystemConfig::getConfigKey, key);
        SystemConfig config = getOne(queryWrapper);
        if (config != null) {
            return config != null ? config.getConfigValue() : null;
        }
        throw new DataException("请检查该key所属配置是否配置了");

    }
}
