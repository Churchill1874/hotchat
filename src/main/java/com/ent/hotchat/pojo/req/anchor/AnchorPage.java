package com.ent.hotchat.pojo.req.anchor;

import com.ent.hotchat.common.constant.enums.OnlineStatusEnum;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.pojo.BasePage;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class AnchorPage extends BasePage implements Serializable {
    private static final long serialVersionUID = 4981246025704729796L;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("昵称")
    private String ncikName;

    @ApiModelProperty("账号状态")
    private StatusEnum status;

    @ApiModelProperty("在线状态")
    private OnlineStatusEnum onlineStatus;
}
