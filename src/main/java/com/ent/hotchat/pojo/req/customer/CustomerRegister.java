package com.ent.hotchat.pojo.req.customer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class CustomerRegister implements Serializable {
    private static final long serialVersionUID = -5966532854377631918L;

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "用户名必须为字母和数字组成且不大于20位")
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "密码必须为字母和数字组成且不大于20位")
    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "确认密码必须为字母和数字组成的20位字符")
    @ApiModelProperty(value = "确认密码",required = true)
    private String confirmPassword;

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

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码",required = true)
    private String verificationCode;
}
