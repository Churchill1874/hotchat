package com.ent.hotchat.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ent.hotchat.entity.ChatProvider;
import com.ent.hotchat.mapper.ChatProviderMapper;
import com.ent.hotchat.service.ChatProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatProviderServiceImpl extends ServiceImpl<ChatProviderMapper, ChatProvider> implements ChatProviderService {

    @Override
    public IPage<ChatProvider> page(Integer number, Integer size) {
        IPage<ChatProvider> iPage = new Page<>(number,size);
        QueryWrapper<ChatProvider> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .orderByAsc(ChatProvider::getStatus)
                .orderByDesc(ChatProvider::getLevel);
        return page(iPage,queryWrapper);
    }

}
