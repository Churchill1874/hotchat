package com.ent.hotchat.common.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum RoleTypeEnum {
    USER(1,"用户"),
    ANCHOR(2,"主播"),
    PROXY(3,"代理"),
    ADMIN(4,"管理");

    @Getter
    @EnumValue
    @JsonValue
    private Integer code;

    @Getter
    private String name;

    RoleTypeEnum(Integer code,String name){
        this.code=code;
        this.name=name;
    }

    @Override
    public String toString(){
        return this.code+":"+this.name;
    }
}
