package com.ent.hotchat.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.pojo.req.proxy.ProxyAdd;
import com.ent.hotchat.pojo.req.proxy.ProxyPage;
import com.ent.hotchat.pojo.resp.proxy.ProxyInfoVO;
import com.ent.hotchat.service.ProxyService;
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
@Api(tags = "代理管理")
@RequestMapping("/admin/proxy")
public class ProxyController {
    @Autowired
    private ProxyService proxyService;

    @RequestMapping("/query")
    @ApiOperation(value = "分页查询代理",notes="分页查询代理")
    public R<IPage<ProxyInfoVO>> query(@RequestBody @Valid ProxyPage req){
        IPage<ProxyInfoVO> iPage = proxyService.queryPage(req);
        return R.ok(iPage);
    }

    @RequestMapping("/add")
    @ApiOperation(value = "新增代理",notes="新增代理")
    public R add(@RequestBody @Valid ProxyAdd req){
        proxyService.add(req);
        return R.ok(null);
    }
}
