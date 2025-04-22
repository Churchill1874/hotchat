package com.ent.hotchat.common.tools;

import com.ent.hotchat.service.ProxyDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 日志工具
 */

@Component
public class DomainTools {

    private static ProxyDomainService proxyDomainService;

    //因为static修饰成员变量不支持自动注入 所以以下面方式给静态成员注入
    @Autowired
    public void ProxyDomainService(ProxyDomainService proxyDomainService) {
        DomainTools.proxyDomainService = proxyDomainService;
    }

    public static String resolveDomain(String domain) {
        return proxyDomainService.resolveProxyDomain(domain);
    }

    public static Long resolveId(String domain) {
        return proxyDomainService.resolveProxyId(domain);
    }
}



