package com.wh.dao.wmh;

import com.wh.base.BaseMapper;
import com.wh.entity.WmhFaultLog;


public interface WmhFaultLogMapper extends BaseMapper<WmhFaultLog, Integer>{
    int deleteByPrimaryKey(String id);

    int insert(WmhFaultLog record);

    int insertSelective(WmhFaultLog record);

    WmhFaultLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WmhFaultLog record);

    int updateByPrimaryKey(WmhFaultLog record);
}