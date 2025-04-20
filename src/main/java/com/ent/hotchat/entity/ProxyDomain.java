package com.ent.hotchat.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.entity.base.BaseInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("proxy_domain")
public class ProxyDomain extends BaseInfo {

    private static final long serialVersionUID = 3813212423766037486L;

    @ApiModelProperty("代理域名")
    private String domainName;

    @ApiModelProperty("代理关联的account_id")
    private Long accountId;

    @ApiModelProperty("代理关联的account")
    private String account;

    @ApiModelProperty("域名可用状态")
    private StatusEnum status;

}
