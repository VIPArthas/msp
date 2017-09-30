package com.wh.dao.msp;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wh.base.BaseMapper;
import com.wh.exception.DAOException;
import com.wh.mspentity.MspAttachements;

@Repository
public interface MspAttachementsMapper extends BaseMapper<MspAttachements, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(MspAttachements record);

    int insertSelective(MspAttachements record);

    MspAttachements selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MspAttachements record);

    int updateByPrimaryKey(MspAttachements record);
    
    /**
     * 根据linkId获取 photoUrl
     * @param linkId
     * @return
     * @throws DAOException
     */
	List<String> getPhotoUrlListByLinkId(@Param(value="linkId")String linkId)throws DAOException;

	int deleteByLinkId(@Param(value="linkId")String linkId)throws DAOException;
}