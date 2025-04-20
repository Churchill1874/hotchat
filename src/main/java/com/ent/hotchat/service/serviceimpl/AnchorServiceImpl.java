package com.ent.hotchat.service.serviceimpl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ent.hotchat.common.constant.SystemConstant;
import com.ent.hotchat.common.constant.enums.OnlineStatusEnum;
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
import com.ent.hotchat.pojo.req.anchor.AnchorOnlineStatusUpdate;
import com.ent.hotchat.pojo.req.anchor.AnchorPage;
import com.ent.hotchat.pojo.resp.anchor.AnchorInfoVO;
import com.ent.hotchat.service.AnchorService;
import com.ent.hotchat.service.CustomerService;
import com.ent.hotchat.service.EhcacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnchorServiceImpl extends ServiceImpl<AnchorMapper, Anchor> implements AnchorService {
    @Autowired
    private AnchorMapper anchorMapper;

    @Autowired
    private EhcacheService ehcacheService;

    @Autowired
    private CustomerService customerService;

    @Override
    public List<AnchorInfoVO> queryPage() {
        return ehcacheService.anchorListCache().get(SystemConstant.ANCHOR_LIST);
    }

    @Override
    public Map<String, String> queryAnchorList() {
        return ehcacheService.anchorMapCache().get(SystemConstant.ANCHOR_MAP);
    }

    @Override
    public IPage<AnchorInfoVO> queryAnchorPage(AnchorPage dto) {
        IPage<AnchorInfoVO> iPage=new Page<>(dto.getPageNum(),dto.getPageSize());
        return anchorMapper.selectFullAnchorPage(iPage,dto);
    }

    //查询主播列表信息，不分页，查询所有信心
    List<AnchorInfoVO> queryList(){
        return anchorMapper.selectAllAnchors(RoleTypeEnum.ANCHOR);
    }

    //查询主播列表信息，仅查昵称和头像
    Map<String,String> queryMap(){
        List<AnchorInfoVO> list = anchorMapper.selectAllAnchors(RoleTypeEnum.ANCHOR);
        Map<String,String> map=new HashMap<>();
        if(CollectionUtils.isEmpty(list)){
            return map;
        }
        for(AnchorInfoVO anchorInfoVO:list){
            map.put(anchorInfoVO.getNickName(),anchorInfoVO.getAvatar());
        }
        return map;
    }

    //刷新缓存
    void refresh(){
        ehcacheService.anchorListCache().remove(SystemConstant.ANCHOR_LIST);
        ehcacheService.anchorMapCache().remove(SystemConstant.ANCHOR_MAP);
        List<AnchorInfoVO> list = queryList();
        Map<String, String> map = queryMap();
        //将查询出来的主播信息存入缓存
        ehcacheService.anchorListCache().put(SystemConstant.ANCHOR_LIST,list);
        ehcacheService.anchorMapCache().put(SystemConstant.ANCHOR_MAP,map);
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
        account.setRoleType(RoleTypeEnum.PROXY);
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
        anchor.setCommissionRate(dto.getCommissionRate());
        anchor.setOnlineStatus(OnlineStatusEnum.OFFLINE);
        anchor.setTotalOrders(0);
        save(anchor);
        //更新缓存信息
        refresh();
        LogTools.addLog("主播管理-新增主播","新增了一个主播"+ JSONUtil.toJsonStr(dto),TokenTools.getLoginToken(true));
    }

    @Override
    public void baseupdate(AnchorBaseUpdate dto) {

    }

    @Override
    public void onlineStatusUpdate(AnchorOnlineStatusUpdate dto) {

    }

    @Override
    public AnchorInfoVO findByAccount(String account) {
        return null;
    }

    @Override
    public AnchorInfoVO findById(Long id) {
        return null;
    }
}
