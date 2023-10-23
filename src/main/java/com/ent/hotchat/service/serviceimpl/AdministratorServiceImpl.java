package com.ent.hotchat.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ent.hotchat.entity.Administrator;
import com.ent.hotchat.mapper.AdministratorMapper;
import com.ent.hotchat.service.AdministratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 管理员接口
 */
@Slf4j
@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {


    @Override
    public Administrator findByAccount(String account) {
        QueryWrapper<Administrator> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Administrator::getAccount, account);
        return this.getOne(queryWrapper);
    }

    @Override
    public Administrator findByName(String name) {
        QueryWrapper<Administrator> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Administrator::getName,name);
        return this.getOne(queryWrapper);
    }

}
