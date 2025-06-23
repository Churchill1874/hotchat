package com.ent.hotchat.controller.client;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.ent.hotchat.entity.OrderInfo;
import com.ent.hotchat.pojo.req.order.OrderInfoAdd;
import com.ent.hotchat.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "订单管理")
@RequestMapping("/client/order")
@Slf4j
public class OrderApi {
    @Autowired
    private OrderInfoService orderInfoService;

    @PostMapping("/createOrder")
    @ApiOperation(value = "创建订单",notes = "创建订单")
    public R createOrder(@RequestBody @Valid OrderInfoAdd orderInfoAdd){
        OrderInfo orderInfo = BeanUtil.toBean(orderInfoAdd, OrderInfo.class);
        orderInfoService.createOrder(orderInfo);
        return R.ok(null);
    }
}
