package com.ent.hotchat.service.serviceimpl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ent.hotchat.common.constant.SystemConstant;
import com.ent.hotchat.common.constant.enums.RoleTypeEnum;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.common.exception.AccountOrPasswordException;
import com.ent.hotchat.common.exception.DataException;
import com.ent.hotchat.common.tools.*;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.mapper.CustomerMapper;
import com.ent.hotchat.pojo.req.customer.ClientPasswordUpdate;
import com.ent.hotchat.pojo.req.customer.CustomerByProxyPage;
import com.ent.hotchat.pojo.req.customer.CustomerPage;
import com.ent.hotchat.pojo.req.customer.CustomerRegister;
import com.ent.hotchat.service.CustomerService;
import com.ent.hotchat.service.EhcacheService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Account> implements CustomerService {

    @Autowired
    private EhcacheService ehcacheService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(CustomerRegister dto) {
        if(!dto.getPassword().equals(dto.getConfirmPassword())){
            throw new DataException("确认密码和密码不一致");
        }
        Set<String> captchaCodeSet = ehcacheService.captchaCodeCache().get(SystemConstant.CAPTCHA_CODE);
        if(CollectionUtils.isEmpty(captchaCodeSet)){
            throw new DataException("验证码有误或已过期");
        }
        boolean confirm = false;
        for(String key:captchaCodeSet){
            if(key.equalsIgnoreCase(dto.getVerificationCode())){
                confirm=true;
                captchaCodeSet.remove(key);
                break;
            }
        }
        if(!confirm){
            throw new DataException("验证码错误");
        }
        Account account=new Account();
        account.setUserName(dto.getUserName());
        account.setNickName(dto.getNickName());
        account.setContactType(dto.getContactType());
        account.setContact(dto.getContact());
        account.setRoleType(RoleTypeEnum.USER);
        account.setStatus(StatusEnum.NORMAL);
        account.setSalt(GenerateTools.getUUID());
        account.setPassword(CodeTools.md5AndSalt(dto.getPassword(),account.getSalt()));
        account.setCreateTime(LocalDateTime.now());
        account.setCreateName(dto.getUserName());
        // 1. 获取当前访问域名
        String domain = HttpTools.extractDomainFromRequest();

        // 2. 解析域名归属
        String proxyDomain = DomainTools.resolveDomain(domain);
        if(StringUtils.isNotBlank(proxyDomain)){
            account.setProxyId(DomainTools.resolveId(domain));
        }
        account.setProxyDomain(domain);
        save(account);
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
    public IPage<Account> queryPage(CustomerPage dto) {
        IPage<Account> iPage=new Page<>(dto.getPageNum(),dto.getPageSize());
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(StringUtils.isNotBlank(dto.getUserName()),Account::getUserName,dto.getUserName())
                .eq(dto.getStatus()!=null,Account::getStatus,dto.getStatus())
                .eq(Account::getRoleType,RoleTypeEnum.USER);
        iPage=page(iPage,queryWrapper);
        return iPage;
    }

    @Override
    public IPage<Account> queryByProxyId(CustomerByProxyPage dto) {
        IPage<Account> iPage=new Page<>(dto.getPageNum(),dto.getPageSize());
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Account::getProxyId,dto.getProxyId())
                .eq(Account::getRoleType,RoleTypeEnum.USER);
        iPage=page(iPage,queryWrapper);
        return iPage;
    }


    @Override
    public Account findByAccount(String account) {
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Account::getUserName,account);
        Account account1=getOne(queryWrapper);
        if(account1!=null){
            return account1;
        }
        throw new DataException("该账号不存在");
    }

    @Override
    public Account findById(Long id) {
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Account::getId,id);
        Account account1=getOne(queryWrapper);
        return account1;
    }

    @Override
    public void CustomerUpdate(Account dto) {
        updateById(dto);
        LogTools.addLog("客户管理-修改客户信息","修改了客户信息"+ JSONUtil.toJsonStr(dto), TokenTools.getLoginToken(true));
    }

    @Override
    public void customerPasswordUpdate(Account dto) {
        if(!StringUtils.isNotBlank(dto.getPassword())){
            dto.setSalt(GenerateTools.getUUID());
            dto.setPassword(CodeTools.md5AndSalt(dto.getPassword(),dto.getSalt()));
            updateById(dto);
        }
        updateById(dto);
        LogTools.addLog("客户管理-后台修改密码","管理员修改了密码"+ JSONUtil.toJsonStr(dto), TokenTools.getLoginToken(true));
    }

    @Override
    public void clientPasswordUpdate(ClientPasswordUpdate dto) {
        Account account = findById(dto.getId());
        //数据库中旧密码的加密
        String actualPassword = account.getPassword();
        //传入的旧密码的加密
        String passwordReq = CodeTools.md5AndSalt(dto.getOldPassword(), account.getSalt());
        if (!actualPassword.equals(passwordReq)) {
            throw new AccountOrPasswordException("旧密码有误");
        }
        if(!dto.getPassword().equals(dto.getConfirmPassword())){
            throw new DataException("新密码和确认密码不一致");
        }
        account.setPassword(CodeTools.md5AndSalt(dto.getPassword(),account.getSalt()));
        updateById(account);
        LogTools.addLog("客户管理-用户修改密码","用户修改了密码"+ JSONUtil.toJsonStr(dto), TokenTools.getLoginToken(true));
    }

    @Override
    public void updateLoginTime(String account) {
        Account account1 = findByAccount(account);
        account1.setLastLoginTime(LocalDateTime.now());
        updateById(account1);
    }

    @Override
    public LocalDateTime getLastLoginTime(String account) {
        return findByAccount(account).getLastLoginTime();
    }

    @Override
    public String findByIds(List<Long> anchorIds) {
        QueryWrapper<Account> wrapper=new QueryWrapper<>();
        wrapper.lambda().select(Account::getNickName)
                .in(Account::getId, anchorIds);
        List<Object> objects = listObjs(wrapper);
        List<String> names=objects.stream().map(Object::toString).collect(Collectors.toList());
        return String.join(",",names);
    }

}
