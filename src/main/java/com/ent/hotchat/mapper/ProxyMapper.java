package com.ent.hotchat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ent.hotchat.common.constant.enums.RoleTypeEnum;
import com.ent.hotchat.common.constant.enums.StatusEnum;
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
    @Select({"<script>",
            "SELECT",
            "    a.id,",
            "    a.user_name AS userName,",
            "    a.nick_name AS nickName,",
            "    a.contact_type AS contactType,",
            "    a.contact,",
            "    a.last_login_time AS lastLoginTime,",
            "    a.status,",
            "    p.commission_rate,",
            "    a.create_time AS createTime, ",
            "    a.create_name AS createName, ",
            "    a.user_name AS proxyName,",
            "    a.proxy_domain AS proxyDomain,",
            "    (SELECT COUNT(*) FROM account WHERE proxy_id = a.id) AS totalUsers",
            "FROM account a",
            "LEFT JOIN proxy_info p ON p.proxy_id = a.id",
            "<where>",
            "    a.role_type = #{roleType}",
            "    <if test='userName != null and userName != \"\"'>",
            "        AND a.user_name LIKE CONCAT('%', #{userName}, '%')",
            "    </if>",
            "    <if test='status != null'>",
            "        AND a.status = #{status}",
            "    </if>",
            "</where>",
            "ORDER BY a.create_time DESC",
            "</script>"})
    IPage<ProxyInfoVO> selectProxyPage(IPage<ProxyInfoVO> page,@Param("roleType") RoleTypeEnum roleType, @Param("userName") String userName,
                                       @Param("status") StatusEnum status);

    /**
     * 根据用户名查询代理信息（包含下属用户数量统计）
     * @param username 用户名
     * @return 代理信息
     */

    @Select("SELECT p.*, a.user_name, a.nick_name, a.contact_type, a.contact, a.role_type, a.status,a.create_time, a.create_name," +
            "(SELECT COUNT(*) FROM account WHERE proxy_id = p.id) AS total_users " +
            "FROM proxy_info p " +
            "JOIN account a ON p.proxy_id = a.id " +
            "WHERE a.user_name = #{username} AND a.role_type = #{roleType}")
    ProxyInfoVO selectProxyByAccount(@Param("username") String username, @Param("roleType") RoleTypeEnum roleType);

    /**
     * 根据代理id查询代理信息（包含下属用户数量统计）
     * @param id 代理id
     * @return 代理信息
     */
    @Select("SELECT p.*, a.user_name, a.nick_name, a.contact_type, a.contact, a.role_type, a.status,a.create_time, a.create_name," +
            "(SELECT COUNT(*) FROM account WHERE proxy_id = p.id) AS total_users " +
            "FROM proxy_info p " +
            "JOIN account a ON p.proxy_id = a.id " +
            "WHERE a.id = #{id} AND a.role_type = #{roleType}")
    ProxyInfoVO selectProxyById(@Param("id") Long id, @Param("roleType") RoleTypeEnum roleType);

}


