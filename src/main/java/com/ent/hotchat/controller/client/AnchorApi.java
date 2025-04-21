package com.ent.hotchat.controller.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.pojo.req.anchor.AnchorPage;
import com.ent.hotchat.pojo.resp.anchor.AnchorInfoVO;
import com.ent.hotchat.service.AnchorService;
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
import java.util.Map;

@Slf4j
@RestController
@Api(tags = "主播管理")
@RequestMapping("/client/anchor")
public class AnchorApi {
    @Autowired
    private AnchorService anchorService;

    @PostMapping("/queryAnchorList")
    @ApiOperation(value = "查询主播列表信息",notes="查询主播列表信息")
    public R<List<AnchorInfoVO>> query(){
        List<AnchorInfoVO> list = anchorService.queryPage();
        return R.ok(list);
    }

    @PostMapping("/queryAnchorMap")
    @ApiOperation(value = "查询主播列表(多人用，仅查询头像和昵称)",notes="查询主播列表(多人用，仅查询头像和昵称)")
    public R<Map> queryMap(){
        Map map = anchorService.queryAnchorList();
        return R.ok(map);
    }
}
