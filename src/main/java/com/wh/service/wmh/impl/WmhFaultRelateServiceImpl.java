package com.wh.service.wmh.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wh.base.PageBounds;
import com.wh.dao.wmh.WmhFaultRelateMapper;
import com.wh.entity.WmhFaultRelate;
import com.wh.exception.ServiceException;
import com.wh.service.base.impl.BaseServiceImpl;
import com.wh.service.wmh.WmhFaultRelateService;



@Service
public class WmhFaultRelateServiceImpl extends BaseServiceImpl<WmhFaultRelate, Integer> implements WmhFaultRelateService{
	

	
	@Resource
	WmhFaultRelateMapper dao;

	@Resource
	public void setBaseDAO(WmhFaultRelateMapper wmhFaultRelateMapper) {
		this.dao = wmhFaultRelateMapper;
		this.baseDAO = wmhFaultRelateMapper;
	}

	@Override
	public List<Map<String, Object>> findfaultUserList(Map<String, Object> map, PageBounds pageBounds)
			throws ServiceException {
		
		return dao.findfaultUserList(map,pageBounds);
	}
	
	

}
