package com.ent.hotchat.pojo.resp.proxy;

import com.ent.hotchat.common.constant.enums.RoleTypeEnum;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProxyInfoVO implements Serializable {
    private static final long serialVersionUID = -5142051943741023925L;

    @ApiModelProperty("代理accountid")
    private Long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("联系类型")
    private String contactType;

    @ApiModelProperty("联系方式信息")
    private String contact;

    @ApiModelProperty("状态")
    private StatusEnum status;

    @ApiModelProperty("佣金比例")
    private BigDecimal commissionRate;

    @ApiModelProperty("下属用户总数")
    private Integer totalUsers;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人")
    private String createName;

}
