package com.ent.hotchat.pojo.req.proxy;

import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.pojo.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProxyPage extends BasePage implements Serializable {
    private static final long serialVersionUID = 6063768971269191015L;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("账号状态")
    private StatusEnum status;
}
