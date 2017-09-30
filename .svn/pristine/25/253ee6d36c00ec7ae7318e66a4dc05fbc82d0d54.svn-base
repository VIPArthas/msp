package com.wh.service.wmh.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.dao.wmh.WmhTagUseMapper;
import com.wh.entity.WmhTagUse;
import com.wh.service.wmh.WmhTagUseService;

/** 
 * @param 
 * @author 王鹏翔 
 * @date 2017年3月8日 上午10:34:07 
 * @return 
 */
@Service
public class WmhTagUseServiceImpl implements WmhTagUseService {

	@Autowired
	private WmhTagUseMapper wmhTagUseMapper;
	
	@Override
	public int insertSelective(WmhTagUse record) {
		return wmhTagUseMapper.insertSelective(record);
	}

	@Override
	public WmhTagUse selectTagByTagName(String tagName) throws Exception {
		return wmhTagUseMapper.selectTagByTagName(tagName);
	}

	@Override
	public int updateByPrimaryKeySelective(WmhTagUse record) throws Exception {
		return wmhTagUseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<WmhTagUse> selectCommonUseTagList() throws Exception {
		return this.wmhTagUseMapper.selectCommonUseTagList();
	}

}
