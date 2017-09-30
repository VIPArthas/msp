package com.wh.dao.xlwapp;

import com.wh.entity.UserSourceLog;

public interface UserSourceLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserSourceLog record);

    int insertSelective(UserSourceLog record);

    UserSourceLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserSourceLog record);

    int updateByPrimaryKey(UserSourceLog record);
}