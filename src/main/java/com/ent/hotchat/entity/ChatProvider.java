package com.ent.hotchat.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ent.hotchat.common.constant.enums.ChatProviderStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("陪聊员")
@TableName("chat_provider")
public class ChatProvider extends BaseInfo implements Serializable {
    private static final long serialVersionUID = 8687194654292974203L;

    @TableField("player_id")
    @ApiModelProperty("关联的玩家账号id")
    private Long playerId;

    @TableField("job")
    @ApiModelProperty("前端下拉框方式 内容: 学生,销售,服务,IT,教师,公务员,制造业,事业单位,技术手工,自由职业,待业,其他")
    private String job;

    @TableField("age")
    @ApiModelProperty("年龄")
    private Integer age;

    @TableField("height")
    @ApiModelProperty("身高")
    private Integer height;

    @TableField("address")
    @ApiModelProperty("国家-城市,前端两个输入框填写国家和地址,最后拼成一个地址参数 例如:中国-海南")
    private String address;

    @TableField("accepted_topic")
    @ApiModelProperty("接受话题 用户可以填写多个,前端用-组合成一个参数")
    private String acceptedTopic;

    @TableField("rejected_topic")
    @ApiModelProperty("不接受话题 用户可以填写多个,前端用-组合成一个参数")
    private String rejectedTopic;

    @TableField("chat_price")
    @ApiModelProperty("每半小时聊天价格")
    private BigDecimal chatPrice;

    @TableField("video_price")
    @ApiModelProperty("每半小时视频价格")
    private BigDecimal videoPrice;

    @TableField("online_time")
    @ApiModelProperty("在线时间范围 前端给两个输入框,分别用于填写两个时间 例如:12:00-23:00")
    private String onlineTime;

    @TableField("software")
    @ApiModelProperty("社交软件 用户可以填写多个,用-拼接成一个参数 例如:QQ-微信")
    private String software;

    @TableField("accounts")
    @ApiModelProperty("联系账号 根据software 用户可以填写相应多个账号,多个拼成一个参数 例如: QQ:12345678,微信:abc123")
    private String accounts;

    @TableField("character")
    @ApiModelProperty("性格描述")
    private String character;

    @TableField("photos")
    @ApiModelProperty("头像集合 多个照片路径用 , 逗号拼接")
    private String photos;

    @TableField("description")
    @ApiModelProperty("自我描述")
    private String description;

    @TableField("tag")
    @ApiModelProperty("标签 可以有多个用-隔开 例如:常在-活泼-搞笑")
    private String tag;

    @TableField("status")
    @ApiModelProperty("状态")
    private ChatProviderStatusEnum status;

    @TableField("level")
    @ApiModelProperty("等级 1-99")
    private Integer level;

}
