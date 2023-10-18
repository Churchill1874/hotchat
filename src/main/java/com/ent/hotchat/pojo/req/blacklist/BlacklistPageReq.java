package com.ent.hotchat.pojo.req.blacklist;

import com.ent.hotchat.pojo.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BlacklistPageReq extends Page implements Serializable {

    private static final long serialVersionUID = 8526224475627286219L;

    @ApiModelProperty("ip")
    private String ip;

    @ApiModelProperty("手机号")
    private String phoneNumber;

}
