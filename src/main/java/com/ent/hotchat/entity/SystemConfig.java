package com.ent.hotchat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ent.hotchat.entity.base.BaseInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_config")
public class SystemConfig extends BaseInfo implements Serializable {
    private static final long serialVersionUID = -7141848779426418438L;

    @ApiModelProperty("配置项key")
    private String configKey;

    @ApiModelProperty("配置项value")
    private String configValue;

    @ApiModelProperty("配置项描述")
    private String description;

}
