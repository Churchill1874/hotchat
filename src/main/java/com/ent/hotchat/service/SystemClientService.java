package com.ent.hotchat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ent.hotchat.entity.Account;
import com.ent.hotchat.pojo.req.systemclient.SystemClientAdd;
import com.ent.hotchat.pojo.req.systemclient.SystemClientPage;
import com.ent.hotchat.pojo.req.systemclient.SystemClientUpdate;
import com.ent.hotchat.pojo.req.systemclient.SystemLogin;
import com.ent.hotchat.pojo.resp.systemclient.CaptchaCode;
import com.ent.hotchat.pojo.resp.token.LoginToken;
import com.ent.hotchat.pojo.resp.token.LoginToken;

public interface SystemClientService extends IService<Account> {

    /**
     * 分页查询管理员页面
     * @param dto
     * @return
     */
    IPage<Account> queryPage(SystemClientPage dto);

    /**
     * 新增管理员
     * @param dto
     */
    void add(Account dto);

    /**
     * 编辑管理员
     * @param dto
     */
    void update(Account dto);

    /**
     *
     */
    void updatePassword(Account dto);
    /**
     * 根据用户名查找
     * @param userName
     * @return
     */
    Account findByAccount(String userName);

    /**
     * 根据Id查找
     * @param id
     * @return
     */
    Account findById(Long id);

    /**
     * 登录接口
     * @param dto
     * @return
     */
    LoginToken login(SystemLogin dto);


    CaptchaCode getCaptchaCode();

    void logout();

}
