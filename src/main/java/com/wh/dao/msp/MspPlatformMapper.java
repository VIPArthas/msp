package com.wh.dao.msp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wh.base.BaseMapper;
import com.wh.exception.DAOException;
import com.wh.mspentity.MspPlatform;

@Repository
public interface MspPlatformMapper extends BaseMapper<MspPlatform, Integer>{
    int deleteByPrimaryKey(String id);

    int insert(MspPlatform record);

    int insertSelective(MspPlatform record);

    MspPlatform selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MspPlatform record);

    int updateByPrimaryKey(MspPlatform record);

	List<MspPlatform> queryAll() throws DAOException;
}