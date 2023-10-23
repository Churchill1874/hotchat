package com.ent.hotchat.common.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 角色枚举
 */
public enum AdminRoleEnum  {

    ADMIN(1, "管理员"),
    SUPER_ADMIN(2, "超级管理员");

    @Getter
    @EnumValue
    private int code;

    @Getter
    @JsonValue
    private String name;

    AdminRoleEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name + ":" + this.code;
    }

}
