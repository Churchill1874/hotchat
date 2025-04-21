package com.ent.hotchat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ent.hotchat.entity.base.BaseInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("proxy_info")
public class Proxy extends BaseInfo {
    private static final long serialVersionUID = 6763856807483804455L;

    @ApiModelProperty("代理关联的account_id")
    private Long proxyId;

    @ApiModelProperty("佣金比例")
    private BigDecimal commissionRate;


}
