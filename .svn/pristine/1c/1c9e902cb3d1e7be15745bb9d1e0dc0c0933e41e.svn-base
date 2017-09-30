package com.wh.service.msp.impl;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.wh.dao.msp.MspMessageMapper;
import com.wh.mspentity.MspMessage;
import com.wh.service.base.impl.BaseServiceImpl;
import com.wh.service.msp.MspMessageService;

@Service
public class MspMessageServiceImpl extends BaseServiceImpl<MspMessage, Integer> implements MspMessageService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	MspMessageMapper dao;

	@Resource
	public void setBaseDAO(MspMessageMapper mspMessageMapper) {
		this.dao = mspMessageMapper;
		this.baseDAO = mspMessageMapper;
	}
}
