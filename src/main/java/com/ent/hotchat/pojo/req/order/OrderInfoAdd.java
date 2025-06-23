package com.ent.hotchat.pojo.req.order;

import com.ent.hotchat.common.constant.enums.OrderTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OrderInfoAdd {

    @ApiModelProperty(value = "订单类型",required = true)
    @NotNull(message = "订单类型不能为空")
    private OrderTypeEnum orderType;

    @ApiModelProperty(value = "客户账号ID",required = true)
    @NotNull(message = "客户id不能为空")
    private Long customerId;

    @ApiModelProperty(value = "主播账号ID1，单人视频/语言的主播id传入这个字段",required = true)
    @NotNull(message = "主播id不能为空,至少需要传入一个主播id")
    private Long anchorId1;

    @ApiModelProperty(value = "主播账号ID2")
    private Long anchorId2;

    @ApiModelProperty(value = "主播账号ID3")
    private Long anchorId3;

    @ApiModelProperty(value = "主播账号ID4")
    private Long anchorId4;

    @ApiModelProperty(value = "时长(小时)")
    private BigDecimal duration;

    @ApiModelProperty(value = "币种（CNY或者USDT）",required = true)
    @NotBlank(message = "币种不能为空")
    private String currency;

}
