package com.wh.service.msp.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wh.base.PageBounds;
import com.wh.dao.msp.dsMapper;
import com.wh.exception.ServiceException;
import com.wh.mspentity.ds;
import com.wh.service.base.impl.BaseServiceImpl;
import com.wh.service.msp.dsService;

@Service
public class dsServiceImpl extends BaseServiceImpl<ds, Integer> implements dsService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	dsMapper dao;

	@Resource
	public void setBaseDAO(dsMapper dsMapper) {
		this.dao = dsMapper;
		this.baseDAO = dsMapper;
	}

	@Override
	public boolean deleteAll()throws ServiceException{
		
		return dao.deleteAll()>0?true:false;
	}

	@Override
	public List<Map<String, Object>> findList1(Map<String, Object> map, PageBounds pageBounds) throws ServiceException {
		
		return dao.findList1(map, pageBounds);
	}
}
