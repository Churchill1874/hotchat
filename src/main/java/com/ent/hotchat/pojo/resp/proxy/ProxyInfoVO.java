package com.ent.hotchat.pojo.resp.proxy;

import com.ent.hotchat.common.constant.enums.RoleTypeEnum;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProxyInfoVO implements Serializable {
    private static final long serialVersionUID = -5142051943741023925L;

    @ApiModelProperty("代理accountid")
    private Long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("联系类型")
    private String contactType;

    @ApiModelProperty("联系方式信息")
    private String contact;

    @ApiModelProperty("角色")
    private RoleTypeEnum roleType;

    @ApiModelProperty("状态")
    private StatusEnum status;

    @ApiModelProperty("佣金比例")
    private BigDecimal commissionRate;

    @ApiModelProperty("下属用户总数")
    private Integer totalUsers;

}
