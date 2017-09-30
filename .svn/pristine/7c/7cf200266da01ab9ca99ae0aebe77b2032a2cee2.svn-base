package com.wh.service.msp.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.wh.dao.msp.MspDepartmentMapper;
import com.wh.mspentity.MspDepartment;
import com.wh.service.base.impl.BaseServiceImpl;
import com.wh.service.msp.MspDepartmentService;

@Service
public class MspDepartmentServiceImpl extends BaseServiceImpl<MspDepartment, String> implements MspDepartmentService {

	@Resource
	MspDepartmentMapper dao;

	@Resource
	public void setBaseDAO(MspDepartmentMapper mspDepartmentMapper) {
		this.dao = mspDepartmentMapper;
		this.baseDAO = mspDepartmentMapper;
	}
}
