package com.ent.hotchat.common.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum OrderTypeEnum {

    VOICE(1, "语音"),
    VIDEO(2, "视频"),
    SCRIPT(3, "剧本"),
    ROLE_PLAY(4, "角色扮演");

    @Getter
    @EnumValue
    @JsonValue
    private final Integer code;

    @Getter
    private final String name;

    OrderTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.code + ":" + this.name;
    }

}
