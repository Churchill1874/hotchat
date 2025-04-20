package com.ent.hotchat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.pojo.req.customer.CustomerBaseUpdate;
import com.ent.hotchat.pojo.req.customer.CustomerPage;
import com.ent.hotchat.pojo.req.customer.CustomerStatusUpdate;
import java.time.LocalDateTime;

public interface CustomerService extends IService<Account> {


    /**
     * 用户注册
     * @param dto
     */
    void register(Account dto);


    /**
     * 分页查询客户信息
     * @param dto
     * @return
     */
    IPage<Account> queryPage(CustomerPage dto);

    /**
     * 根据账号查询客户
     * @param account
     * @return
     */
    Account findByaccount(String account);

    /**
     * 修改客户信息
     * @param dto
     */
    void CustomerUpdate(Account dto);


    void CustomerPasswordUpdate(Account dto);

    /**
     * 修改最后登录时间
     * @param account
     */
    void updateLoginTime(String account);

    /**
     * 获取最后登录时间
     * @param account
     * @return
     */
    LocalDateTime getLastLoginTime(String account);

    void add(Account dto);

    void edit(Account dto);

    Integer count(Long id);

}
