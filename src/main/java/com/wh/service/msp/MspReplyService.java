package com.wh.service.msp;


import java.util.List;
import java.util.Map;

import com.wh.exception.ServiceException;
import com.wh.mspentity.MspReply;
import com.wh.service.base.BaseService;


public interface MspReplyService extends BaseService<MspReply, Integer> {

	List<Map<String, Object>> getReplyListByPhotoId(String photoId)throws ServiceException;

	boolean deleteByphotoId(String photoId)throws ServiceException;



}
