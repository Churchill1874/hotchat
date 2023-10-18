package com.ent.hotchat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ent.hotchat.entity.Blacklist;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlacklistMapper extends BaseMapper<Blacklist> {
}
