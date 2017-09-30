package com.wh.service.msp;


import java.util.List;
import java.util.Map;

import com.wh.base.PageBounds;
import com.wh.exception.ServiceException;
import com.wh.mspentity.MspPhoto;

import com.wh.service.base.BaseService;


public interface MspPhotoService extends BaseService<MspPhoto, String> {

	List<Map<String, Object>> showList(Integer type, PageBounds pages)throws ServiceException;

	Map<String, Object> getShowInfoByPhotoId(String photoId)throws ServiceException;

	boolean updatePageviewNum(String photoId);



}
