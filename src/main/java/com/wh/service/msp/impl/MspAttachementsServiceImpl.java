package com.wh.service.msp.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.wh.dao.msp.MspAttachementsMapper;
import com.wh.exception.ServiceException;
import com.wh.mspentity.MspAttachements;
import com.wh.service.base.impl.BaseServiceImpl;
import com.wh.service.msp.MspAttachementsService;

@Service
public class MspAttachementsServiceImpl extends BaseServiceImpl<MspAttachements, Integer>
		implements MspAttachementsService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	MspAttachementsMapper dao;

	@Resource
	public void setBaseDAO(MspAttachementsMapper mspAttachementsMapper) {
		this.dao = mspAttachementsMapper;
		this.baseDAO = mspAttachementsMapper;
	}

	@Override
	public List<String> getPhotoUrlListByLinkId(String linkId) throws ServiceException {

		return dao.getPhotoUrlListByLinkId(linkId);
	}

	@Override
	public boolean deleteByLinkId(String linkId) throws ServiceException {

		return dao.deleteByLinkId(linkId) > 0 ? true : false;
	}

}
