package com.ent.hotchat.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ent.hotchat.entity.Anchor;
import com.ent.hotchat.pojo.req.anchor.AnchorAdd;
import com.ent.hotchat.pojo.req.anchor.AnchorBaseUpdate;
import com.ent.hotchat.pojo.req.anchor.AnchorPage;
import com.ent.hotchat.pojo.resp.anchor.AnchorInfoVO;

import java.util.List;
import java.util.Map;

public interface AnchorService extends IService<Anchor> {
    /**
     * 查询主播列表信息，前台获取，不分页
     * @return
     */
    List<AnchorInfoVO> queryPage();

    /**
     * 查询主播列表,仅查询头像和昵称
     * @return
     */
    Map<String,String> queryAnchorList();

    /**
     * 后端分页查询主播列表
     * @param dto
     * @return
     */
    IPage<AnchorInfoVO> queryAnchorPage(AnchorPage dto);

    /**
     * 新增主播
     * @param dto
     */
    void add(AnchorAdd dto);

    /**
     * 编辑主播
     * @param dto
     */
    void baseupdate(AnchorBaseUpdate dto);


    /**
     * 根据主播账号查找主播信息
     * @param account
     * @return
     */
    AnchorInfoVO findByAccount(String account);

    /**
     * 根据主播Id查询账号信息
     * @param id
     * @return
     */
    AnchorInfoVO findById(Long id);







}
