package com.ent.hotchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ent.hotchat.entity.Administrator;

/**
 * 管理员接口
 */
public interface AdministratorService extends IService<Administrator> {

    /**
     * 根据账号查询管理员
     *
     * @param account
     * @return
     */
    Administrator findByAccount(String account);

    /**
     * 根据名称查找管理员
     *
     * @param name
     * @return
     */
    Administrator findByName(String name);

}