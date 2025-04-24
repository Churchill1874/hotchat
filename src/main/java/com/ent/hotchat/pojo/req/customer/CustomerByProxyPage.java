package com.ent.hotchat.pojo.req.customer;

import com.ent.hotchat.pojo.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CustomerByProxyPage extends BasePage implements Serializable {
    private static final long serialVersionUID = 3205320776751858315L;

    @NotNull(message = "代理Id不能为空")
    @ApiModelProperty(value = "代理Id,通过代理id查询下线",required = true)
    private Long proxyId;
}
