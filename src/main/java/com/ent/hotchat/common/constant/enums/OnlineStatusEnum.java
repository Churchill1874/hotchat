package com.ent.hotchat.common.constant.enums;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;

public enum OnlineStatusEnum {
    ONLINE(0,"空闲"),
    DND(1,"忙碌"),
    OFFLINE(2,"下线");

    @Getter
    @JsonValue
    @EnumValue
    private Integer code;

    @Getter
    private String name;

    OnlineStatusEnum(Integer code,String name){
        this.code=code;
        this.name=name;
    }

    @Override
    public String toString() {
        return this.code+":"+this.name;
    }
}
