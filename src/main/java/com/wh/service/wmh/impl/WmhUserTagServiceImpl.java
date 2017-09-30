package com.wh.service.wmh.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.dao.wmh.WmhUserTagMapper;
import com.wh.entity.WmhUserTag;
import com.wh.service.wmh.WmhUserTagService;

/** 
 * @param 
 * @author 王鹏翔 
 * @date 2017年3月8日 上午10:05:51 
 * @return 
 */
@Service
public class WmhUserTagServiceImpl implements WmhUserTagService {

	@Autowired
	private WmhUserTagMapper wmhUserTagMapper;
	
	
	@Override
	public int deleteUserTagByUserId(String userId) {
		return this.wmhUserTagMapper.deleteUserTagByUserId(userId);
	}


	@Override
	public int insertSelective(WmhUserTag record) {
		return this.wmhUserTagMapper.insertSelective(record);
	}

	//根据标签id查询使用该标签的所有用户
	@Override
	public List<WmhUserTag> searchTagById(String tagId) throws Exception {
		List<WmhUserTag> list=wmhUserTagMapper.searchTagById(tagId);
		return list;
	}


	@Override
	public List<Map<String, Object>> selectUserTagList(String userId) {
		return this.wmhUserTagMapper.selectUserTagList(userId);
	}


	@Override
	public int deleteByPrimaryKey(String id) {
		return this.wmhUserTagMapper.deleteByPrimaryKey(id);
	}


	@Override
	public WmhUserTag selectUserTagByCondition(WmhUserTag userTag) throws Exception {
		return this.wmhUserTagMapper.selectUserTagByCondition(userTag);
	}

	
}
