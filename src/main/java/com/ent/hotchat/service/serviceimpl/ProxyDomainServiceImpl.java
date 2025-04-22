package com.ent.hotchat.service.serviceimpl;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.common.tools.TokenTools;
import com.ent.hotchat.entity.ProxyDomain;
import com.ent.hotchat.mapper.ProxyDomainMapper;
import com.ent.hotchat.service.CustomerService;
import com.ent.hotchat.service.ProxyDomainService;
import com.ent.hotchat.service.ProxyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProxyDomainServiceImpl extends ServiceImpl<ProxyDomainMapper, ProxyDomain> implements ProxyDomainService {
    @Autowired
    private CustomerService customerService;

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

    @Override
    public void addDomain(ProxyDomain dto) {
        dto.setAccountId(customerService.findByAccount(dto.getAccount()).getId());
        dto.setCreateName(TokenTools.getUserName());
        dto.setCreateTime(LocalDateTime.now());
        save(dto);
    }

    @Override
    public void updateDomain(ProxyDomain dto) {
        dto.setAccountId(customerService.findByAccount(dto.getAccount()).getId());
        dto.setStatus(dto.getStatus());
        dto.setDomainName(dto.getDomainName());
        updateById(dto);
    }

    @Override
    public List<ProxyDomain> queryPage() {
        QueryWrapper<ProxyDomain> queryWrapper=new QueryWrapper<>();
        List<ProxyDomain> list = list(queryWrapper);
        return list;
    }
}
