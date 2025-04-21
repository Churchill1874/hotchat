package com.ent.hotchat.controller.admin;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.pojo.req.systemclient.*;
import com.ent.hotchat.pojo.resp.systemclient.CaptchaCode;
import com.ent.hotchat.pojo.resp.systemclient.SystemclientVO;
import com.ent.hotchat.pojo.resp.token.LoginToken;
import com.ent.hotchat.service.SystemClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(tags="系统管理")
@RequestMapping("/admin")
public class SystemClientController {

    @Autowired
    private SystemClientService systemClientService;

    @PostMapping("/getCaptchaCode")
    @ApiOperation(value = "获取验证码",notes = "获取验证码")
    public R<CaptchaCode> getCaptchaCode(){
        CaptchaCode captchaCode = systemClientService.getCaptchaCode();
        return R.ok(captchaCode);
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录",notes = "登录")
    public R<LoginToken> login(@RequestBody @Valid SystemLogin req){
        LoginToken login = systemClientService.login(req);
        return R.ok(login);
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询系统用户",notes = "分页查询系统用户")
    public R<IPage<SystemclientVO>> query(@RequestBody @Valid SystemClientPage req){
        IPage<SystemclientVO> IPage = systemClientService.queryPage(req);
        return R.ok(IPage);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增系统用户",notes = "新增系统用户")
    public R add(@RequestBody @Valid SystemClientAdd req){
        Account account = BeanUtil.toBean(req, Account.class);
        systemClientService.add(account);
        return R.ok(null);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改系统用户信息",notes = "修改系统用户信息")
    public R update(@RequestBody @Valid SystemClientUpdate req){
        Account account = BeanUtil.toBean(req, Account.class);
        systemClientService.update(account);
        return R.ok(null);
    }

    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改密码",notes = "修改密码")
    public R updatePassword(@RequestBody @Valid SystemPasswordUpdate req){
        Account account = BeanUtil.toBean(req, Account.class);
        systemClientService.updatePassword(account);
        return R.ok(null);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出登录",notes = "退出登录")
    public R logout(){
        systemClientService.logout();
        return R.ok(null);
    }

}
