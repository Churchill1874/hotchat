package com.ent.hotchat.pojo.req.customer;

import com.ent.hotchat.pojo.Id;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class CustomerBaseUpdate extends Id implements Serializable {
    private static final long serialVersionUID = 2259181954899798761L;

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
