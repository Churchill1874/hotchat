package com.ent.hotchat.common.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum StatusEnum {
    DISABLE(0,"禁用"),
    NORMAL(1,"正常");

    @Getter
    @EnumValue
    @JsonValue
    private Integer code;

    @Getter
    private String name;

    StatusEnum(Integer code,String name){
        this.code=code;
        this.name=name;
    }

    @Override
    public String toString() {
        return this.code+":"+this.name;
    }
}
