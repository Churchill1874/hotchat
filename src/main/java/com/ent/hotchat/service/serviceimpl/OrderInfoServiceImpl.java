package com.ent.hotchat.service.serviceimpl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ent.hotchat.common.constant.enums.OrderTypeEnum;
import com.ent.hotchat.common.constant.enums.PaymentStatusEnum;
import com.ent.hotchat.common.constant.enums.SettlementStatusEnum;
import com.ent.hotchat.common.tools.GenerateTools;
import com.ent.hotchat.common.tools.LogTools;
import com.ent.hotchat.common.tools.TokenTools;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.entity.OrderInfo;
import com.ent.hotchat.mapper.OrderInfoMapper;
import com.ent.hotchat.pojo.req.order.OrderInfoAdd;
import com.ent.hotchat.pojo.resp.proxy.ProxyInfoVO;
import com.ent.hotchat.service.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {
    private static final Logger logger= LoggerFactory.getLogger(OrderInfoServiceImpl.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProxyService proxyService;

    @Autowired
    private AnchorService anchorService;

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private SettlementInfoService settlementInfoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(OrderInfo orderInfo) {
        orderInfo.setOrderNo(GenerateTools.generateOrderNumber());
        //如果是剧本，读取公共配置里面的价格，剧本配置里的时长
        if(orderInfo.getOrderType().equals(OrderTypeEnum.SCRIPT)){
            orderInfo.setDuration(new BigDecimal(2));
            if(orderInfo.getCurrency().equals("CNY")){
                orderInfo.setAmount1(new BigDecimal(systemConfigService.getByKey("script_amount_cny").getConfigValue()));
            }
            orderInfo.setAmount1(new BigDecimal(systemConfigService.getByKey("script_amount_usdt").getConfigValue()));

        }
        //如果是角色扮演，读取公共配置里面的价格
        if(orderInfo.getOrderType().equals(OrderTypeEnum.ROLE_PLAY)){
            if(orderInfo.getCurrency().equals("CNY")){
                orderInfo.setAmount1(new BigDecimal(systemConfigService.getByKey("role_amount_cny").getConfigValue()));
            }
            orderInfo.setAmount1(new BigDecimal(systemConfigService.getByKey("role_amount_usdt").getConfigValue()));
        }

        List<Long> ids=new ArrayList<>();
        //根据传入的主播id，获取主播昵称
        if(orderInfo.getAnchorId1()!=null && orderInfo.getAnchorId1()!=0){
            orderInfo.setAnchorName1(customerService.findById(orderInfo.getAnchorId1()).getNickName());
            ids.add(orderInfo.getAnchorId1());
        }
        if(orderInfo.getAnchorId2()!=null && orderInfo.getAnchorId2()!=0){
            orderInfo.setAnchorName2(customerService.findById(orderInfo.getAnchorId2()).getNickName());
            ids.add(orderInfo.getAnchorId2());
        }
        if(orderInfo.getAnchorId3()!=null && orderInfo.getAnchorId3()!=0){
            orderInfo.setAnchorName3(customerService.findById(orderInfo.getAnchorId3()).getNickName());
            ids.add(orderInfo.getAnchorId3());
        }
        if(orderInfo.getAnchorId4()!=null && orderInfo.getAnchorId4()!=0){
            orderInfo.setAnchorName4(customerService.findById(orderInfo.getAnchorId4()).getNickName());
            ids.add(orderInfo.getAnchorId4());
        }

        //获取语音金额
        if (orderInfo.getOrderType().equals(OrderTypeEnum.VOICE) && orderInfo.getAnchorId1()!=null && orderInfo.getAnchorId1()!=0){
            if(orderInfo.getCurrency().equals("CNY")){
                BigDecimal voiceAmount1 = anchorService.findById(orderInfo.getAnchorId1()).getVoicePriceCny();
                orderInfo.setAmount1(voiceAmount1);
            }
            BigDecimal voiceAmount1 = anchorService.findById(orderInfo.getAnchorId1()).getVoicePriceUsdt();
            orderInfo.setAmount1(voiceAmount1);
        }

        if (orderInfo.getOrderType().equals(OrderTypeEnum.VOICE) && orderInfo.getAnchorId2()!=null && orderInfo.getAnchorId2()!=0){
            if(orderInfo.getCurrency().equals("CNY")){
                BigDecimal voiceAmount2 = anchorService.findById(orderInfo.getAnchorId2()).getVoicePriceCny();
                orderInfo.setAmount2(voiceAmount2);
            }
            BigDecimal voiceAmount2 = anchorService.findById(orderInfo.getAnchorId2()).getVoicePriceUsdt();
            orderInfo.setAmount2(voiceAmount2);
        }
        if (orderInfo.getOrderType().equals(OrderTypeEnum.VOICE) && (orderInfo.getAnchorId3()!=null && orderInfo.getAnchorId3()!=0)){
            if(orderInfo.getCurrency().equals("CNY")){
                BigDecimal voiceAmount3 = anchorService.findById(orderInfo.getAnchorId3()).getVoicePriceCny();
                orderInfo.setAmount3(voiceAmount3);
            }
            BigDecimal voiceAmount3 = anchorService.findById(orderInfo.getAnchorId3()).getVoicePriceUsdt();
            orderInfo.setAmount3(voiceAmount3);
        }
        if (orderInfo.getOrderType().equals(OrderTypeEnum.VOICE) && orderInfo.getAnchorId4()!=null && orderInfo.getAnchorId4()!=0){
            if(orderInfo.getCurrency().equals("CNY")){
                BigDecimal voiceAmount4 = anchorService.findById(orderInfo.getAnchorId4()).getVoicePriceCny();
                orderInfo.setAmount4(voiceAmount4);
            }
            BigDecimal voiceAmount4 = anchorService.findById(orderInfo.getAnchorId4()).getVoicePriceUsdt();
            orderInfo.setAmount4(voiceAmount4);
        }
        //获取视频金额
        if (orderInfo.getOrderType().equals(OrderTypeEnum.VIDEO) && orderInfo.getAnchorId1()!=null && orderInfo.getAnchorId1()!=0){
            if(orderInfo.getCurrency().equals("CNY")){
                BigDecimal videoAmount1 = anchorService.findById(orderInfo.getAnchorId1()).getVideoPriceCny();
                orderInfo.setAmount1(videoAmount1);
            }
            BigDecimal videoAmount1 = anchorService.findById(orderInfo.getAnchorId1()).getVideoPriceUsdt();
            orderInfo.setAmount1(videoAmount1);
        }

        if (orderInfo.getOrderType().equals(OrderTypeEnum.VIDEO) && orderInfo.getAnchorId2()!=null && orderInfo.getAnchorId2()!=0){
            if(orderInfo.getCurrency().equals("CNY")){
                BigDecimal videoAmount2 = anchorService.findById(orderInfo.getAnchorId2()).getVideoPriceCny();
                orderInfo.setAmount2(videoAmount2);
            }
            BigDecimal videoAmount2 = anchorService.findById(orderInfo.getAnchorId2()).getVideoPriceUsdt();
            orderInfo.setAmount2(videoAmount2);
        }
        if (orderInfo.getOrderType().equals(OrderTypeEnum.VIDEO) && orderInfo.getAnchorId3()!=null && orderInfo.getAnchorId3()!=0){
            if(orderInfo.getCurrency().equals("CNY")){
                BigDecimal videoAmount3 = anchorService.findById(orderInfo.getAnchorId3()).getVideoPriceCny();
                orderInfo.setAmount3(videoAmount3);
            }
            BigDecimal videoAmount3 = anchorService.findById(orderInfo.getAnchorId3()).getVideoPriceUsdt();
            orderInfo.setAmount3(videoAmount3);
        }
        if (orderInfo.getOrderType().equals(OrderTypeEnum.VIDEO) && orderInfo.getAnchorId4()!=null && orderInfo.getAnchorId4()!=0){
            if(orderInfo.getCurrency().equals("CNY")){
                BigDecimal videoAmount4 = anchorService.findById(orderInfo.getAnchorId4()).getVideoPriceCny();
                orderInfo.setAmount4(videoAmount4);
            }
            BigDecimal videoAmount4 = anchorService.findById(orderInfo.getAnchorId4()).getVideoPriceUsdt();
            orderInfo.setAmount4(videoAmount4);
        }
        Account account = customerService.findById(orderInfo.getCustomerId());
        orderInfo.setCustomerName(account.getNickName());
        orderInfo.setContactInfo(account.getContactType()+account.getContact());
        orderInfo.setPaymentStatus(PaymentStatusEnum.UNPAID);
        orderInfo.setOrderTime(LocalDateTime.now());
        if(account.getProxyId()!=null && account.getProxyId()!=0){
            ProxyInfoVO proxyInfoVO=proxyService.findById(account.getProxyId());
            orderInfo.setCommissionRate(proxyInfoVO.getCommissionRate());
        }
        orderInfo.setTotalAmount((orderInfo.getAmount1().add(orderInfo.getAmount2()).add(orderInfo.getAmount3())
                .add(orderInfo.getAmount4())).multiply(orderInfo.getDuration()));
        orderInfo.setAnchorNames(customerService.findByIds(ids));
        orderInfo.setSettlementStatus(SettlementStatusEnum.UNSETTLED);
        orderInfo.setCreateName(TokenTools.getUserName());
        orderInfo.setCreateTime(LocalDateTime.now());
        log.info("创建订单参数：{}",orderInfo);
        save(orderInfo);
        LogTools.addLog("创建订单","新增一个订单："+ JSONUtil.toJsonStr(orderInfo),TokenTools.getLoginToken(true));

        settlementInfoService.createSettlement(orderInfo);
    }


}
