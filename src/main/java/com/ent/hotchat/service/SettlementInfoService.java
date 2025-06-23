package com.ent.hotchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ent.hotchat.entity.OrderInfo;
import com.ent.hotchat.entity.SettlementInfo;

import java.util.List;


public interface SettlementInfoService extends IService<SettlementInfo> {
    /**
     * 创建结算单
     * @param orderInfo
     */
    void createSettlement(OrderInfo orderInfo);
}
