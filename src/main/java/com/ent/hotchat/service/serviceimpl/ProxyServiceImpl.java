package com.ent.hotchat.service.serviceimpl;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ent.hotchat.common.constant.enums.RoleTypeEnum;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.common.tools.CodeTools;
import com.ent.hotchat.common.tools.GenerateTools;
import com.ent.hotchat.common.tools.LogTools;
import com.ent.hotchat.common.tools.TokenTools;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.entity.Proxy;
import com.ent.hotchat.mapper.ProxyMapper;
import com.ent.hotchat.pojo.req.proxy.ProxyAdd;
import com.ent.hotchat.pojo.req.proxy.ProxyBaseUpdate;
import com.ent.hotchat.pojo.req.proxy.ProxyPage;
import com.ent.hotchat.pojo.resp.proxy.ProxyInfoVO;
import com.ent.hotchat.service.CustomerService;
import com.ent.hotchat.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
public class ProxyServiceImpl extends ServiceImpl<ProxyMapper, Proxy> implements ProxyService {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProxyMapper proxyMapper;

    @Override
    public IPage<ProxyInfoVO> queryPage(ProxyPage dto) {
        IPage<ProxyInfoVO> iPage=new Page<>(dto.getPageNum(),dto.getPageSize());
        IPage<ProxyInfoVO> iPage1=proxyMapper.selectProxyPage(iPage,RoleTypeEnum.PROXY,dto.getUserName(),dto.getStatus());
        return iPage1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ProxyAdd dto) {
        Account account=new Account();
        Proxy proxy=new Proxy();
        account.setUserName(dto.getUserName());
        account.setNickName(dto.getNickName());
        account.setSalt(GenerateTools.getUUID());
        account.setPassword(CodeTools.md5AndSalt(dto.getPassword(),account.getSalt()));
        account.setContactType(dto.getContactType());
        account.setContact(dto.getContact());
        account.setRoleType(RoleTypeEnum.PROXY);
        account.setStatus(StatusEnum.NORMAL);
        account.setCreateName(TokenTools.getUserName());
        account.setCreateTime(LocalDateTime.now());
        customerService.add(account);
        proxy.setCommissionRate(dto.getCommissionRate());
        proxy.setProxyId(account.getId());
        proxy.setCreateName(account.getCreateName());
        proxy.setCreateTime(account.getCreateTime());
        save(proxy);
        LogTools.addLog("代理管理-新增代理","新增了一个代理"+ JSONUtil.toJsonStr(dto),TokenTools.getLoginToken(true));

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void baseUpdate(ProxyBaseUpdate dto) {
        Account account=customerService.findById(dto.getId());
        Proxy proxy=new Proxy();
        account.setNickName(dto.getNickName());
        account.setContactType(dto.getContactType());
        account.setContact(dto.getContact());
        account.setStatus(dto.getStatus());
        customerService.edit(account);
        proxy.setCommissionRate(dto.getCommissionRate());
        UpdateWrapper<Proxy> updateWrapper=new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(Proxy::getProxyId,account.getId());
        update(proxy,updateWrapper);
        LogTools.addLog("代理管理-编辑代理","修改了一个代理"+ JSONUtil.toJsonStr(dto),TokenTools.getLoginToken(true));
    }

    @Override
    public ProxyInfoVO findByAccount(String account) {
        ProxyInfoVO proxyInfoVO=proxyMapper.selectProxyByAccount(account,RoleTypeEnum.PROXY);
        return proxyInfoVO;
    }

    @Override
    public ProxyInfoVO findById(Long id) {
        return proxyMapper.selectProxyById(id,RoleTypeEnum.PROXY);
    }
}
