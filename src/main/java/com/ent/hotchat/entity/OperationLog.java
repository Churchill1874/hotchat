package com.ent.hotchat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ent.hotchat.entity.base.BaseInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("operation_log")
public class OperationLog extends BaseInfo implements Serializable {
    private static final long serialVersionUID = -5886058328940647370L;
    @ApiModelProperty("描述")
    private String introduce;

    @ApiModelProperty("操作的菜单")
    private String menu;

    @ApiModelProperty("操作账号")
    private String account;

}
