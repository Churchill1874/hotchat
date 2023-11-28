package com.ent.hotchat.pojo.player.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginReq implements Serializable {
    private static final long serialVersionUID = 7018945269436104435L;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("账号")
    private String account;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;


}
