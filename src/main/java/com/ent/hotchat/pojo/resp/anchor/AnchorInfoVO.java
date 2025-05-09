package com.ent.hotchat.pojo.resp.anchor;

import com.ent.hotchat.common.constant.enums.OnlineStatusEnum;
import com.ent.hotchat.common.constant.enums.RoleTypeEnum;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AnchorInfoVO implements Serializable {
    private static final long serialVersionUID = -1947203130985750408L;

    @ApiModelProperty("主播accountid")
    private Long id;

    @ApiModelProperty("用户名")
    private String userName;


    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("联系类型")
    private String contactType;

    @ApiModelProperty("联系方式信息")
    private String contact;

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

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人")
    private String createName;

//    @ApiModelProperty("主播总订单数")
//    private Integer totalOrders;

}
