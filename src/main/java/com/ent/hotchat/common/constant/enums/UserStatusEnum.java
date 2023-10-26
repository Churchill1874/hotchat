package com.ent.hotchat.common.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 用户状态枚举
 */
public enum UserStatusEnum {

    DISABLE(0,"禁用"),
    NORMAL(1,"正常");

    @Getter
    @EnumValue
    private int code;

    @Getter
    @JsonValue
    private String name;

    UserStatusEnum(int code,String name){
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return this.name + ":" + this.code;
    }

}
