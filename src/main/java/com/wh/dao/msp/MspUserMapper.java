package com.wh.dao.msp;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wh.base.BaseMapper;
import com.wh.exception.DAOException;
import com.wh.mspentity.MspUser;
@Repository
public interface MspUserMapper extends BaseMapper<MspUser, String>{
    int deleteByPrimaryKey(String id);

    int insert(MspUser record);

    int insertSelective(MspUser record);

    MspUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MspUser record);

    int updateByPrimaryKey(MspUser record);
    
    int updateUserIdNull(@Param(value="id")String id)throws DAOException;
    /**
     * 根据用户id获取用户详情信息
     * @param userid	主键id
     * @return
     * @throws DAOException
     */
	Map<String, Object> getMyInfo(@Param(value="id")String userid)throws DAOException;
}