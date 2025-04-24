package com.ent.hotchat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.entity.Proxy;
import com.ent.hotchat.pojo.req.customer.CustomerByProxyPage;
import com.ent.hotchat.pojo.req.customer.CustomerPage;
import com.ent.hotchat.pojo.req.proxy.ProxyAdd;
import com.ent.hotchat.pojo.req.proxy.ProxyBaseUpdate;
import com.ent.hotchat.pojo.req.proxy.ProxyPage;
import com.ent.hotchat.pojo.resp.proxy.ProxyInfoVO;

public interface ProxyService extends IService<Proxy> {

    /**
     * 分页查询代理信息
     * @param dto
     * @return
     */
    IPage<ProxyInfoVO> queryPage(ProxyPage dto);

    IPage<Account> queryByProxyId(CustomerByProxyPage dto);

    /**
     * 新增代理
     * @param dto
     */
    void add(ProxyAdd dto);

    /**
     * 修改代理基本信息
     * @param dto
     */
    void baseUpdate(ProxyBaseUpdate dto);

    /**
     * 根据账号查询代理信息
     * @param account
     * @return
     */
    ProxyInfoVO findByAccount(String account);

    /**
     * 根据id查询代理信息
     * @param id
     * @return
     */
    ProxyInfoVO findById(Long id);

}
