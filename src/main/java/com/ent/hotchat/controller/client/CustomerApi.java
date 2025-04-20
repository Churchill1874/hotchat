package com.ent.hotchat.controller.client;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.pojo.req.customer.CustomerBaseUpdate;
import com.ent.hotchat.pojo.req.customer.CustomerPage;
import com.ent.hotchat.pojo.req.customer.CustomerPasswordUpdate;
import com.ent.hotchat.pojo.req.customer.CustomerRegister;
import com.ent.hotchat.service.CustomerService;
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
@Api(tags = "客户管理")
@RequestMapping("/client/customer")
public class CustomerApi {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/register")
    @ApiOperation(value = "分页查询客户", notes = "分页查询客户")
    public R register(@RequestBody @Valid CustomerRegister req) {
        Account account = BeanUtil.toBean(req, Account.class);
        customerService.register(account);
        return R.ok(null);
    }

    @RequestMapping("/customerBaseUpdate")
    @ApiOperation(value = "修改基本信息", notes = "修改基本信息")
    public R customerBaseUpdate(@RequestBody @Valid CustomerBaseUpdate req) {
        Account account = BeanUtil.toBean(req, Account.class);
        customerService.CustomerUpdate(account);
        return R.ok(null);
    }

    @RequestMapping("/customerPasswordUpdate")
    @ApiOperation(value = "修改密码", notes = "修改密码")
    public R customerPasswordUpdate(@RequestBody @Valid CustomerPasswordUpdate req) {
        Account account = BeanUtil.toBean(req, Account.class);
        customerService.CustomerPasswordUpdate(account);
        return R.ok(null);
    }
}
