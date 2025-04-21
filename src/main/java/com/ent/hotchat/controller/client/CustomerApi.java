package com.ent.hotchat.controller.client;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.pojo.Id;
import com.ent.hotchat.pojo.req.customer.*;
import com.ent.hotchat.service.CustomerService;
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
@Api(tags = "客户管理")
@RequestMapping("/client/customer")
public class CustomerApi {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public R register(@RequestBody @Valid CustomerRegister req) {
        customerService.register(req);
        return R.ok(null);
    }

    @PostMapping("/customerInfoUpdate")
    @ApiOperation(value = "修改基本信息", notes = "修改基本信息")
    public R customerInfoUpdate(@RequestBody @Valid CustomerInfoUpdate req) {
        Account account = BeanUtil.toBean(req, Account.class);
        customerService.CustomerUpdate(account);
        return R.ok(null);
    }

    @PostMapping("/customerPasswordUpdate")
    @ApiOperation(value = "修改密码", notes = "修改密码")
    public R customerPasswordUpdate(@RequestBody @Valid ClientPasswordUpdate req) {
        customerService.clientPasswordUpdate(req);
        return R.ok(null);
    }

    @PostMapping("/getInfoById")
    @ApiOperation(value = "查询个人信息",notes="查询个人信息")
    public R<Account> getInfoById(@RequestBody @Valid Id req){
        Account account =  customerService.findById(req.getId());
        return R.ok(account);
    }
}
