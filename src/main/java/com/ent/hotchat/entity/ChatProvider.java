package com.ent.hotchat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("chat_provider")
@ApiModel("陪聊员")
public class ChatProvider extends BaseInfo implements Serializable {
    private static final long serialVersionUID = 8687194654292974203L;

    @ApiModelProperty("昵称")
    private String name;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("前端下拉框方式 内容: 学生,销售,服务,IT,教师,公务员,制造业,事业单位,技术手工,自由职业,待业,其他")
    private String job;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("身高")
    private Integer height;

    @ApiModelProperty("国家-城市,前端两个输入框填写国家和地址,最后拼成一个地址参数 例如:中国-海南")
    private String address;

    @ApiModelProperty("接受话题 用户可以填写多个,前端用-组合成一个参数")
    private String acceptedTopic;

    @ApiModelProperty("不接受话题 用户可以填写多个,前端用-组合成一个参数")
    private String rejectedTopic;

    @ApiModelProperty("每半小时聊天价格")
    private BigDecimal chatPrice;

    @ApiModelProperty("每半小时视频价格")
    private BigDecimal videoPrice;

    @ApiModelProperty("在线时间范围 前端给两个输入框,分别用于填写两个时间 例如:12:00-23:00")
    private String onlineTime;

    @ApiModelProperty("社交软件 用户可以填写多个,用-拼接成一个参数 例如:QQ-微信")
    private String software;

    @ApiModelProperty("联系账号 根据software 用户可以填写相应多个账号,多个拼成一个参数 例如: QQ:12345678,微信:abc123")
    private String accounts;

    @ApiModelProperty("性格描述")
    private String character;

    @ApiModelProperty("头像集合 多个照片路径用 , 逗号拼接")
    private String photos;

    @ApiModelProperty("自我描述")
    private String description;

    @ApiModelProperty("标签 可以有多个用-隔开 例如:常在-活泼-搞笑")
    private String tag;

    @ApiModelProperty("密码")
    private String password;



}
