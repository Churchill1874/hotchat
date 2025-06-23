package com.ent.hotchat.pojo.req.proxy;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProxyAdd implements Serializable {
    private static final long serialVersionUID = 5504473083038195491L;

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "用户名必须为字母和数字组成且不大于20位")
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "密码必须为字母和数字组成且不大于20位")
    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @NotBlank(message = "昵称不能为空")
    @Length(max = 50,message = "昵称不能超过50个字符")
    @ApiModelProperty(value = "昵称",required = true)
    private String nickName;

    @NotBlank(message = "联系类型不能为空")
    @ApiModelProperty(value = "联系类型",required = true)
    private String contactType;

    @NotBlank(message = "联系方式信息不能为空")
    @Length(max = 30,message = "昵称不能超过30个字符")
    @ApiModelProperty(value = "联系方式信息",required = true)
    private String contact;

}
