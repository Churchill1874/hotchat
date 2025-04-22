package com.ent.hotchat.controller.admin;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.entity.ProxyDomain;
import com.ent.hotchat.pojo.req.proxy.ProxyAdd;
import com.ent.hotchat.pojo.req.proxydomain.ProxyDomainAdd;
import com.ent.hotchat.pojo.req.proxydomain.ProxyDomainUpdate;
import com.ent.hotchat.service.ProxyDomainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "代理域名管理")
@RequestMapping("/admin/proxyDomain")
public class ProxyDomainController {
    @Autowired
    private ProxyDomainService proxyDomainService;

    @PostMapping("/query")
    @ApiOperation(value = "查询所有代理域名",notes="查询所有代理域名")
    public R<List<ProxyDomain>> queryPage(){
        List<ProxyDomain> proxyDomains = proxyDomainService.queryPage();
        return R.ok(proxyDomains);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增代理域名",notes="新增代理域名")
    public R add(@RequestBody @Valid ProxyDomainAdd req){
        ProxyDomain proxyDomain = BeanUtil.toBean(req, ProxyDomain.class);
        proxyDomainService.addDomain(proxyDomain);
        return R.ok(null);
    }

    @PostMapping("/update")
    @ApiOperation(value = "编辑代理域名",notes="编辑代理域名")
    public R update(@RequestBody @Valid ProxyDomainUpdate req){
        ProxyDomain proxyDomain = BeanUtil.toBean(req, ProxyDomain.class);
        proxyDomainService.updateDomain(proxyDomain);
        return R.ok(null);
    }
}
