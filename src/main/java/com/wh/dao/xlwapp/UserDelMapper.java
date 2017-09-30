package com.wh.dao.xlwapp;

import com.wh.entity.UserDel;

public interface UserDelMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserDel record);

    int insertSelective(UserDel record);

    UserDel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserDel record);

    int updateByPrimaryKey(UserDel record);
}