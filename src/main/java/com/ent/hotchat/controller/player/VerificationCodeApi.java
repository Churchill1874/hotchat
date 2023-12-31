package com.ent.hotchat.controller.player;

import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.common.constant.enums.LogTypeEnum;
import com.ent.hotchat.common.tools.GenerateTools;
import com.ent.hotchat.common.tools.HttpTools;
import com.ent.hotchat.service.EhcacheService;
import com.ent.hotchat.service.LogRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "验证码")
@RequestMapping("/player/verificationCode")
public class VerificationCodeApi {

    @Autowired
    private EhcacheService ehcacheService;

    @Autowired
    private LogRecordService logRecordService;

    @PostMapping("/get")
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    public synchronized R<String> get() {
        //添加频繁点击校验 3秒内点击超过30次 检查警告日志 如果该ip已经存在警告则拉黑 不存在则新加警告日志
        ehcacheService.checkIp3SecondsClick(30,"每3秒超过30次点击验证码");
        //获取验证码
        String verificationCode = GenerateTools.getVerificationCode();
        ehcacheService.getVerificationCodeCache().put(HttpTools.getIp(), verificationCode);
        logRecordService.insert(GenerateTools.createLog(LogTypeEnum.OPERATION,"获取验证码"));
        return R.ok(verificationCode);
    }

}
