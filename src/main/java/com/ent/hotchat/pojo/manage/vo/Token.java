package com.ent.hotchat.pojo.manage.vo;

import com.ent.hotchat.common.constant.enums.AdminRoleEnum;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Token {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 昵称
     */
    private String name;

    /**
     * 创建时间
     */
    private LocalDateTime loginTime;

    /**
     * 角色类型
     */
    private AdminRoleEnum roleType;

    /**
     * 状态 0禁用 1正常
     */
    private StatusEnum status;


}
