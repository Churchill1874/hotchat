package com.ent.hotchat.pojo.req.anchor;

import com.ent.hotchat.common.constant.enums.OnlineStatusEnum;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.pojo.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AnchorPage extends BasePage implements Serializable {
    private static final long serialVersionUID = 4981246025704729796L;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("账号状态,默认传1")
    private StatusEnum status=StatusEnum.NORMAL;

    @ApiModelProperty("在线状态")
    private OnlineStatusEnum onlineStatus=OnlineStatusEnum.ONLINE;
}
