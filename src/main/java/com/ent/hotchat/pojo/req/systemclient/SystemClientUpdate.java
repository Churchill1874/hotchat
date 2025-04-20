package com.ent.hotchat.pojo.req.systemclient;

import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.pojo.Id;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SystemClientUpdate extends Id implements Serializable {
    private static final long serialVersionUID = 1525667436003821504L;


    @NotBlank(message = "昵称不能为空")
    @Length(max = 50,message = "昵称不能超过50个字符")
    @ApiModelProperty(value = "昵称",required = true)
    private String nickName;

    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态",required = true)
    private StatusEnum status;

}
