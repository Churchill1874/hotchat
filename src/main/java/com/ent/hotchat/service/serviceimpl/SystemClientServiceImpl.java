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
import com.ent.hotchat.mapper.SystemClientMapper;
import com.ent.hotchat.pojo.req.systemclient.SystemClientPage;
import com.ent.hotchat.pojo.req.systemclient.SystemLogin;
import com.ent.hotchat.pojo.resp.anchor.AnchorInfoVO;
import com.ent.hotchat.pojo.resp.proxy.ProxyInfoVO;
import com.ent.hotchat.pojo.resp.systemclient.CaptchaCode;
import com.ent.hotchat.pojo.resp.systemclient.SystemclientVO;
import com.ent.hotchat.pojo.resp.token.LoginToken;
import com.ent.hotchat.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class SystemClientServiceImpl extends ServiceImpl<SystemClientMapper, Account> implements SystemClientService {
    @Autowired
    private EhcacheService ehcacheService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AnchorService anchorService;

    @Autowired
    private ProxyService proxyService;

    @Override
    public IPage<SystemclientVO> queryPage(SystemClientPage dto) {
        IPage<Account> iPage=new Page<>(dto.getPageNum(),dto.getPageSize());
        IPage<SystemclientVO> iPage1=new Page<>(dto.getPageNum(),dto.getPageSize());
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(StringUtils.isNotBlank(dto.getUserName()),Account::getUserName,dto.getUserName())
                .eq(dto.getStatus()!=null,Account::getStatus,dto.getStatus())
                .eq(Account::getRoleType,RoleTypeEnum.ADMIN);
        iPage=page(iPage,queryWrapper);
        List<Account> list=iPage.getRecords();
        List<SystemclientVO> newList=new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return iPage1;
        }
        for(Account account:list){
            SystemclientVO systemclientVO=new SystemclientVO();
            systemclientVO.setUserName(account.getUserName());
            systemclientVO.setNickName(account.getNickName());
            systemclientVO.setStatus(account.getStatus());
            systemclientVO.setCreateTime(account.getCreateTime());
            systemclientVO.setCreateName(account.getCreateName());
            newList.add(systemclientVO);
        }

        iPage1.setPages(iPage.getPages());
        iPage1.setTotal(iPage.getTotal());
        iPage1.setRecords(newList);
        return iPage1;
    }

    @Override
    public void add(Account dto) {
        dto.setStatus(StatusEnum.NORMAL);
        dto.setRoleType(RoleTypeEnum.ADMIN);
        if(StringUtils.isBlank(dto.getSalt())){
            dto.setSalt(GenerateTools.getUUID());
            dto.setPassword(CodeTools.md5AndSalt(dto.getPassword(),dto.getSalt()));
        }
        dto.setCreateTime(LocalDateTime.now());
        if(StringUtils.isBlank(dto.getCreateName())){
            dto.setCreateName(TokenTools.getUserName());
        }
        if(dto.getCreateTime()==null){
            dto.setCreateTime(LocalDateTime.now());
        }
        save(dto);
        if(!"系统初始化".equals(dto.getCreateName())){
            LogTools.addLog("系统用户-新增","新增了一个系统用户"+JSONUtil.toJsonStr(dto),TokenTools.getLoginToken(true));
        }
    }

    @Override
    public void update(Account dto) {
        updateById(dto);

    }

    @Override
    public void updatePassword(Account dto) {
        if(!StringUtils.isNotBlank(dto.getPassword())){
            dto.setSalt(GenerateTools.getUUID());
            dto.setPassword(CodeTools.md5AndSalt(dto.getPassword(),dto.getSalt()));
            updateById(dto);
        }
        updateById(dto);
    }

    @Override
    public Account findByAccount(String userName) {
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Account::getUserName,userName);
        Account account=getOne(queryWrapper);
        return account;
    }

    @Override
    public Account findById(Long id) {
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Account::getId,id);
        Account account=getOne(queryWrapper);
        return account;
    }

    //对比密码正确与否
    private void checkAccountAndPassword(String actualPassword, String passwordReq) {
        if (!actualPassword.equals(passwordReq)) {
            throw new AccountOrPasswordException();
        }
    }

    //判断登录账号与密码是否存在 并正确
    private LoginToken checkLogin(String account, String password) {
        LoginToken loginToken;

        //判断登录账号是否存在系统用户中
        Account customeraccount = findByAccount(account);
        AnchorInfoVO anchor=anchorService.findByAccount(account);
        ProxyInfoVO proxy=proxyService.findByAccount(account);
        if (customeraccount != null) {
            loginToken = new LoginToken();
            loginToken.setId(customeraccount.getId());
            loginToken.setUserName(customeraccount.getUserName());
            loginToken.setNickName(customeraccount.getNickName());
            loginToken.setRoleType(customeraccount.getRoleType());
            loginToken.setContactType(customeraccount.getContactType());
            loginToken.setContact(customeraccount.getContact());
            loginToken.setStatus(customeraccount.getStatus());
            if(customeraccount.getRoleType()==RoleTypeEnum.USER){
                loginToken.setProxyId(customeraccount.getProxyId());
                loginToken.setProxyName(customeraccount.getProxyName());
                loginToken.setProxyDomain(customeraccount.getProxyDomain());
                //客户默认为0
                loginToken.setCommissionRate(BigDecimal.ZERO);
            }
            if(customeraccount.getRoleType()== RoleTypeEnum.PROXY){
                loginToken.setId(proxy.getId());
                //如果是代理就存代理的比例
                loginToken.setCommissionRate(proxy.getCommissionRate());
            }
            if (customeraccount.getRoleType()== RoleTypeEnum.ANCHOR){
                loginToken.setId(anchor.getId());
                loginToken.setAvatar(anchor.getAvatar());
                loginToken.setVoiceSample(anchor.getVoiceSample());
                loginToken.setVoicePriceCny(anchor.getVoicePriceCny());
                loginToken.setVoicePriceUsdt(anchor.getVoicePriceUsdt());
                loginToken.setVideoPriceCny(anchor.getVideoPriceCny());
                loginToken.setVideoPriceUsdt(anchor.getVideoPriceUsdt());
                loginToken.setHeight(anchor.getHeight());
                loginToken.setWeight(anchor.getWeight());
                loginToken.setPersonality(anchor.getPersonality());
                loginToken.setAge(anchor.getAge());
                loginToken.setEducation(anchor.getEducation());
                loginToken.setRegion(anchor.getRegion());
                loginToken.setHobbies(anchor.getHobbies());
                loginToken.setGoodTopics(anchor.getGoodTopics());
                loginToken.setRejectTopics(anchor.getRejectTopics());
                loginToken.setDescription(anchor.getDescription());
                loginToken.setOnlineStatus(anchor.getOnlineStatus());

                //主播的比例
                loginToken.setCommissionRate(anchor.getCommissionRate());
            }
            //管理员默认为0
            loginToken.setCommissionRate(BigDecimal.ZERO);
            //对比登录密码和正确密码
            checkAccountAndPassword(customeraccount.getPassword(), CodeTools.md5AndSalt(password, customeraccount.getSalt()));
            //用户，代理，主播的信息封装到同一个token返回
            return loginToken;
        }
        throw new AccountOrPasswordException();
     }

    @Override
    public LoginToken login(SystemLogin dto) {
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
        //校验用户名密码是否正确
        LoginToken loginToken=checkLogin(dto.getUserName(),dto.getPassword());
        loginToken.setUserName(dto.getUserName());
        //修改最后登录时间
        customerService.updateLoginTime(dto.getUserName());
        //最后登录时间存入token
        loginToken.setLastLoginTime(customerService.getLastLoginTime(dto.getUserName()));
        //生成tokenid并存入token实体类中
        loginToken.setTokenId(GenerateTools.createTokenId());
        ehcacheService.adminTokenCache().put(loginToken.getTokenId(),loginToken);
        //清空此次登录验证码
        captchaCodeSet.remove(dto.getVerificationCode());
        ehcacheService.captchaCodeCache().put(SystemConstant.CAPTCHA_CODE,captchaCodeSet);
        return loginToken;
    }

    @Override
    public synchronized CaptchaCode getCaptchaCode() {
        String codeImageStream = ehcacheService.getVC( 30, "每3秒超过30次点击验证码");
        CaptchaCode captchaCode = new CaptchaCode();
        captchaCode.setCaptchaImage("data:image/png;base64," + codeImageStream);
        return captchaCode;
    }

    @Override
    public void logout() {
        LoginToken loginToken = TokenTools.getLoginToken(false);
        String tokenId = loginToken.getTokenId();
        if (!StringUtils.isBlank(tokenId) && loginToken!=null) {
            ehcacheService.adminTokenCache().remove(tokenId);
        }
    }
}
