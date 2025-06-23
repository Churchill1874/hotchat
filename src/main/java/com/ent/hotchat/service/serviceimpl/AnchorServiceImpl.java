package com.ent.hotchat.service.serviceimpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ent.hotchat.common.constant.SystemConstant;
import com.ent.hotchat.common.constant.enums.OnlineStatusEnum;
import com.ent.hotchat.common.constant.enums.OrderTypeEnum;
import com.ent.hotchat.common.constant.enums.RoleTypeEnum;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.common.tools.CodeTools;
import com.ent.hotchat.common.tools.GenerateTools;
import com.ent.hotchat.common.tools.LogTools;
import com.ent.hotchat.common.tools.TokenTools;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.entity.Anchor;
import com.ent.hotchat.mapper.AnchorMapper;
import com.ent.hotchat.pojo.req.anchor.AnchorAdd;
import com.ent.hotchat.pojo.req.anchor.AnchorBaseUpdate;
import com.ent.hotchat.pojo.req.anchor.AnchorPage;
import com.ent.hotchat.pojo.resp.anchor.AnchorInfoVO;
import com.ent.hotchat.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AnchorServiceImpl extends ServiceImpl<AnchorMapper, Anchor> implements AnchorService {
    final String CONFIG_KEY="anchor_rate";

    @Autowired
    private AnchorMapper anchorMapper;

    @Autowired
    private EhcacheService ehcacheService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CommonConfigService commonConfigService;


    @Override
    public List<AnchorInfoVO> queryPage() {
        return ehcacheService.anchorListCache().get(SystemConstant.ANCHOR_LIST);
    }


    @Override
    public IPage<AnchorInfoVO> queryAnchorPage(AnchorPage dto) {
        IPage<AnchorInfoVO> iPage=new Page<>(dto.getPageNum(),dto.getPageSize());
        return anchorMapper.selectFullAnchorPage(iPage,RoleTypeEnum.ANCHOR,dto.getUserName(), dto.getNickName(),dto.getStatus(),dto.getOnlineStatus());
    }

    //查询主播列表信息，不分页，查询所有信息
    List<AnchorInfoVO> queryList(){
        return anchorMapper.selectAllAnchors(RoleTypeEnum.ANCHOR);
    }


    //刷新缓存
    void refresh(){
        ehcacheService.anchorListCache().remove(SystemConstant.ANCHOR_LIST);
        ehcacheService.anchorMapCache().remove(SystemConstant.ANCHOR_MAP);
        List<AnchorInfoVO> list = queryList();
        //将查询出来的主播信息存入缓存
        ehcacheService.anchorListCache().put(SystemConstant.ANCHOR_LIST,list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AnchorAdd dto) {
        Account account=new Account();
        Anchor anchor = new Anchor();
        account.setUserName(dto.getUserName());
        account.setNickName(dto.getNickName());
        account.setSalt(GenerateTools.getUUID());
        account.setPassword(CodeTools.md5AndSalt(dto.getPassword(),account.getSalt()));
        account.setContactType(dto.getContactType());
        account.setContact(dto.getContact());
        account.setRoleType(RoleTypeEnum.ANCHOR);
        account.setStatus(StatusEnum.NORMAL);
        account.setCreateName(TokenTools.getUserName());
        account.setCreateTime(LocalDateTime.now());
        customerService.add(account);
        anchor.setAnchorId(account.getId());
        anchor.setAvatar(dto.getAvatar());
        anchor.setVoiceSample(dto.getVoiceSample());
        anchor.setVoicePriceCny(dto.getVoicePriceCny());
        anchor.setVoicePriceUsdt(dto.getVoicePriceUsdt());
        anchor.setVideoPriceCny(dto.getVideoPriceCny());
        anchor.setVideoPriceUsdt(dto.getVideoPriceUsdt());
        anchor.setHeight(dto.getHeight());
        anchor.setWeight(dto.getWeight());
        anchor.setPersonality(dto.getPersonality());
        anchor.setAge(dto.getAge());
        anchor.setEducation(dto.getEducation());
        anchor.setRegion(dto.getRegion());
        anchor.setHobbies(dto.getHobbies());
        anchor.setGoodTopics(dto.getGoodTopics());
        anchor.setRejectTopics(dto.getRejectTopics());
        anchor.setDescription(dto.getDescription());
        BigDecimal commissionRate=new BigDecimal(commonConfigService.getValueByKey(CONFIG_KEY));
        anchor.setCommissionRate(commissionRate);
        anchor.setCreateTime(account.getCreateTime());
        anchor.setCreateName(account.getCreateName());
        save(anchor);
        //更新缓存信息
        refresh();
        LogTools.addLog("主播管理-新增主播","新增了一个主播"+ JSONUtil.toJsonStr(dto),TokenTools.getLoginToken(true));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void baseupdate(AnchorBaseUpdate dto) {
        Account account=customerService.findById(dto.getId());
        account.setNickName(dto.getNickName());
        account.setContactType(dto.getContactType());
        account.setContact(dto.getContact());
        account.setStatus(dto.getStatus());
        customerService.edit(account);
        Anchor anchor=new Anchor();
        anchor.setAvatar(dto.getAvatar());
        anchor.setVoiceSample(dto.getVoiceSample());
        anchor.setVoicePriceCny(dto.getVoicePriceCny());
        anchor.setVoicePriceUsdt(dto.getVoicePriceUsdt());
        anchor.setVideoPriceCny(dto.getVideoPriceCny());
        anchor.setVideoPriceUsdt(dto.getVideoPriceUsdt());
        anchor.setHeight(dto.getHeight());
        anchor.setWeight(dto.getWeight());
        anchor.setPersonality(dto.getPersonality());
        anchor.setAge(dto.getAge());
        anchor.setEducation(dto.getEducation());
        anchor.setRegion(dto.getRegion());
        anchor.setHobbies(dto.getHobbies());
        anchor.setGoodTopics(dto.getGoodTopics());
        anchor.setRejectTopics(dto.getRejectTopics());
        anchor.setDescription(dto.getDescription());
        anchor.setCommissionRate(dto.getCommissionRate());
        anchor.setOnlineStatus(OnlineStatusEnum.OFFLINE);
        UpdateWrapper<Anchor> updateWrapper=new UpdateWrapper<>();
        updateWrapper.lambda()
                        .eq(Anchor::getAnchorId,account.getId());
        update(anchor,updateWrapper);
        refresh();
        LogTools.addLog("主播管理-编辑主播","修改了一个主播"+ JSONUtil.toJsonStr(dto),TokenTools.getLoginToken(true));
    }

    @Override
    public AnchorInfoVO findByAccount(String account) {
        return anchorMapper.selectAnchorByAccount(account,RoleTypeEnum.ANCHOR);
    }

    @Override
    public AnchorInfoVO findById(Long id) {
        return anchorMapper.selectAnchorById(id,RoleTypeEnum.ANCHOR);
    }

    @Override
    public List<AnchorInfoVO> findByIds(String ids) {
        List<String> anchorIds = Arrays.asList(ids.split(","));
        List<Long> idList=anchorIds.stream().map(Long::parseLong).collect(Collectors.toList());
        QueryWrapper<Anchor> wrapper=new QueryWrapper<>();
        wrapper.lambda()
                .in(Anchor::getAnchorId,idList);
        List<Anchor> anchorList = list(wrapper);
        List<AnchorInfoVO> list=new ArrayList<>();
        for(Anchor anchor:anchorList){
            AnchorInfoVO anchorInfoVO = BeanUtil.toBean(anchor, AnchorInfoVO.class);
            anchorInfoVO.setUserName(customerService.findById(anchor.getAnchorId()).getUserName());
            anchorInfoVO.setNickName(customerService.findById(anchor.getAnchorId()).getNickName());
            list.add(anchorInfoVO);
        }
        return list;
    }

    @Override
    public BigDecimal getVedioAmounts(String ids, String currency) {
        List<String> anchorIds = Arrays.asList(ids.split(","));
        List<Long> idList=anchorIds.stream().map(Long::parseLong).collect(Collectors.toList());
        QueryWrapper<Anchor> wrapper=new QueryWrapper<>();
        wrapper.lambda()
                .in(Anchor::getAnchorId,idList);
        List<Anchor> anchorList = list(wrapper);
        BigDecimal amount=BigDecimal.ZERO;
        for(Anchor anchor:anchorList){
            if (currency.equals("CNY")){
                amount.add(anchor.getVideoPriceCny());
            }
            amount.add(anchor.getVideoPriceUsdt());
        }
        return amount;
    }

    @Override
    public BigDecimal getVoiceAmounts(String ids, String currency) {
        List<String> anchorIds = Arrays.asList(ids.split(","));
        List<Long> idList=anchorIds.stream().map(Long::parseLong).collect(Collectors.toList());
        QueryWrapper<Anchor> wrapper=new QueryWrapper<>();
        wrapper.lambda()
                .in(Anchor::getAnchorId,idList);
        List<Anchor> anchorList = list(wrapper);
        BigDecimal amount=BigDecimal.ZERO;
        for(Anchor anchor:anchorList){
            if (currency.equals("CNY")){
                amount.add(anchor.getVoicePriceCny());
            }
            amount.add(anchor.getVoicePriceUsdt());
        }
        return amount;
    }


}
