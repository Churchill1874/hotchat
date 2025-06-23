package com.ent.hotchat.common.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


public enum PaymentStatusEnum {

    UNPAID(0, "待付款"),
    PAID_WAITING_SERVICE(1, "已付款，待服务"),
    IN_SERVICE(3, "进行中"),
    COMPLETED(4, "已完成");

    @Getter
    @EnumValue
    @JsonValue
    private final Integer code;

    @Getter
    private final String name;

    PaymentStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.code + ":" + this.name;
    }

}
