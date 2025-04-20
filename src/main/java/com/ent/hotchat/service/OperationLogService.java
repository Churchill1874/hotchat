package com.ent.hotchat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ent.hotchat.entity.OperationLog;
import com.ent.hotchat.pojo.BasePage;

public interface OperationLogService extends IService<OperationLog> {

    IPage<OperationLog> queryPage(BasePage dto);

    void add(OperationLog dto);

}
