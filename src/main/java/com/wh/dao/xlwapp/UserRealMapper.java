package com.wh.dao.xlwapp;

import com.wh.entity.UserReal;

import java.util.List;

public interface UserRealMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserReal record);

    /**
     * 添加一条userReal数据
     * @author 王鹏翔
     * @Date 2017年1月9日  下午1:10:22
     * @param record
     * @return
     */
    int insertSelective(UserReal record);

    UserReal selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserReal record);

    int updateByPrimaryKey(UserReal record);

    /**
     * 根据userid查询用户真是信息
     * @author 王鹏翔
     * @Date 2017年1月9日  下午1:10:27
     * @param userId
     * @return
     */
    UserReal selectByUserId(String userId);

    void updateByUserId(UserReal userReal);


    int deleteByUserId(String userId);
}