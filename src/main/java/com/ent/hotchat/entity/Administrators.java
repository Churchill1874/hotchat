package com.ent.hotchat.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ent.hotchat.common.constant.enums.AdminRoleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("管理员")
@TableName("administrators")
public class Administrators extends BaseInfo implements Serializable {
    private static final long serialVersionUID = -7719439849954352417L;

    @TableField("role_type")
    @ApiModelProperty("角色")
    private AdminRoleEnum roleType;

    @TableField("name")
    @ApiModelProperty("名字")
    private String name;

    @TableField("account")
    @ApiModelProperty("账号")
    private String account;

    @TableField("password")
    @ApiModelProperty("密码")
    private String password;


}
