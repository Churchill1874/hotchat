package com.ent.hotchat.common.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


public enum SettlementStatusEnum {

    UNSETTLED(0, "待结算"),
    SETTLED(1, "已结算");

    @Getter
    @EnumValue
    @JsonValue
    private final Integer code;

    @Getter
    private final String name;

    SettlementStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.code + ":" + this.name;
    }

}
