package com.ent.hotchat.controller.manage;

import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.tools.GenerateTools;
import com.ent.hotchat.tools.HttpTools;
import com.ent.hotchat.service.EhcacheService;
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
@RequestMapping("/manage/captchaCache")
public class CaptchaCodeController {

    @Autowired
    private EhcacheService ehcacheService;


    @PostMapping("/get")
    @ApiOperation(value = "获取图形验证码", notes = "获取图形验证码")
    public synchronized R<String> get() {
        //添加频繁点击校验 3秒内点击超过30次 检查警告日志 如果该ip已经存在警告则拉黑 不存在则新加警告日志
        ehcacheService.checkIp3SecondsClick(20,"每3秒超过30次点击验证码");
        //获取验证码
        String verificationCode = GenerateTools.getVerificationCode();
        ehcacheService.captchaCache().put(HttpTools.getIp(), verificationCode);
        return R.ok(verificationCode);
    }

}