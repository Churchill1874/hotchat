package com.ent.hotchat.pojo.req.proxydomain;

import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.pojo.Id;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ProxyDomainUpdate extends Id implements Serializable {
    private static final long serialVersionUID = 7754236826578651753L;

    @NotBlank(message = "代理域名不能为空")
    @ApiModelProperty("代理域名")
    private String domainName;

    @NotBlank(message = "代理账号不能为空")
    @ApiModelProperty("代理关联的account")
    private String account;

    @NotNull(message = "代理域名不能为空")
    @ApiModelProperty("域名可用状态")
    private StatusEnum status;

}
