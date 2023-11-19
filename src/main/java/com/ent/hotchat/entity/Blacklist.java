package com.ent.hotchat.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("黑名单")
@TableName("blacklist")
public class Blacklist extends BaseInfo implements Serializable {

    private static final long serialVersionUID = -3324921096334389678L;

    @TableField("ip")
    private String ip;

    @TableField("phone_number")
    private String phoneNumber;

    @TableField("remarks")
    private String remarks;

}
