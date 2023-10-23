package com.ent.hotchat.controller.manage;

import cn.hutool.Hutool;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.common.annotation.SuperAdminLoginCheck;
import com.ent.hotchat.common.constant.SystemConstant;
import com.ent.hotchat.common.exception.AccountOrPasswordException;
import com.ent.hotchat.entity.Administrator;
import com.ent.hotchat.pojo.manage.req.administrator.AddAdminReq;
import com.ent.hotchat.pojo.manage.req.administrator.LoginReq;
import com.ent.hotchat.pojo.manage.resp.administrator.LoginResp;
import com.ent.hotchat.service.AdministratorService;
import com.ent.hotchat.service.EhcacheService;
import com.ent.hotchat.tools.CodeTools;
import com.ent.hotchat.tools.GenerateTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(tags = "管理员")
@RequestMapping("/manage/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private EhcacheService ehcacheService;

    @PostMapping("/login")
    @ApiModelProperty(value = "登录", notes = "登录")
    public R<LoginResp> login(@RequestBody @Valid LoginReq req) {
        //校验用户是否存在
        Administrator administrator = administratorService.findByAccount(req.getAccount());
        if (administrator == null) {
            throw new AccountOrPasswordException();
        }

        //校验密码是否正确
        if (!CodeTools.md5AndSalt(req.getPassword()).equals(administrator.getPassword())) {
            throw new AccountOrPasswordException();
        }

        //生产token
        String tokenId = GenerateTools.createTokenId(req.getAccount());

        //存入缓存
        Cache tokenCache = ehcacheService.getTokenCache();
        tokenCache.put(SystemConstant.TOKEN_ID, tokenId);

        //返回前端token
        return R.ok(LoginResp.CreateLoginResp(administrator, tokenId));
    }

    @PostMapping("/add")
    @SuperAdminLoginCheck
    @ApiModelProperty(value = "添加管理员", notes = "添加管理员")
    public R register(@RequestBody @Valid AddAdminReq req) {
        //克隆
        Administrator ob = BeanUtil.copyProperties(req,Administrator.class);

        //密码加密
        String password = CodeTools.md5AndSalt(req.getPassword());
        ob.setPassword(password);

        administratorService.save(ob);
        return R.ok(null);
    }

}
