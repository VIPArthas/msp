package com.wh.service.msp;


import java.util.List;

import com.wh.exception.ServiceException;
import com.wh.mspentity.MspAttachements;


import com.wh.service.base.BaseService;


public interface MspAttachementsService extends BaseService<MspAttachements, Integer> {
	
	/**
	 * 根据linkId获取 photoUrl
	 * @param photoId
	 * @return
	 */
	List<String> getPhotoUrlListByLinkId(String linkId)throws ServiceException;

	boolean  deleteByLinkId(String linkId)throws ServiceException;



}
