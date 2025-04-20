package com.ent.hotchat.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.entity.ProxyDomain;
import com.ent.hotchat.mapper.ProxyDomainMapper;
import com.ent.hotchat.service.ProxyDomainService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ProxyDomainServiceImpl extends ServiceImpl<ProxyDomainMapper, ProxyDomain> implements ProxyDomainService {
    @Override
    public String resolveProxyDomain(String domain) {
        if (StringUtils.isBlank(domain)) {
            return null;
        }
        QueryWrapper<ProxyDomain> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(ProxyDomain::getDomainName,domain)
                .eq(ProxyDomain::getStatus, StatusEnum.NORMAL);
        ProxyDomain proxyDomain=getOne(queryWrapper);
        if(proxyDomain!=null){
            return proxyDomain.getDomainName();
        }
        return null;
    }

    @Override
    public Long resolveProxyId(String domain) {
        if (StringUtils.isBlank(domain)) {
            return null;
        }
        QueryWrapper<ProxyDomain> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(ProxyDomain::getDomainName,domain)
                .eq(ProxyDomain::getStatus, StatusEnum.NORMAL);
        ProxyDomain proxyDomain=getOne(queryWrapper);
        if(proxyDomain!=null){
            return proxyDomain.getAccountId();
        }
        return null;
    }
}
