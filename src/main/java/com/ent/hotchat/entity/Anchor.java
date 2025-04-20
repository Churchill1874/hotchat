package com.ent.hotchat.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.ent.hotchat.common.constant.enums.OnlineStatusEnum;
import com.ent.hotchat.entity.base.BaseInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;


@Data
@TableName("anchor_info")
public class Anchor extends BaseInfo {
    private static final long serialVersionUID = 6618323200998532196L;

    @ApiModelProperty("主播对应account.id")
    private Long anchorId;

    @ApiModelProperty("主播头像")
    private String avatar;

    @ApiModelProperty("主播声音")
    private String voiceSample;

    @ApiModelProperty("语音价格(CNY)/小时")
    private BigDecimal voicePriceCny;

    @ApiModelProperty("语音价格(USDT)/小时")
    private BigDecimal voicePriceUsdt;

    @ApiModelProperty("视频价格(CNY)/小时")
    private BigDecimal videoPriceCny;

    @ApiModelProperty("视频价格(USDT)/小时")
    private BigDecimal videoPriceUsdt;

    @ApiModelProperty("主播身高")
    private Integer height;

    @ApiModelProperty("主播体重")
    private Integer weight;

    @ApiModelProperty("主播性格")
    private String personality;

    @ApiModelProperty("主播年龄")
    private Integer age;

    @ApiModelProperty("主播学历")
    private String education;

    @ApiModelProperty("主播所在地区")
    private String region;

    @ApiModelProperty("主播爱好,逗号分隔")
    private String hobbies;

    @ApiModelProperty("主播擅长话题,逗号分隔")
    private String goodTopics;

    @ApiModelProperty("主播拒绝话题,逗号分隔")
    private String rejectTopics;

    @ApiModelProperty("主播个人描述")
    private String description;

    @ApiModelProperty("主播佣金比例")
    private BigDecimal commissionRate;

    @ApiModelProperty("主播在线状态")
    private OnlineStatusEnum onlineStatus;

    @ApiModelProperty("主播总订单数")
    private Integer totalOrders;

}
