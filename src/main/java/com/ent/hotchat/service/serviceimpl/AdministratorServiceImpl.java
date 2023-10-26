package com.ent.hotchat.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ent.hotchat.entity.Administrators;
import com.ent.hotchat.mapper.AdministratorMapper;
import com.ent.hotchat.service.AdministratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 管理员接口
 */
@Slf4j
@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrators> implements AdministratorService {


    @Override
    public Administrators findByAccount(String account) {
        QueryWrapper<Administrators> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Administrators::getAccount, account);
        return this.getOne(queryWrapper);
    }

    @Override
    public Administrators findByName(String name) {
        QueryWrapper<Administrators> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Administrators::getName,name);
        return this.getOne(queryWrapper);
    }

}
