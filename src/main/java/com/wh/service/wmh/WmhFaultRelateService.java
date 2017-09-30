package com.wh.service.wmh;


import java.util.List;
import java.util.Map;

import com.wh.base.PageBounds;
import com.wh.entity.WmhFaultRelate;
import com.wh.exception.ServiceException;
import com.wh.service.base.BaseService;



/**
 * 故障报修关系表
 * 含故障报修区域,物品及负责人信息
 * @author Administrator
 *
 */
public interface WmhFaultRelateService extends BaseService<WmhFaultRelate, Integer>{
	
	/**
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	List<Map<String, Object>> findfaultUserList(Map<String, Object> map, PageBounds pageBounds)throws ServiceException ;
	


}
