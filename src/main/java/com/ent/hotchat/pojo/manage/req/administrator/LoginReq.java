package com.ent.hotchat.pojo.manage.req.administrator;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginReq implements Serializable {
    private static final long serialVersionUID = 1649335790018162891L;

    @NotBlank(message = "账号不能为空")
    @ApiModelProperty(value = "账号",required = true)
    private String account;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @NotBlank(message = "图形验证码不能为空")
    @ApiModelProperty(value = "图形验证码",required = true)
    private String captcha;

}
