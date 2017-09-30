package com.wh.service.msp;



import java.util.Map;

import com.wh.exception.ServiceException;
import com.wh.mspentity.MspUser;
import com.wh.service.base.BaseService;


public interface MspUserService extends BaseService<MspUser, String> {

	MspUser selectByPrimaryKey(String id);

	boolean updateUserIdNull(String id)throws ServiceException;

	Map<String, Object> getMyInfo(String userid)throws ServiceException;

}
