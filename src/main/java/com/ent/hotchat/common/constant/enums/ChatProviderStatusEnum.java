package com.ent.hotchat.common.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum ChatProviderStatusEnum {

    FREE(1,"空闲"),
    WORKING(2,"忙碌中"),
    DOWN_LINE(3,"下线");

    @Getter
    @EnumValue
    private int code;

    @Getter
    @JsonValue
    private String name;

    ChatProviderStatusEnum(int code,String name){
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return this.name + ":" + this.code;
    }

}
