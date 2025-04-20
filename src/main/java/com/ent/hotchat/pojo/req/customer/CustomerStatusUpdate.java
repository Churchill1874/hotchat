package com.ent.hotchat.pojo.req.customer;

import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.pojo.Id;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CustomerStatusUpdate extends Id implements Serializable {
    private static final long serialVersionUID = -3694633076107283463L;

    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态",required = true)
    private StatusEnum status;
}
