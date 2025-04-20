package com.ent.hotchat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ent.hotchat.common.constant.enums.RoleTypeEnum;
import com.ent.hotchat.entity.Anchor;
import com.ent.hotchat.pojo.req.anchor.AnchorPage;
import com.ent.hotchat.pojo.resp.anchor.AnchorInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AnchorMapper extends BaseMapper<Anchor> {
    /**
     * 获取所有主播信息列表（包含账户基础信息）
     * @return 主播信息列表
     */
    @Select("SELECT a.*, ai.* " +
            "FROM account a " +
            "JOIN anchor_info ai ON a.id = ai.anchor_id " +
            "WHERE a.role_type = #{roleType} " +
            "ORDER BY ai.total_orders DESC")
    List<AnchorInfoVO> selectAllAnchors(@Param("roleType") RoleTypeEnum roleType);

    /**
     * 完整分页查询主播列表
     * @param page 分页参数
     * @param dto 查询条件
     * @return 分页结果
     */
    @Select("<script>" +
            "SELECT a.*, ai.* " +
            "FROM account a " +
            "JOIN anchor_info ai ON a.id = ai.anchor_id " +
            "WHERE a.role_type = #{dto.roleType} " +
            "<if test='dto.username != null'> AND a.user_name LIKE CONCAT('%', #{dto.username}, '%') </if>" +
            "<if test='dto.nickname != null'> AND a.nick_name LIKE CONCAT('%', #{dto.nickname}, '%') </if>" +
            "<if test='dto.status != null'> AND a.status = #{dto.status} </if>" +
            "<if test='dto.onlineStatus != null'> AND ai.online_status = #{dto.onlineStatus} </if>" +
            "ORDER BY ai.total_orders DESC" +
            "</script>")
    IPage<AnchorInfoVO> selectFullAnchorPage(IPage<AnchorInfoVO> page, @Param("dto") AnchorPage dto);
}
