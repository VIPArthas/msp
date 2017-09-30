package com.wh.dao.msp;

import org.springframework.stereotype.Repository;

import com.wh.base.BaseMapper;
import com.wh.mspentity.MspAdmin;

@Repository
public interface MspAdminMapper extends BaseMapper<MspAdmin, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(MspAdmin record);

    int insertSelective(MspAdmin record);

    MspAdmin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MspAdmin record);

    int updateByPrimaryKey(MspAdmin record);
}