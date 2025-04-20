package com.ent.hotchat.pojo.req.customer;

import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.pojo.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerPage extends BasePage implements Serializable {
    private static final long serialVersionUID = -6896919599689536318L;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("账号状态")
    private StatusEnum status;
}
