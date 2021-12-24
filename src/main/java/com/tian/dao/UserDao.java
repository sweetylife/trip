package com.tian.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tian.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
