package com.ent.hotchat.pojo.manage.req.administrator;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class AddAdminReq implements Serializable {
    private static final long serialVersionUID = 863165349174209160L;

    @NotBlank(message = "名字不能为空")
    @ApiModelProperty(value = "名字", required = true)
    private String name;

    @NotBlank(message = "账号不能为空")
    @ApiModelProperty(value = "账号", required = true)
    private String account;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
