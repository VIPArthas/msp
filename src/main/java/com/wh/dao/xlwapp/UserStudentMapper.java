package com.wh.dao.xlwapp;

import com.wh.entity.UserStudent;

import java.util.List;
import java.util.Map;

public interface UserStudentMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserStudent record);

    int insertSelective(UserStudent record);

    UserStudent selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserStudent record);

    int updateByPrimaryKey(UserStudent record);

    UserStudent selectByUserId(String userId);

    void updateByUserId(UserStudent userStudent);

    int deleteByUserId(String userId);

    UserStudent selectBySchoolName(String schoolName);
    Map<String,Object> selectByLikeName(String selectByLikeName);
}