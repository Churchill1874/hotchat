package com.ent.hotchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ent.hotchat.entity.OrderInfo;


public interface OrderInfoService extends IService<OrderInfo> {
    /**
     * 创建订单接口
     * @param orderInfoAdd
     */
    void createOrder(OrderInfo orderInfo);

}
