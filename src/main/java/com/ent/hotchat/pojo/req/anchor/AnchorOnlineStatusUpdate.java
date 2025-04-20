package com.ent.hotchat.pojo.req.anchor;

import com.ent.hotchat.common.constant.enums.OnlineStatusEnum;
import com.ent.hotchat.pojo.Id;
import lombok.Data;

import java.io.Serializable;

@Data
public class AnchorOnlineStatusUpdate extends Id implements Serializable {
    private static final long serialVersionUID = -7680557156086334272L;

    private OnlineStatusEnum onlineStatus;
}
