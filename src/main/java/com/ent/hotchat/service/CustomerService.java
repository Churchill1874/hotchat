package com.ent.hotchat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.pojo.req.customer.ClientPasswordUpdate;
import com.ent.hotchat.pojo.req.customer.CustomerByProxyPage;
import com.ent.hotchat.pojo.req.customer.CustomerPage;
import com.ent.hotchat.pojo.req.customer.CustomerRegister;

import java.time.LocalDateTime;

public interface CustomerService extends IService<Account> {


    /**
     * 用户注册
     * @param dto
     */
    void register(CustomerRegister dto);


    /**
     * 分页查询客户信息
     * @param dto
     * @return
     */
    IPage<Account> queryPage(CustomerPage dto);

    /**
     * 分页查询代理下线
     * @param dto
     * @return
     */
    IPage<Account> queryByProxyId(CustomerByProxyPage dto);

    /**
     * 根据账号查询客户
     * @param account
     * @return
     */
    Account findByAccount(String account);

    /**
     * 根据Id查询客户信息
     * @param id
     * @return
     */
    Account findById(Long id);

    /**
     * 修改客户信息
     * @param dto
     */
    void CustomerUpdate(Account dto);


    /**
     * 修改密码
     * @param dto
     */
    void customerPasswordUpdate(Account dto);

    /**
     * 用户修改密码
     * @param dto
     */
    void clientPasswordUpdate(ClientPasswordUpdate dto);

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


}
