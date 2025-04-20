package com.ent.hotchat.controller.admin;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.pojo.req.systemclient.SystemClientAdd;
import com.ent.hotchat.pojo.req.systemclient.SystemClientPage;
import com.ent.hotchat.pojo.req.systemclient.SystemClientUpdate;
import com.ent.hotchat.pojo.req.systemclient.SystemPasswordUpdate;
import com.ent.hotchat.service.SystemClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/page")
    @ApiOperation(value = "分页查询系统用户",notes = "分页查询系统用户")
    public R<IPage<Account>> query(@RequestBody @Valid SystemClientPage req){
        IPage<Account> IPage = systemClientService.queryPage(req);
        return R.ok(IPage);
    }

    @RequestMapping("/add")
    @ApiOperation(value = "新增系统用户",notes = "新增系统用户")
    public R add(@RequestBody @Valid SystemClientAdd req){
        Account account = BeanUtil.toBean(req, Account.class);
        systemClientService.add(account);
        return R.ok(null);
    }

    @RequestMapping("/update")
    @ApiOperation(value = "新增系统用户",notes = "新增系统用户")
    public R update(@RequestBody @Valid SystemClientUpdate req){
        Account account = BeanUtil.toBean(req, Account.class);
        systemClientService.add(account);
        return R.ok(null);
    }

    @RequestMapping("/updatePassword")
    @ApiOperation(value = "新增系统用户",notes = "新增系统用户")
    public R updatePassword(@RequestBody @Valid SystemPasswordUpdate req){
        Account account = BeanUtil.toBean(req, Account.class);
        systemClientService.add(account);
        return R.ok(null);
    }
}
