package com.ent.hotchat.pojo.req.customer;

import com.ent.hotchat.pojo.Id;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class ClientPasswordUpdate extends Id implements Serializable {
    private static final long serialVersionUID = -4028679145812202996L;

    @NotBlank(message = "旧密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "旧密码必须为字母和数字组成的20位字符")
    @ApiModelProperty(value = "旧密码",required = true)
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "新密码必须为字母和数字组成的20位字符")
    @ApiModelProperty(value = "新密码",required = true)
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "确认密码必须为字母和数字组成的20位字符")
    @ApiModelProperty(value = "确认密码",required = true)
    private String confirmPassword;
}
