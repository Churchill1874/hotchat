package com.ent.hotchat.common.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum ChatProviderStatusEnum {

    FREE(1,"空闲"),
    WORKING(2,"工作中"),
    REST(3,"休息");

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
