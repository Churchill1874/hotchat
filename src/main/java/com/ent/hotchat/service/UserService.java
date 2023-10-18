package com.ent.hotchat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ent.hotchat.entity.UserInfo;

import java.util.List;

/**
 * 用户
 */
public interface UserService extends IService<UserInfo> {

    /**
     * 添加用户
     * @param po
     * @return
     */
    boolean add(UserInfo po);

    /**
     * 修改用户状态
     * @return
     */
    boolean updateStatus(UserInfo po);

    /**
     * 修改用户
     * @param po
     * @return
     */
    boolean update(UserInfo po);

    /**
     * 删除用户
     * @param idList
     * @return
     */
    boolean del(List<Long> idList);

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    UserInfo getUser(Long id);

    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    UserInfo findByAccount(String account);

    /**
     * 根据网名查找用户
     * @param name
     * @return
     */
    UserInfo findByName(String name);

    /**
     * 分页查询用户信息
     * @param po
     * @return
     */
    IPage<UserInfo> page(UserPageReq po);

    /**
     * 查询用户列表
     * @param po
     * @return
     */
    List<UserInfo> getList(UserInfo po);


    /**
     * 根据id集合获取用户
     * @param idList
     * @return
     */
    List<UserInfo> findByIds(List<Long> idList);

    /**
     * 获取最大账号值
     * @return
     */
    int maxAccount();

    /**
     * 根据手机号查找用户
     * @param phoneNumber
     */
    UserInfo findByPhoneNumber(String phoneNumber);

}
