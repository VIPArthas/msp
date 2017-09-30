package com.wh.dao.wchat;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wh.entity.TbPlatform;

public interface TbPlatformMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbPlatform record);

    TbPlatform selectByPrimaryKey(String id);

    int updateByPrimaryKey(TbPlatform record);
    
    /**
     * 查询所有微信平台信息
     * @return
     */
     List<TbPlatform> queryAll();
    /**
     * 根据平台Id查询平台相关信息
     * @param id
     * @return
     */
     TbPlatform queryById(@Param("id")String id);
    /**
     * 根据平台AppId查询平台相关信息
     * @param appId
     * @return
     */
     TbPlatform queryByAppId(@Param("appid")String appid);
    /**
     * 根据平台Id查询平台相关信息
     * @param accout
     * @return
     */
     TbPlatform queryByAccout(@Param("accout")String accout);
}