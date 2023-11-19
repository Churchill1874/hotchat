package com.ent.hotchat.common.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum PlayerRoleEnum {

    ROBOT(1,"机器人"),
    NORMAL(2,"普通"),
    CHAT_PROVIDER(3,"陪聊员");

    @Getter
    @EnumValue
    private int code;

    @Getter
    @JsonValue
    private String name;

    PlayerRoleEnum(int code,String name){
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return this.name + ":" + this.code;
    }

}
