package com.ent.hotchat.common.tools;

import cn.hutool.core.util.RandomUtil;
import com.ent.hotchat.entity.UserInfo;
import com.ent.hotchat.pojo.vo.Token;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 生产工具
 */
public class GenerateTools {

    /**
     * 根据当前时间生成编号
     *
     * @return
     */
    public static String getTimeNo() {
        return LocalDateTime.now().toString()
                .replace(".", "")
                .replace("T", "")
                .replace(":", "")
                .replace("-", "");
    }

    /**
     * 生成五位验证码
     */
    public static String getVerificationCode() {
        int num = RandomUtil.randomInt(10000, 99999);
        return String.valueOf(num);
    }

    /**
     * 生成日志对象
     *
     * @param logTypeEnum 日志类型
     * @param message     日志内容
     * @return
     */
    public static LogRecord createLog(LogTypeEnum logTypeEnum, String message) {
        LogRecord logRecord = new LogRecord();
        logRecord.setIp(HttpTools.getIp());
        logRecord.setType(logTypeEnum);
        logRecord.setMessage(message);
        logRecord.setAccount(TokenTools.getAccountMayNull());
        logRecord.setPlatform(HttpTools.getPlatform());
        return logRecord;
    }

    /**
     * 生成登录日志
     *
     * @return
     */
    public static LogRecord createLoginLog(Integer platform) {
        String ip = HttpTools.getIp();
        LogRecord logRecord = new LogRecord();
        logRecord.setIp(ip);
        logRecord.setType(LogTypeEnum.LOGIN);
        logRecord.setMessage(HttpTools.findAddressByIp(ip));
        logRecord.setAccount(TokenTools.getAccountMayNull());
        logRecord.setPlatform(platform);
        return logRecord;
    }

    /**
     * 传概念注册账号日志
     *
     * @param name
     * @param account
     * @return
     */
    public static LogRecord registerLog(String name, String account) {
        String ip = HttpTools.getIp();
        LogRecord logRecord = new LogRecord();
        logRecord.setIp(ip);
        logRecord.setType(LogTypeEnum.REGISTER);
        logRecord.setMessage(name);
        logRecord.setAccount(account);
        return logRecord;
    }

    /**
     * 生产警告日志对象
     *
     * @param message
     * @return
     */
    public static LogRecord createWarnLog(String message) {
        String ip = HttpTools.getIp();
        LogRecord logRecord = new LogRecord();
        logRecord.setIp(ip);
        logRecord.setType(LogTypeEnum.WARN);
        logRecord.setMessage(message);
        return logRecord;
    }

    /**
     * 生产警告日志对象
     *
     * @param message
     * @param ip
     * @return
     */
    public static LogRecord createWarnLog(String message, String ip, int platform) {
        LogRecord logRecord = new LogRecord();
        logRecord.setIp(ip);
        logRecord.setType(LogTypeEnum.WARN);
        logRecord.setMessage(message);
        logRecord.setPlatform(platform);
        return logRecord;
    }


    /**
     * 生成token对象
     *
     * @param userInfo
     * @return
     */
    public static Token createToken(UserInfo userInfo) {
        Token token = new Token();
        token.setName(userInfo.getName());
        token.setLoginTime(LocalDateTime.now());
        token.setAccount(userInfo.getAccount());
        token.setId(userInfo.getId());
        token.setStatus(userInfo.getStatus());
        token.setLevel(userInfo.getLevel());
        return token;
    }


    /**
     * 创建tokenId
     *
     * @param account
     * @return
     */
    public static String createTokenId(String account) {
        int accountLength = String.valueOf(account).length();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = uuid.substring(0, uuid.length() - accountLength);
        return uuid + account;
    }
}
