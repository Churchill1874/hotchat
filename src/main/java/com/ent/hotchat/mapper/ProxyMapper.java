package com.ent.hotchat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ent.hotchat.common.constant.enums.RoleTypeEnum;
import com.ent.hotchat.entity.Proxy;
import com.ent.hotchat.pojo.resp.proxy.ProxyInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ProxyMapper extends BaseMapper<Proxy> {
    /**
     * 分页查询所有代理信息（包含下属用户数量统计）
     * @param page 分页参数
     * @return 分页结果
     */
    @Select("SELECT p.*, a.user_name, a.nick_name, a.contact_type, a.contact, a.role_type, a.status, " +
            "(SELECT COUNT(*) FROM account WHERE proxy_id = p.id) AS total_users " +
            "FROM proxy_info p " +
            "JOIN account a ON p.proxy_id = a.id " +
            "WHERE a.role_type = #{roleType} " +
            "ORDER BY p.create_time DESC")
    IPage<ProxyInfoVO> selectProxyPage(IPage<ProxyInfoVO> page, @Param("roleType") RoleTypeEnum roleType);

    /**
     * 根据用户名查询代理信息（包含下属用户数量统计）
     * @param username 用户名
     * @return 代理信息
     */
    @Select("SELECT p.*, a.user_name, a.nick_name, a.contact_type, a.contact, a.role_type, a.status, " +
            "(SELECT COUNT(*) FROM account WHERE proxy_id = p.id) AS total_users " +
            "FROM proxy_info p " +
            "JOIN account a ON p.proxy_id = a.id " +
            "WHERE a.username = #{username} AND a.role_type = #{roleType}")
    ProxyInfoVO selectProxyByAccount(@Param("username") String username, @Param("roleType") RoleTypeEnum roleType);

    /**
     * 根据代理id查询代理信息（包含下属用户数量统计）
     * @param id 代理id
     * @return 代理信息
     */
    @Select("SELECT p.*, a.user_name, a.nick_name, a.contact_type, a.contact, a.role_type, a.status, " +
            "(SELECT COUNT(*) FROM account WHERE proxy_id = p.id) AS total_users " +
            "FROM proxy_info p " +
            "JOIN account a ON p.proxy_id = a.id " +
            "WHERE a.id = #{id} AND a.role_type = #{roleType}")
    ProxyInfoVO selectProxyById(@Param("id") Long id, @Param("roleType") RoleTypeEnum roleType);

}


