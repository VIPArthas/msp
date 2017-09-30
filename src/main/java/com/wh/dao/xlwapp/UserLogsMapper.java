package com.wh.dao.xlwapp;

import com.wh.dto.SourceDto;
import com.wh.entity.UserLogs;

import java.util.List;
import java.util.Map;

public interface UserLogsMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserLogs record);

    int insertSelective(UserLogs record);

    UserLogs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserLogs record);

    int updateByPrimaryKey(UserLogs record);
    
    UserLogs queryLastLog(String sessionId);

    List<UserLogs> queryUserLogsByOpenId(UserLogs userLogs);

    /**
     * 更新用户搜索的历史
     * @param userLogs
     */
    void updateHistoryByOpenId(UserLogs userLogs);
    
    /**
     * 根据openid和userid查询活跃用户数
     * @param sourceDto
     * @return
     * @author 王鹏翔
     * @date 2016年8月19日
     */
    List<Map<Object, Object>> selectAtCount(SourceDto sourceDto);

    /**
     * 活跃用户统计，数据库中暂时就有一个服务号，就只统计user_logs表，不关联其他表。
     * @param sourceDto
     * @return
     */
    List<Map<String, Object>> selectActiveOpenIdCount(SourceDto sourceDto);
}