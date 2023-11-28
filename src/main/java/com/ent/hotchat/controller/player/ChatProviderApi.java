package com.ent.hotchat.controller.player;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.entity.ChatProvider;
import com.ent.hotchat.pojo.PageReq;
import com.ent.hotchat.service.ChatProviderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "陪聊员")
@RequestMapping("/chatProvider")
public class ChatProviderApi {

    @Autowired
    private ChatProviderService chatProviderService;

    @PostMapping("/page")
    @ApiOperation(value = "分页查询陪聊员", notes = "分页查询陪聊员")
    public R<IPage<ChatProvider>> page(@RequestBody PageReq req) {
        IPage<ChatProvider> iPage = chatProviderService.page(req.getPageNum(), req.getPageSize());
        return R.ok(iPage);
    }

}
