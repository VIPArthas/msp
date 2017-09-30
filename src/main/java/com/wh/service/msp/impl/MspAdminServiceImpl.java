package com.wh.service.msp.impl;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.wh.dao.msp.MspAdminMapper;
import com.wh.mspentity.MspAdmin;
import com.wh.service.base.impl.BaseServiceImpl;
import com.wh.service.msp.MspAdminService;


/**
 * 班级表
 * @author Administrator
 *
 */
@Service
public class MspAdminServiceImpl extends BaseServiceImpl<MspAdmin, Integer> implements MspAdminService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	MspAdminMapper dao;

	@Resource
	public void setBaseDAO(MspAdminMapper mspAdminMapper) {
		this.dao = mspAdminMapper;
		this.baseDAO = mspAdminMapper;
	}
}
