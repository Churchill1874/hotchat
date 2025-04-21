package com.ent.hotchat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ent.hotchat.common.constant.enums.OnlineStatusEnum;
import com.ent.hotchat.common.constant.enums.RoleTypeEnum;
import com.ent.hotchat.common.constant.enums.StatusEnum;
import com.ent.hotchat.entity.Anchor;
import com.ent.hotchat.pojo.req.anchor.AnchorPage;
import com.ent.hotchat.pojo.resp.anchor.AnchorInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AnchorMapper extends BaseMapper<Anchor> {
    /**
     * 获取所有主播信息列表
     * @return 主播信息列表
     */
    @Select("SELECT " +
            "a.id, " +
            "a.user_name as userName, " +
            "a.nick_name as nickName, " +
            "a.contact_type as contactType, " +
            "a.contact, " +
            "an.avatar, " +
            "an.voice_sample as voiceSample, " +
            "an.voice_price_cny as voicePriceCny, " +
            "an.voice_price_usdt as voicePriceUsdt, " +
            "an.video_price_cny as videoPriceCny, " +
            "an.video_price_usdt as videoPriceUsdt, " +
            "an.height, " +
            "an.weight, " +
            "an.personality, " +
            "an.age, " +
            "an.education, " +
            "an.region, " +
            "an.hobbies, " +
            "an.good_topics as goodTopics, " +
            "an.reject_topics as rejectTopics, " +
            "an.description, " +
            "an.commission_rate as commissionRate, " +
            "an.online_status as onlineStatus " +
            "FROM account a " +
            "JOIN anchor_info an ON a.id = an.anchor_id " +
            "WHERE a.role_type = #{roleType} " +
            "ORDER BY a.create_time DESC")
    List<AnchorInfoVO> selectAllAnchors(@Param("roleType") RoleTypeEnum roleType);

    /**
     * 完整分页查询主播列表
     * @param page 分页参数
     * @return 分页结果
     */
    @Select({
            "<script>",
            "SELECT",
            "    a.id AS id,",
            "    a.user_name AS userName,",
            "    a.nick_name AS nickName,",
            "    a.contact_type AS contactType,",
            "    a.contact AS contact,",
            "    b.avatar,",
            "    a.create_time AS createTime,",
            "    a.create_name AS createName,",
            "    b.voice_sample AS voiceSample,",
            "    b.voice_price_cny AS voicePriceCny,",
            "    b.voice_price_usdt AS voicePriceUsdt,",
            "    b.video_price_cny AS videoPriceCny,",
            "    b.video_price_usdt AS videoPriceUsdt,",
            "    b.height,",
            "    b.weight,",
            "    b.personality,",
            "    b.age,",
            "    b.education,",
            "    b.region,",
            "    b.hobbies,",
            "    b.good_topics AS goodTopics,",
            "    b.reject_topics AS rejectTopics,",
            "    b.description,",
            "    b.commission_rate AS commissionRate,",
            "    b.online_status AS onlineStatus",
            "FROM account a",
            "JOIN anchor_info b ON a.id = b.anchor_id",
            "<where>",
            "    a.role_type = #{roleType}",
            "    <if test='userName != null and userName != \"\"'>",
            "        AND a.user_name LIKE CONCAT('%', #{userName}, '%')",
            "    </if>",
            "    <if test='nickName != null and nickName != \"\"'>",
            "        AND a.nick_name LIKE CONCAT('%', #{nickName}, '%')",
            "    </if>",
            "    <if test='status != null'>",
            "        AND a.status = #{status}",
            "    </if>",
            "    <if test='onlineStatus != null'>",
            "        AND b.online_status = #{onlineStatus}",
            "    </if>",
            "</where>",
            "ORDER BY a.create_time DESC",
            "</script>"
    })
    IPage<AnchorInfoVO> selectFullAnchorPage(IPage<AnchorInfoVO> page,@Param("roleType") RoleTypeEnum roleType,@Param("userName") String userName,@Param("nickName") String nickName,@Param("status") StatusEnum status,@Param("onlineStatus") OnlineStatusEnum onlineStatus);

    /**
     * 根据用户名查询主播信息
     * @param username 用户名
     * @param roleType 角色类型(固定为主播类型)
     * @return 主播信息
     */
    @Select("SELECT a.*, ai.* " +
            "FROM account a " +
            "JOIN anchor_info ai ON a.id = ai.anchor_id " +
            "WHERE a.user_name = #{username} AND a.role_type = #{roleType}")
    AnchorInfoVO selectAnchorByAccount(@Param("username") String username,
                                    @Param("roleType") RoleTypeEnum roleType);

    /**
     * 根据ID查询主播信息
     * @param anchorId 主播ID
     * @param roleType 角色类型(固定为主播类型)
     * @return 主播信息
     */
    @Select("SELECT a.*, ai.* " +
            "FROM account a " +
            "JOIN anchor_info ai ON a.id = ai.anchor_id " +
            "WHERE a.id = #{anchorId} AND a.role_type = #{roleType}")
    AnchorInfoVO selectAnchorById(@Param("anchorId") Long anchorId,
                              @Param("roleType") RoleTypeEnum roleType);
}

