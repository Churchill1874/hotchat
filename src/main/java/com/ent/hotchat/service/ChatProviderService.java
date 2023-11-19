package com.ent.hotchat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ent.hotchat.entity.ChatProvider;

public interface ChatProviderService extends IService<ChatProvider> {

    /**
     * 分页查询陪聊员
     * @param number
     * @param size
     * @return
     */
    IPage<ChatProvider> page(Integer number,Integer size);


}
