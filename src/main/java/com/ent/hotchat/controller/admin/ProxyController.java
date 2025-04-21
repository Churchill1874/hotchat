package com.ent.hotchat.controller.admin;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.pojo.Id;
import com.ent.hotchat.pojo.req.proxy.ProxyAdd;
import com.ent.hotchat.pojo.req.proxy.ProxyBaseUpdate;
import com.ent.hotchat.pojo.req.proxy.ProxyPage;
import com.ent.hotchat.pojo.req.systemclient.SystemPasswordUpdate;
import com.ent.hotchat.pojo.resp.proxy.ProxyInfoVO;
import com.ent.hotchat.service.ProxyService;
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
@Api(tags = "代理管理")
@RequestMapping("/admin/proxy")
public class ProxyController {
    @Autowired
    private ProxyService proxyService;

    @Autowired
    private SystemClientService systemClientService;

    @PostMapping("/query")
    @ApiOperation(value = "分页查询代理",notes="分页查询代理")
    public R<IPage<ProxyInfoVO>> query(@RequestBody @Valid ProxyPage req){
        IPage<ProxyInfoVO> iPage = proxyService.queryPage(req);
        return R.ok(iPage);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增代理",notes="新增代理")
    public R add(@RequestBody @Valid ProxyAdd req){
        proxyService.add(req);
        return R.ok(null);
    }

    @PostMapping("/proxyBaseUpdate")
    @ApiOperation(value = "修改代理基本信息",notes = "修改代理基本信息")
    public R proxyBaseUpdate(@RequestBody @Valid ProxyBaseUpdate req){
        proxyService.baseUpdate(req);
        return R.ok(null);
    }

    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改密码",notes = "修改密码")
    public R updatePassword(@RequestBody @Valid SystemPasswordUpdate req){
        Account account = BeanUtil.toBean(req, Account.class);
        systemClientService.updatePassword(account);
        return R.ok(null);
    }

    @PostMapping("/getProxyById")
    @ApiOperation(value = "根据Id查询代理信息",notes = "根据Id查询代理信息")
    public R<ProxyInfoVO> getProxyById(@RequestBody @Valid Id req){
        ProxyInfoVO proxyInfoVO=proxyService.findById(req.getId());
        return R.ok(proxyInfoVO);
    }
}
