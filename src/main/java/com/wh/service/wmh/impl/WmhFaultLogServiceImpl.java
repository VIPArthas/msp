package com.wh.service.wmh.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wh.dao.wmh.WmhFaultLogMapper;

import com.wh.entity.WmhFaultLog;
import com.wh.service.base.impl.BaseServiceImpl;
import com.wh.service.wmh.WmhFaultLogService;



@Service
public class WmhFaultLogServiceImpl extends BaseServiceImpl<WmhFaultLog, Integer> implements WmhFaultLogService{
	

	
	@Resource
	WmhFaultLogMapper dao;

	@Resource
	public void setBaseDAO(WmhFaultLogMapper wmhFaultLogMapper) {
		this.dao = wmhFaultLogMapper;
		this.baseDAO = wmhFaultLogMapper;
	}
	
	

}
