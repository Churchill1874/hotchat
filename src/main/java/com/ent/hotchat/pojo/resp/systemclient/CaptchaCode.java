package com.ent.hotchat.pojo.resp.systemclient;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CaptchaCode implements Serializable {
    private static final long serialVersionUID = 4608282030907446982L;

    @ApiModelProperty("验证码图片")
    private String captchaImage;
}
