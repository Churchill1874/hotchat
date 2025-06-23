package com.ent.hotchat.pojo.req.anchor;

import com.ent.hotchat.common.constant.enums.OnlineStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AnchorAdd implements Serializable {
    private static final long serialVersionUID = -7861517365814881064L;

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "用户名必须为字母和数字组成的20位字符")
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "密码必须为字母和数字组成的20位字符")
    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @NotBlank(message = "昵称不能为空")
    @Length(max = 50,message = "昵称不能超过50个字符")
    @ApiModelProperty(value = "昵称",required = true)
    private String nickName;

    @NotBlank(message = "联系类型不能为空")
    @ApiModelProperty(value = "联系类型",required = true)
    private String contactType;

    @NotBlank(message = "联系方式信息不能为空")
    @Length(max = 30,message = "昵称不能超过30个字符")
    @ApiModelProperty(value = "联系方式信息",required = true)
    private String contact;

    @NotBlank(message = "头像不能为空")
    @ApiModelProperty(value = "主播头像",required = true)
    private String avatar;

    @NotBlank(message = "声音不能为空")
    @ApiModelProperty(value = "主播声音",required = true)
    private String voiceSample;

    @NotNull(message = "语音价格(CNY)不能为空")
    @ApiModelProperty(value = "语音价格(CNY)/小时",required = true)
    private BigDecimal voicePriceCny=BigDecimal.ZERO;

    @NotNull(message = "语音价格(USDT)不能为空")
    @ApiModelProperty(value = "语音价格(USDT)/小时",required = true)
    private BigDecimal voicePriceUsdt=BigDecimal.ZERO;

    @NotNull(message = "视频价格(CNY)不能为空")
    @ApiModelProperty(value = "视频价格(CNY)/小时",required = true)
    private BigDecimal videoPriceCny=BigDecimal.ZERO;

    @NotNull(message = "视频价格(USDT)不能为空")
    @ApiModelProperty(value = "视频价格(USDT)/小时",required = true)
    private BigDecimal videoPriceUsdt=BigDecimal.ZERO;

    @NotNull(message = "身高不能为空")
    @ApiModelProperty(value = "主播身高",required = true)
    private Integer height;

    @NotNull(message = "体重不能为空")
    @ApiModelProperty(value = "主播体重",required = true)
    private Integer weight;

    @NotBlank(message = "性格不能为空")
    @ApiModelProperty(value = "主播性格",required = true)
    private String personality;

    @NotNull(message = "年龄不能为空")
    @ApiModelProperty(value = "主播年龄",required = true)
    private Integer age;

    @NotBlank(message = "学历不能为空")
    @ApiModelProperty(value = "主播学历",required = true)
    private String education;

    @NotBlank(message = "所在地区不能为空")
    @ApiModelProperty(value = "主播所在地区",required = true)
    private String region;

    @NotBlank(message = "爱好不能为空")
    @ApiModelProperty(value = "主播爱好,逗号分隔",required = true)
    private String hobbies;

    @NotBlank(message = "擅长话题不能为空")
    @ApiModelProperty(value = "主播擅长话题,逗号分隔",required = true)
    private String goodTopics;

    @NotBlank(message = "拒绝话题不能为空")
    @ApiModelProperty(value = "主播拒绝话题,逗号分隔",required = true)
    private String rejectTopics;

    @ApiModelProperty("主播个人描述")
    private String description;

    @NotNull(message = "主播在线状态不能为空")
    @ApiModelProperty(value = "主播在线状态")
    private OnlineStatusEnum onlineStatus;

}
