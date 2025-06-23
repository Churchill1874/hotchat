package com.ent.hotchat.service.serviceimpl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ent.hotchat.common.constant.enums.SettlementStatusEnum;
import com.ent.hotchat.common.tools.GenerateTools;
import com.ent.hotchat.entity.OrderInfo;
import com.ent.hotchat.entity.SettlementInfo;
import com.ent.hotchat.mapper.SettlementInfoMapper;
import com.ent.hotchat.service.AnchorService;
import com.ent.hotchat.service.SettlementInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SettlementInfoServiceImpl extends ServiceImpl<SettlementInfoMapper, SettlementInfo> implements SettlementInfoService {


    @Autowired
    private AnchorService anchorService;

    @Override
    public void createSettlement(OrderInfo orderInfo) {
        log.info("传入结算单订单参数：{}",orderInfo);
        List<Long> ids=new ArrayList<>();
        //根据传入的主播id，获取主播昵称
        if(orderInfo.getAnchorId1()!=null && orderInfo.getAnchorId1()!=0){
            ids.add(orderInfo.getAnchorId1());
        }
        if(orderInfo.getAnchorId2()!=null && orderInfo.getAnchorId2()!=0){
            ids.add(orderInfo.getAnchorId2());
        }
        if(orderInfo.getAnchorId3()!=null && orderInfo.getAnchorId3()!=0){
            ids.add(orderInfo.getAnchorId3());
        }
        if(orderInfo.getAnchorId4()!=null && orderInfo.getAnchorId4()!=0){
            ids.add(orderInfo.getAnchorId4());
        }
        List<SettlementInfo> list=new ArrayList<>();
        for(Long id:ids){
            SettlementInfo settlementInfo=BeanUtil.toBean(orderInfo,SettlementInfo.class);
            settlementInfo.setAnchorId(id);
            if(id.equals(orderInfo.getAnchorId1())){
                settlementInfo.setAnchorName(orderInfo.getAnchorName1());
                settlementInfo.setAmount(orderInfo.getAmount1());
            }
            if(id.equals(orderInfo.getAnchorId2())){
                settlementInfo.setAnchorName(orderInfo.getAnchorName2());
                settlementInfo.setAmount(orderInfo.getAmount2());
            }
            if(id.equals(orderInfo.getAnchorId3())){
                settlementInfo.setAnchorName(orderInfo.getAnchorName3());
                settlementInfo.setAmount(orderInfo.getAmount3());
            }
            if(id.equals(orderInfo.getAnchorId4())){
                settlementInfo.setAnchorName(orderInfo.getAnchorName4());
                settlementInfo.setAmount(orderInfo.getAmount4());
            }
            settlementInfo.setTotalAmount(settlementInfo.getAmount().multiply(settlementInfo.getDuration()));
            settlementInfo.setSettlementStatus(SettlementStatusEnum.UNSETTLED);
            settlementInfo.setCommissionRate(anchorService.findById(id).getCommissionRate());
            list.add(settlementInfo);
        }
        log.info("结算单明细参数：{}",list);
        saveBatch(list);
    }
}
