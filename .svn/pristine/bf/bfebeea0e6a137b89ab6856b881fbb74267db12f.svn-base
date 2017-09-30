package com.wh.service.msp.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wh.dao.msp.MspTeacherClassMapper;
import com.wh.exception.ServiceException;
import com.wh.mspentity.MspTeacherClass;
import com.wh.service.base.impl.BaseServiceImpl;

import com.wh.service.msp.MspTeacherClassService;
@Service
public class MspTeacherClassServiceImpl extends BaseServiceImpl<MspTeacherClass, Integer> implements MspTeacherClassService{

	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	MspTeacherClassMapper dao;

	@Resource
	public void setBaseDAO(MspTeacherClassMapper mspTeacherClassMapper) {
		this.dao = mspTeacherClassMapper;
		this.baseDAO = mspTeacherClassMapper;
	}
	/**
	 * 根据教师工号获取代班信息
	 */
	@Override
	public List<Map<String, Object>> tecClassListByMembId(String memberId) throws ServiceException {
		
		return dao.tecClassListByMembId(memberId);
	}
}
