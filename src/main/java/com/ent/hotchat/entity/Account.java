package com.ent.hotchat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ent.hotchat.common.constant.enums.RoleTypeEnum;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.entity.base.BaseInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("account")
public class Account extends BaseInfo implements Serializable {

    private static final long serialVersionUID = -772455558170830639L;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("盐")
    private String salt;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("联系类型")
    private String contactType;

    @ApiModelProperty("联系方式信息")
    private String contact;

    @ApiModelProperty("角色")
    private RoleTypeEnum roleType;

    @ApiModelProperty("最后登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("账号状态")
    private StatusEnum status;

    @ApiModelProperty("代理Id")
    private Long proxyId;

    @ApiModelProperty("代理账号")
    private String proxyName;

    @ApiModelProperty("代理域名")
    private String proxyDomain;


}
