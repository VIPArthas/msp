package com.wh.dao.msp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wh.base.BaseMapper;
import com.wh.exception.DAOException;
import com.wh.mspentity.MspReply;

@Repository
public interface MspReplyMapper extends BaseMapper<MspReply, Integer> {
	int deleteByPrimaryKey(Integer id);

	int insert(MspReply record);

	int insertSelective(MspReply record);

	MspReply selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(MspReply record);

	int updateByPrimaryKey(MspReply record);

	/**
	 * 根据photoId获取评论信息
	 * 
	 * @param photoId
	 * @return
	 * @throws DAOException
	 */
	List<Map<String, Object>> getReplyListByPhotoId(@Param(value = "photoId") String photoId) throws DAOException;
	/**
	 * 根据photoId删除评论信息
	 * @param photoId
	 * @return
	 * @throws DAOException
	 */
	int deleteByphotoId(@Param(value = "photoId") String photoId) throws DAOException;
}