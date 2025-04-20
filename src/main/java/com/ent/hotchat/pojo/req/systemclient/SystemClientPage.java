package com.ent.hotchat.pojo.req.systemclient;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.pojo.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SystemClientPage extends BasePage {
    private static final long serialVersionUID = -6177166561766192461L;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("账号状态")
    private StatusEnum status;
}
