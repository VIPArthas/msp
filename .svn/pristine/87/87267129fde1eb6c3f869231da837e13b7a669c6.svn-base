package com.wh.service.wmh;

import java.util.List;
import java.util.Map;

import com.wh.base.PageBounds;
import com.wh.entity.WmhFaultRepair;
import com.wh.entity.WmhUser;

/**
 * 
 *  故障报修
 *
 * <p>
 * <a href="WmhFaultRepairService.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:">wd</a>
 *
 * @version Revision: 1.0  Date: 2017年5月5日 上午10:53:06 
 *
 */
public interface WmhFaultRepairService {
	
	/**
	 * 
	 * @Title: faultRepair 
	 * @Description:故障报修
	 * @author wd
	 * @Date 2017年5月5日  上午11:41:15 
	 * @param wmhFaultRepair
	 * @param user
	 * @return
	 * @return String    返回类型
	 */
	String faultRepair(WmhFaultRepair wmhFaultRepair,WmhUser user);
	/**
	 * 后台故障报修list
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	List<Map<String, Object>> findList(Map<String, Object> map, PageBounds pageBounds);
	
	int countList();
	/**
	 * 获取图片路径
	 * @param malId   根据LinkId
	 * @return
	 */
	List<String> getPicPathByLinkId(String malId);


}
