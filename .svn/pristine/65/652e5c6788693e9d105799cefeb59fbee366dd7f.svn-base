package com.wh.service.msp.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.wh.dao.msp.MspReplyMapper;
import com.wh.exception.ServiceException;
import com.wh.mspentity.MspReply;
import com.wh.service.base.impl.BaseServiceImpl;
import com.wh.service.msp.MspReplyService;

@Service
public class MspReplyServiceImpl extends BaseServiceImpl<MspReply, Integer> implements MspReplyService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	MspReplyMapper dao;

	@Resource
	public void setBaseDAO(MspReplyMapper mspReplyMapper) {
		this.dao = mspReplyMapper;
		this.baseDAO = mspReplyMapper;
	}

	@Override
	public List<Map<String, Object>> getReplyListByPhotoId(String photoId) throws ServiceException {

		return dao.getReplyListByPhotoId(photoId);
	}

	@Override
	public boolean deleteByphotoId(String photoId) throws ServiceException {

		return dao.deleteByphotoId(photoId) > 0 ? true : false;
	}

}
