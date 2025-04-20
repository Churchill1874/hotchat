package com.ent.hotchat.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.pojo.req.anchor.AnchorAdd;
import com.ent.hotchat.pojo.req.anchor.AnchorPage;
import com.ent.hotchat.pojo.resp.anchor.AnchorInfoVO;
import com.ent.hotchat.service.AnchorService;
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
@Api(tags = "主播管理")
@RequestMapping("/admin/anchor")
public class AnchorController {
    @Autowired
    private AnchorService anchorService;

    @RequestMapping("/queryAllList")
    @ApiOperation(value = "后台分页查询主播",notes="后台分页查询主播")
    public R<IPage<AnchorInfoVO>> query(@RequestBody @Valid AnchorPage req){
        IPage<AnchorInfoVO> iPage = anchorService.queryAnchorPage(req);
        return R.ok(iPage);
    }

    @RequestMapping("/add")
    @ApiOperation(value = "新增代理",notes="新增代理")
    public R add(@RequestBody @Valid AnchorAdd req){
        anchorService.add(req);
        return R.ok(null);
    }
}
