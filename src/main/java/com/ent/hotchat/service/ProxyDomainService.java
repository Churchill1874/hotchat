package com.ent.hotchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ent.hotchat.entity.ProxyDomain;

import java.util.List;

public interface ProxyDomainService extends IService<ProxyDomain> {
    /**
     * 查询匹配的代理域名
     * @param domain
     * @return
     */
    String resolveProxyDomain(String domain);

    /**
     * 根据域名查找代理Id
     * @param domain
     * @return
     */
    Long resolveProxyId(String domain);

    void addDomain(ProxyDomain dto);

    void updateDomain(ProxyDomain dto);

    List<ProxyDomain> queryPage();
}
