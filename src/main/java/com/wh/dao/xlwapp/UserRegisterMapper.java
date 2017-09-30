package com.wh.dao.xlwapp;

import com.wh.entity.UserRegister;

public interface UserRegisterMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRegister record);

    int insertSelective(UserRegister record);

    UserRegister selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRegister record);

    int updateByPrimaryKey(UserRegister record);
}