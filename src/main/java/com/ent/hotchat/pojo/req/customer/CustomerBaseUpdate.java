package com.ent.hotchat.pojo.req.customer;

import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.pojo.Id;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class CustomerBaseUpdate extends Id implements Serializable {
    private static final long serialVersionUID = 2259181954899798761L;


    @Length(max = 50,message = "昵称不能超过50个字符")
    @ApiModelProperty(value = "昵称")
    private String nickName;


    @ApiModelProperty(value = "联系类型")
    private String contactType;


    @Length(max = 30,message = "昵称不能超过30个字符")
    @ApiModelProperty(value = "联系方式信息")
    private String contact;


    @ApiModelProperty(value = "状态")
    private StatusEnum status;
}
