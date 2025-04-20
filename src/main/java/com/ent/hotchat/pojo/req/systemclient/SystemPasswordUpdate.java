package com.ent.hotchat.pojo.req.systemclient;

import com.ent.hotchat.pojo.Id;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class SystemPasswordUpdate extends Id implements Serializable {
    private static final long serialVersionUID = -2621160852498370227L;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "密码必须为字母和数字组成的20位字符")
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
