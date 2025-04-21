package com.ent.hotchat.controller.admin;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.pojo.Id;
import com.ent.hotchat.pojo.req.anchor.AnchorAdd;
import com.ent.hotchat.pojo.req.anchor.AnchorBaseUpdate;
import com.ent.hotchat.pojo.req.anchor.AnchorPage;
import com.ent.hotchat.pojo.req.systemclient.SystemPasswordUpdate;
import com.ent.hotchat.pojo.resp.anchor.AnchorInfoVO;
import com.ent.hotchat.service.AnchorService;
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
@Api(tags = "主播管理")
@RequestMapping("/admin/anchor")
public class AnchorController {
    @Autowired
    private AnchorService anchorService;

    @Autowired
    private SystemClientService systemClientService;

    @PostMapping("/queryAllList")
    @ApiOperation(value = "后台分页查询主播",notes="后台分页查询主播")
    public R<IPage<AnchorInfoVO>> query(@RequestBody  AnchorPage req){
        IPage<AnchorInfoVO> iPage = anchorService.queryAnchorPage(req);
        return R.ok(iPage);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增主播",notes="新增主播")
    public R add(@RequestBody @Valid AnchorAdd req){
        anchorService.add(req);
        return R.ok(null);
    }

    @PostMapping("/anchorInfoUpdate")
    @ApiOperation(value = "修改主播基本信息",notes = "修改主播基本信息")
    public R anchorInfoUpdate(@RequestBody @Valid AnchorBaseUpdate req){
        anchorService.baseupdate(req);
        return R.ok(null);
    }

    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改密码",notes = "修改密码")
    public R updatePassword(@RequestBody @Valid SystemPasswordUpdate req){
        Account account = BeanUtil.toBean(req, Account.class);
        systemClientService.updatePassword(account);
        return R.ok(null);
    }

    @PostMapping("/getAnchorById")
    @ApiOperation(value = "根据Id查询主播信息",notes = "根据Id查询主播信息")
    public R<AnchorInfoVO> getProxyById(@RequestBody @Valid Id req){
        AnchorInfoVO anchorInfoVO=anchorService.findById(req.getId());
        return R.ok(anchorInfoVO);
    }
}
