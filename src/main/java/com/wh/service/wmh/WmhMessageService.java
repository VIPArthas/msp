/*
 * 功  能：简单说明该类的功能
 * 
 * 文件名：WmhMessageService.java
 * 
 * 描  述：
 * 
 * [变更履历]
 * Version   变更日         		部署              作者           变更内容
 * -----------------------------------------------------------------------------
 * V1.00     2017年3月7日   		jh   	 wd     create
 * -----------------------------------------------------------------------------
 *
 *
 * Copyright (c) 2017  	jh All Rights Reserved.
 *┌─────────────────────────────────────────────────—────┐
 *│ 版权声明                               	jh      	│
 *└──────────────────────────────—————————————————————───┘
 */

package com.wh.service.wmh;

import java.util.List;
import java.util.Map;

import com.wh.entity.WmhMessage;
import com.wh.entity.WmhTagUse;
import com.wh.service.base.BaseService;
import com.wh.util.BaseModel;

import javax.servlet.http.HttpServletRequest;

/**
 *  消息推送
 *
 * <p>
 * <a href="WmhMessageService.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:">wd</a>
 *
 * @version Revision: 1.0  Date: 2017年3月7日 下午1:59:04 
 *
 */

public interface WmhMessageService extends BaseService<WmhMessage,Integer>{
	/**
	 * 1.模板类型:会议通知；
	 */
	static final int TEMPLATE_TYPE_HYTZ = 1;
	
	/**
	 * 2.模板类型:薪资发放通知；
	 */
	static final int TEMPLATE_TYPE_XZFFTZ = 2;
	
	/**
	 * 3.模板类型:周师修改日志通知；
	 */
	static final int TEMPLATE_TYPE_ZSXGRZTZ = 3;
	
	/**
	 * 
	 * @Title: getTagIdAndUserCount 
	 * @Description: 根据标签名称查询标签ID和具有该标签的用户数量
	 * @author wd
	 * @Date 2017年3月8日  上午9:40:15 
	 * @param tagName
	 * @return
	 * @return Map<String,Object>    返回类型
	 */
	Map<String,Object> getTagIdAndUserCount(String tagName);
	
	/**
	 * 
	 * @Title: getUserInfoByPhone 
	 * @Description: 根据手机号查询用户ID
	 * @author wd
	 * @Date 2017年3月8日  上午11:54:51 
	 * @param phone
	 * @param realName
	 * @return
	 * @return String    返回类型
	 */
	Map<String,Object> getUserInfoByPhone(String phone,String realName);
	
	/**
	 * 
	 * @Title: getOftenAndLastUseTags 
	 * @Description: 获取常用标签和最新使用的标签
	 * @author wd
	 * @Date 2017年3月8日  下午1:53:31 
	 * @return
	 * @return Map<String,List<Map<String,Object>>>    返回类型
	 */
	Map<String,List<Map<String,Object>>> getOftenAndLastUseTags();
	Integer pushMessageMethod(HttpServletRequest httpServletRequest, WmhMessage wmhMessage) throws  Exception;
    List<Object> searchAllMsg(WmhMessage wmhMessage) throws Exception;
	/**
	 *@Author:liping
	 *@Description: 根据消息id查询
	 *@Date: 2017年3月15日09:54:31
	 */
	WmhMessage searchById(String id) throws Exception;
	
	void selectTotalPages(Map model);
}
