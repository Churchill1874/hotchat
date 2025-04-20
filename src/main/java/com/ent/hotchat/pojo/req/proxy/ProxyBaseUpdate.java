package com.ent.hotchat.pojo.req.proxy;

import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.pojo.Id;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProxyBaseUpdate extends Id implements Serializable {
    private static final long serialVersionUID = -8375655186918959336L;

    @NotBlank(message = "昵称不能为空")
    @Length(max = 50,message = "昵称不能超过50个字符")
    @ApiModelProperty(value = "昵称",required = true)
    private String nickName;

    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态",required = true)
    private StatusEnum status;

    @NotBlank(message = "联系类型不能为空")
    @ApiModelProperty(value = "联系类型",required = true)
    private String contactType;

    @NotBlank(message = "联系方式信息不能为空")
    @Length(max = 30,message = "昵称不能超过30个字符")
    @ApiModelProperty(value = "联系方式信息",required = true)
    private String contact;

    @NotNull(message = "代理佣金比例不能为空")
    @ApiModelProperty(value = "代理佣金比例",required = true)
    private BigDecimal commissionRate=BigDecimal.ZERO;
}
