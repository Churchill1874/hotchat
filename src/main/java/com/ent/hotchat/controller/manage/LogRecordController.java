package com.ent.hotchat.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.common.annotation.AdminLoginCheck;
import com.ent.hotchat.common.tools.HttpTools;
import com.ent.hotchat.entity.LogRecord;
import com.ent.hotchat.pojo.req.log.LogPageReq;
import com.ent.hotchat.service.LogRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "日志")
@RequestMapping("/manage/logRecord")
public class LogRecordController {

    @Autowired
    private LogRecordService logRecordService;

    @AdminLoginCheck
    @PostMapping("/page")
    @ApiOperation(value = "分页日志", notes = "分页日志")
    public R<IPage<LogRecord>> page(@RequestBody @Valid LogPageReq req) {
        Integer platform = HttpTools.getPlatform();
        if (HttpTools.getPlatform() != 0){
            req.setPlatform(platform);
        }
        return R.ok(logRecordService.page(req));
    }

}
