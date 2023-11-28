package com.ent.hotchat.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ent.hotchat.common.exception.AccountOrPasswordException;
import com.ent.hotchat.common.exception.DataException;
import com.ent.hotchat.entity.Player;
import com.ent.hotchat.mapper.PlayerMapper;
import com.ent.hotchat.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PlayerServiceImpl extends ServiceImpl<PlayerMapper, Player> implements PlayerService {

    @Override
    public Player checkLogin(String account, String password) {
        QueryWrapper<Player> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .lambda()
                .eq(Player::getAccount, account)
                .eq(Player::getPassword, password);
        Player player = getOne(queryWrapper);

        if (player == null) {
            throw new AccountOrPasswordException();
        }

        return player;
    }

    @Override
    public Player findByAccountOrPhone(String account, String phone) {
        if (StringUtils.isBlank(account) && StringUtils.isBlank(phone)) {
            throw new DataException("账号和手机号不能同时为空查找");
        }

        QueryWrapper<Player> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StringUtils.isNotBlank(account), Player::getAccount, account)
                .eq(StringUtils.isNotBlank(phone), Player::getPhone, phone);

        return getOne(queryWrapper);
    }

    @Override
    public Player findByAccountAndPassword(String account, String password) {
        QueryWrapper<Player> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Player::getAccount, account)
                .eq(Player::getPassword, password);
        return getOne(queryWrapper);
    }

    @Override
    public Player findByPhoneAndPassword(String phone, String password) {
        QueryWrapper<Player> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Player::getPhone, phone)
                .eq(Player::getPassword, password);
        return getOne(queryWrapper);
    }

}
