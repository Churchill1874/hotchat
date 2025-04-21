package com.ent.hotchat.controller.admin;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.pojo.Id;
import com.ent.hotchat.pojo.req.customer.CustomerBaseUpdate;
import com.ent.hotchat.pojo.req.customer.CustomerPage;
import com.ent.hotchat.pojo.req.customer.CustomerPasswordUpdate;
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
@RequestMapping("/admin/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/query")
    @ApiOperation(value = "分页查询客户",notes="分页查询客户")
    public R<IPage<Account>> query(@RequestBody @Valid CustomerPage req){
        IPage<Account> iPage = customerService.queryPage(req);
        return R.ok(iPage);
    }

    @PostMapping("/customerBaseUpdate")
    @ApiOperation(value = "后台编辑客户基本信息",notes="后台编辑客户基本信息")
    public R customerBaseUpdate(@RequestBody @Valid CustomerBaseUpdate req){
        Account account = BeanUtil.toBean(req, Account.class);
        customerService.CustomerUpdate(account);
        return R.ok(null);
    }

    @PostMapping("/customerPasswordUpdate")
    @ApiOperation(value = "后台修改客户密码",notes="后台修改客户密码")
    public R customerPasswordUpdate(@RequestBody @Valid CustomerPasswordUpdate req){
        Account account = BeanUtil.toBean(req, Account.class);
        customerService.customerPasswordUpdate(account);
        return R.ok(null);
    }

    @PostMapping("/getInfoById")
    @ApiOperation(value = "根据ID查询客户",notes="根据ID查询客户")
    public R<Account> getCustomerById(@RequestBody @Valid Id req){
        Account account =  customerService.findById(req.getId());
        return R.ok(account);
    }


}
