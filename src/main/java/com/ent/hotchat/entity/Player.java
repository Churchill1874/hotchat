package com.ent.hotchat.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ent.hotchat.common.constant.enums.PlayerRoleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("玩家")
@TableName("player")
public class Player extends BaseInfo implements Serializable {
    private static final long serialVersionUID = 1848031804918029501L;

    @TableField("account")
    @ApiModelProperty("账号")
    private String account;

    @TableField("name")
    @ApiModelProperty("名字")
    private String name;

    @TableField("password")
    @ApiModelProperty("密码")
    private String password;

    @TableField("balance")
    @ApiModelProperty("余额")
    private BigDecimal balance;

    @TableField("software")
    @ApiModelProperty("社交软件 用逗号隔开")
    private String software;

    @TableField("accounts")
    @ApiModelProperty("社交软件账号 用逗号隔开")
    private String accounts;

    @TableField("role")
    @ApiModelProperty("角色")
    private PlayerRoleEnum role;

    @TableField("phone")
    @ApiModelProperty("手机号")
    private String phone;

    @TableField("last_time")
    @ApiModelProperty("最后登陆时间")
    private LocalDateTime lastTime;

    @TableField("last_ip")
    @ApiModelProperty("最后登录ip")
    private String lastIp;

    @TableField("last_address")
    @ApiModelProperty("最后登录地址")
    private String lastAddress;


}
