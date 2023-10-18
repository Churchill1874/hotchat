package com.ent.hotchat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ent.hotchat.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<UserInfo> {

    @Select("select max(account) as maxAccount from user")
    int maxCount();

}
