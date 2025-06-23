package com.ent.hotchat.entity;
import com.ent.hotchat.common.constant.enums.OrderTypeEnum;
import com.ent.hotchat.common.constant.enums.PaymentStatusEnum;
import com.ent.hotchat.common.constant.enums.SettlementStatusEnum;
import com.ent.hotchat.entity.base.BaseInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SettlementInfo extends BaseInfo implements Serializable {
    private static final long serialVersionUID = 1372730176119004067L;

    @ApiModelProperty("订单编号")
    private String orderNo;

    @ApiModelProperty("订单类型")
    private OrderTypeEnum orderType;

    @ApiModelProperty("客户账号ID")
    private Long customerId;

    @ApiModelProperty("客户昵称")
    private String customerName;

    @ApiModelProperty("随订单主播昵称(多个逗号分隔)")
    private String anchorNames;

    @ApiModelProperty("结算单主播ID")
    private Long anchorId;

    @ApiModelProperty("结算单主播昵称")
    private String anchorName;

    @ApiModelProperty("时长(小时)")
    private BigDecimal duration;

    @ApiModelProperty("联系方式")
    private String contactInfo;

    @ApiModelProperty("币种（CNY或者USDT）")
    private String currency;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("支付状态")
    private PaymentStatusEnum paymentStatus;

    @ApiModelProperty("下单时间")
    private LocalDateTime orderTime;

    @ApiModelProperty("付款时间")
    private LocalDateTime paymentTime;

    @ApiModelProperty("开始服务时间")
    private LocalDateTime startTime;

    @ApiModelProperty("结束服务时间")
    private LocalDateTime endTime;

    @ApiModelProperty("主播佣金比例")
    private BigDecimal commissionRate;

    @ApiModelProperty("主播结算金额")
    private BigDecimal settlementAmount;

    @ApiModelProperty("结算状态")
    private SettlementStatusEnum settlementStatus;

    @ApiModelProperty("结算单号")
    private String settlementNo;

    @ApiModelProperty("结算日期")
    private LocalDateTime settlementDate;

    @ApiModelProperty("总金额")
    private BigDecimal totalAmount;

}
