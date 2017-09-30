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

import javax.servlet.http.HttpServletRequest;

import com.wh.entity.WmhNews;

import net.sf.json.JSONObject;

/**
 *  新闻管理
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

public interface WmhNewsService {
	
	/**
	 * 河南师范大学新联学院：官网url
	 */
	static final String XLXY_HOME_URL = "http://www.xlxy.edu.cn";
	
	/**
	 * 河南师范大学新联学院_资讯列表：url[&cpage=6]
	 */
	static final String XLXY_NEWS_URL = "http://www.xlxy.edu.cn/html/816/";
	
	
	/**
	 * 河南职业技术学院：官网url
	 */
	static final String ZYJS_HOME_URL = "http://www.hnzj.edu.cn";
	
	/**
	 * 河南职业技术学院_资讯列表：url[&cpage=6]
	 */
	static final String ZYJS_NEWS_URL = "http://www.hnzj.edu.cn/xyxw.htm";
	
	/**
	 * 
	 * @Title: selectWmhNewsListPage 
	 * @Description: 新闻列表分页
	 * @author wd
	 * @Date 2017年3月9日  下午5:24:31 
	 * @param news
	 * @return
	 * @return List<Object>    返回类型
	 */
	List<Object> selectWmhNewsListPage(WmhNews news);
	
	/**
	 * 
	 * @Title: getNewsInfo 
	 * @Description: 查询新闻详情
	 * @author wd
	 * @Date 2017年3月10日  下午4:35:03 
	 * @param id
	 * @return
	 * @return WmhNews    返回类型
	 */
	WmhNews getNewsInfo(String id);
	
	/**
	 * 
	 * @Title: syncNews 
	 * @Description: 新闻同步
	 * @author wd
	 * @Date 2017年3月9日  上午10:14:16 
	 * @param request
	 * @return
	 * @return JSONObject    返回类型
	 */
	JSONObject syncNews(HttpServletRequest request);
	
	/**
     * 根据时间倒序查询数据
     * @author 王鹏翔
     * @Date 2017年3月15日  上午10:17:07
     * @return
     */
    List<Object> selectNewsByCrateTimeMobileListPage(WmhNews news) throws Exception;
    
    
    /**
     * 根据条件查询新闻条数
     * @author 王鹏翔
     * @Date 2017年4月6日  下午5:11:31
     * @param news
     * @return
     */
    Long selectNewsCount(WmhNews news);
    
    /**
     * 查询同步时间和
     * @param model
     * @param news
     */
    void selectSyncTimeAndTotalPages(Map model,WmhNews news);

	JSONObject syncNews3(HttpServletRequest req);

}
