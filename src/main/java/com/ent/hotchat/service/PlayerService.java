package com.ent.hotchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ent.hotchat.entity.Player;

/**
 * 玩家接口
 */
public interface PlayerService extends IService<Player> {

    /**
     * 校验登录信息,并返回查到的用户信息
     * @param account
     * @param password
     */
    Player checkLogin(String account, String password);

    /**
     * 根据账号手机号查玩家信息
     * @param account
     * @param phone
     */
    Player findByAccountOrPhone(String account, String phone);

    /**
     * 根据账号密码查找用户
     * @param account
     * @param password
     * @return
     */
    Player findByAccountAndPassword(String account, String password);

    /**
     * 根据手机号和密码查询用户
     * @param password
     * @param phone
     * @return
     */
    Player findByPhoneAndPassword(String phone, String password);

}
