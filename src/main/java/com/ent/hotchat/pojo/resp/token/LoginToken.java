package com.ent.hotchat.pojo.resp.token;

import com.ent.hotchat.common.constant.enums.OnlineStatusEnum;
import com.ent.hotchat.common.constant.enums.RoleTypeEnum;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoginToken implements Serializable {
    private static final long serialVersionUID = 5130986957455861652L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("token令牌")
    private String tokenId;

    @ApiModelProperty("账号")
    private String userName;

    @ApiModelProperty("名字")
    private String nickName;

    @ApiModelProperty("角色")
    private RoleTypeEnum roleType;

    @ApiModelProperty("最后登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("账号状态 0禁用 1正常")
    private StatusEnum status;

    @ApiModelProperty("联系类型")
    private String contactType;

    @ApiModelProperty("联系方式信息")
    private String contact;

    @ApiModelProperty("代理对应account.id")
    private Long proxyId;

    @ApiModelProperty("代理账号")
    private String proxyName;

    @ApiModelProperty("代理域名")
    private String proxyDomain;

    @ApiModelProperty("代理/主播佣金比例")
    private BigDecimal commissionRate;


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

    @ApiModelProperty("主播状态")
    private OnlineStatusEnum onlineStatus;

    @ApiModelProperty("主播总订单数")
    private Integer totalOrders;

}
