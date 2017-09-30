package com.wh.service.msp.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wh.base.PageBounds;
import com.wh.dao.msp.MspPhotoMapper;
import com.wh.dao.msp.MspReplyMapper;
import com.wh.exception.ServiceException;
import com.wh.mspentity.MspPhoto;
import com.wh.mspentity.MspReply;
import com.wh.service.base.impl.BaseServiceImpl;
import com.wh.service.msp.MspPhotoService;
import com.wh.service.msp.MspReplyService;

@Service
public class MspPhotoServiceImpl extends BaseServiceImpl<MspPhoto, String> implements MspPhotoService {

	@Resource
	MspPhotoMapper dao;

	@Resource
	public void setBaseDAO(MspPhotoMapper mspPhotoMapper) {
		this.dao = mspPhotoMapper;
		this.baseDAO = mspPhotoMapper;
	}


	
	
	
	
	
	

	@Override
	public Map<String, Object> getShowInfoByPhotoId(String photoId) throws ServiceException {

		return dao.getShowInfoByPhotoId(photoId);
	}

	@Override
	public boolean updatePageviewNum(String photoId) {

		return dao.updatePageviewNum(photoId) > 0 ? true : false;
	}









	@Override
	public List<Map<String, Object>> showList(Integer type, PageBounds pages) throws ServiceException {
		
		return dao.showList(type,pages);
	}

}
