package com.ent.hotchat.service.serviceimpl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ent.hotchat.common.constant.enums.RoleTypeEnum;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.common.tools.*;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.mapper.CustomerMapper;
import com.ent.hotchat.pojo.req.customer.CustomerBaseUpdate;
import com.ent.hotchat.pojo.req.customer.CustomerPage;
import com.ent.hotchat.pojo.req.customer.CustomerStatusUpdate;
import com.ent.hotchat.service.CustomerService;
import com.ent.hotchat.service.ProxyDomainService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Account> implements CustomerService {
    @Autowired
    private ProxyDomainService proxyDomainService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(Account dto) {
        dto.setRoleType(RoleTypeEnum.USER);
        dto.setStatus(StatusEnum.NORMAL);
        dto.setSalt(GenerateTools.getUUID());
        dto.setPassword(CodeTools.md5AndSalt(dto.getPassword(),dto.getSalt()));
        dto.setCreateTime(LocalDateTime.now());
        dto.setCreateName(dto.getUserName());
        // 1. 获取当前访问域名
        String domain = HttpTools.extractDomainFromRequest();

        // 2. 解析域名归属
        String proxyDomain = proxyDomainService.resolveProxyDomain(domain);
        if(StringUtils.isNotBlank(proxyDomain)){
            dto.setProxyId(proxyDomainService.resolveProxyId(domain));
        }
        dto.setProxyDomain(domain);
    }


    @Override
    public void add(Account dto) {
        save(dto);
    }

    @Override
    public  void edit(Account dto){
        updateById(dto);
    }

    @Override
    public Integer count(Long id) {

        return null;
    }

    @Override
    public IPage<Account> queryPage(CustomerPage dto) {
        IPage<Account> iPage=new Page<>(dto.getPageNum(),dto.getPageSize());
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(StringUtils.isNotBlank(dto.getUserName()),Account::getUserName,dto.getUserName())
                .eq(dto.getStatus()!=null,Account::getStatus,dto.getStatus());
        return null;
    }

    @Override
    public Account findByaccount(String account) {
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Account::getUserName,account);
        Account account1=getOne(queryWrapper);
        return account1;
    }

    @Override
    public void CustomerUpdate(Account dto) {
        updateById(dto);
        LogTools.addLog("客户管理-修改客户信息","修改了客户信息"+ JSONUtil.toJsonStr(dto), TokenTools.getLoginToken(true));
    }

    @Override
    public void CustomerPasswordUpdate(Account dto) {
        if(!StringUtils.isNotBlank(dto.getPassword())){
            dto.setSalt(GenerateTools.getUUID());
            dto.setPassword(CodeTools.md5AndSalt(dto.getPassword(),dto.getSalt()));
            updateById(dto);
        }
        updateById(dto);
    }

    @Override
    public void updateLoginTime(String account) {
        Account account1 = findByaccount(account);
        account1.setLastLoginTime(LocalDateTime.now());
        save(account1);
    }

    @Override
    public LocalDateTime getLastLoginTime(String account) {
        return findByaccount(account).getLastLoginTime();
    }

}
