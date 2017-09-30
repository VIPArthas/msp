package com.wh.service.msp;


import java.util.List;
import java.util.Map;

import com.wh.exception.ServiceException;
import com.wh.mspentity.MspTeacherClass;
import com.wh.service.base.BaseService;

public interface MspTeacherClassService extends BaseService<MspTeacherClass, Integer>{

	List<Map<String, Object>> tecClassListByMembId(String memberId)throws ServiceException;

}
