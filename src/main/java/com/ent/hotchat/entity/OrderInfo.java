package com.ent.hotchat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("order_info")
public class OrderInfo extends BaseInfo implements Serializable {
    private static final long serialVersionUID = -3710312934760593898L;

    @ApiModelProperty("订单编号")
    private String orderNo;

    @ApiModelProperty("订单类型")
    private OrderTypeEnum orderType;

    @ApiModelProperty("客户账号ID")
    private Long customerId;

    @ApiModelProperty("客户昵称")
    private String customerName;

    @ApiModelProperty("主播账号ID1")
    private Long anchorId1=0L;

    @ApiModelProperty("主播账号ID2")
    private Long anchorId2=0L;

    @ApiModelProperty("主播账号ID3")
    private Long anchorId3=0L;

    @ApiModelProperty("主播账号ID4")
    private Long anchorId4=0L;

    @ApiModelProperty("主播昵称1")
    private String anchorName1="";

    @ApiModelProperty("主播昵称2")
    private String anchorName2="";

    @ApiModelProperty("主播昵称3")
    private String anchorName3="";

    @ApiModelProperty("主播昵称4")
    private String anchorName4="";

    @ApiModelProperty("订单主播昵称(多个昵称用逗号分隔)")
    private String anchorNames="";

    @ApiModelProperty("时长(小时)")
    private BigDecimal duration;

    @ApiModelProperty("联系方式")
    private String contactInfo;

    @ApiModelProperty("币种（CNY或者USDT）")
    private String currency;

    @ApiModelProperty("金额1")
    private BigDecimal amount1=BigDecimal.ZERO;

    @ApiModelProperty("金额2")
    private BigDecimal amount2=BigDecimal.ZERO;

    @ApiModelProperty("金额3")
    private BigDecimal amount3=BigDecimal.ZERO;

    @ApiModelProperty("金额4")
    private BigDecimal amount4=BigDecimal.ZERO;

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

    @ApiModelProperty("代理佣金比例")
    private BigDecimal commissionRate;

    @ApiModelProperty("结算状态")
    private SettlementStatusEnum settlementStatus;

    @ApiModelProperty("结算单号")
    private String settlementNo;

    @ApiModelProperty("结算日期")
    private LocalDateTime settlementDate;

    @ApiModelProperty("总金额")
    private BigDecimal totalAmount;



}
