package com.wh.dao.msp;

import org.springframework.stereotype.Repository;

import com.wh.base.BaseMapper;

import com.wh.mspentity.MspDepartment;
@Repository
public interface MspDepartmentMapper extends BaseMapper<MspDepartment, String>{
    int deleteByPrimaryKey(String id);

    int insert(MspDepartment record);

    int insertSelective(MspDepartment record);

    MspDepartment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MspDepartment record);

    int updateByPrimaryKey(MspDepartment record);
}