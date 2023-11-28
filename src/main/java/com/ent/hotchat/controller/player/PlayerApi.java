package com.ent.hotchat.controller.player;

import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.common.constant.enums.PlayerRoleEnum;
import com.ent.hotchat.common.exception.AccountOrPasswordException;
import com.ent.hotchat.common.exception.DataException;
import com.ent.hotchat.entity.Player;
import com.ent.hotchat.pojo.player.req.LoginReq;
import com.ent.hotchat.pojo.player.req.RegisterReq;
import com.ent.hotchat.service.EhcacheService;
import com.ent.hotchat.service.PlayerService;
import com.ent.hotchat.tools.HttpTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RestController
@Api(tags = "玩家")
@RequestMapping("/player")
public class PlayerApi {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private EhcacheService ehcacheService;

    @PostMapping("/注册")
    @ApiOperation(value = "注册", notes = "注册")
    public R register(@RequestBody @Valid RegisterReq req) {
        if (StringUtils.isBlank(req.getAccount())){
            throw new DataException("请至少填写一个账号或手机号");
        }

        Player player = playerService.findByAccountOrPhone(req.getAccount(),null);

        if (player != null){
            throw new DataException("账号已存在");
        }

        //初始化
        player.setAccount(req.getAccount());
        player.setName(req.getName());
        player.setPassword(req.getPassword());
        player.setBalance(BigDecimal.ZERO);
        player.setRole(PlayerRoleEnum.NORMAL);
        player.setLastTime(LocalDateTime.now());
        player.setLastIp(HttpTools.getIp());
        player.setLastAddress(HttpTools.getAddress());
        player.setCreateTime(LocalDateTime.now());
        playerService.save(player);

        return R.ok(null);
    }

    @PostMapping("/登录")
    @ApiOperation(value = "登录", notes = "登录")
    public R<String> login(@RequestBody @Valid LoginReq req) {
        if (StringUtils.isBlank(req.getAccount()) && StringUtils.isBlank(req.getPhone())){
            throw new DataException("请至少填写一个账号或手机号");
        }

        Player player = null;

        if (StringUtils.isNotBlank(req.getAccount())) {
            player = playerService.findByAccountAndPassword(req.getAccount(), req.getPassword());
        }
        if (StringUtils.isBlank(req.getAccount()) && StringUtils.isNotBlank(req.getPhone())){
            player = playerService.findByPhoneAndPassword(req.getPhone(), req.getPassword());
        }

        if (player == null){
            throw new AccountOrPasswordException();
        }

        String tokenId = UUID.randomUUID().toString();
        ehcacheService.getTokenCache().put(tokenId, player);

        return R.ok(tokenId);
    }

}
