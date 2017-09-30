/*
 * 功  能：简单说明该类的功能
 * 
 * 文件名：WmhMessageController.java
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

/*
 * 功  能：简单说明该类的功能
 * 
 * 文件名：WmhMessageController.java
 * 
 * 描  述：
 * 
 * [变更履历]
 * Version   变更日                   部署              作者           		变更内容
 * -----------------------------------------------------------------------------
 * V1.00     2017年3月7日       jh        wd    	create
 * -----------------------------------------------------------------------------
 *
 * Copyright (c) 2017   jh All Rights Reserved.
 *┌─────────────────────────────────────────────────—────┐
 *│ 版权声明                                  jh      │        
 *└──────────────────────────────—————————————————————───┘
 */
package com.wh.controller.wmh;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wh.controller.common.BaseController;
import com.wh.entity.WmhNews;
import com.wh.framework.MethodLog;
import com.wh.service.wmh.WmhNewsService;
import com.wh.util.WebUtil;

import net.sf.json.JSONObject;

/**
 *  新闻管理
 *
 * <p>
 * <a href="WmhMessageController.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:">wd</a>
 *
 * @version Revision: 1.0  Date: 2017年3月7日 下午1:56:18 
 *
 */
@Controller
@RequestMapping("/wmh/news")
public class WmhNewsController extends BaseController{

	@Autowired
    private WmhNewsService wmhNewsService;
	
	@RequestMapping("/web/goNewsList.htm")
	public String goNewsList(HttpServletRequest request,ModelMap map,WmhNews wmhNews){
		    Integer sign=3;
			wmhNews.setSign(sign);
		
		List<Object> wmhNewsList = wmhNewsService.selectWmhNewsListPage(wmhNews);
		map.put("newsList", wmhNewsList);
		if(!CollectionUtils.isEmpty(wmhNewsList)){
			Object list = wmhNewsList.get(0);
			List<WmhNews> news = (List<WmhNews>) list;
			if(news.size() > 0){
				map.put("syncTime", news.get(0).getSyncTime());
			}
		}
		
		return "/wmh/web/news/news";
	}
	
	@RequestMapping("/web/goNewsList1.htm")
	public String goNewsList1(HttpServletRequest request,ModelMap map,WmhNews wmhNews){
		 Integer sign=3;
		wmhNews.setSign(sign);

		//查询新闻列表
		try {
			wmhNewsService.selectSyncTimeAndTotalPages(map,wmhNews);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/wmh/wx/manage/news/news";
	}
	
	/**
	 * 微信端  前台查看所有新闻
	 * 根据学校的不同
	 * @param request
	 * @param map
	 * @param wmhNews
	 * @return
	 */
	@RequestMapping("/web/goNewsList2.htm")
	public String goNewsList2(HttpServletRequest request,ModelMap map,WmhNews news){
		Integer sign=3;
		news.setSign(sign);

	     try {
	            //查询新闻列表
	            List<Object> list = this.wmhNewsService.selectNewsByCrateTimeMobileListPage(news);
	            List<Map<String, Object>> newsList = (List<Map<String, Object>>) list.get(0);
	            for (Map<String, Object> map2 : newsList) {
	                String createTime = ((String) map2.get("create_time")).replaceAll("-", ".");
	                map2.put("yearMonth", createTime.substring(0, 7));
	                map2.put("day", createTime.substring(8, 10));
	            }
	            Long newsCount = this.wmhNewsService.selectNewsCount(news);
	            map.put("newsCount", newsCount);
	            map.put("list", list);
	            map.put("searchContent", news.getSearchContent());
	            map.put("sign", news.getSign());
	        } catch (Exception e) {
	            log.error("异常日志：", e);
	        }
		
		
		return "/wmh/wx/news/allnews";
	}
	
	
	
	
	
	@RequestMapping("/web/goNewsListData.htm")
	public void goNewsListData(HttpServletRequest request,WmhNews wmhNews,HttpServletResponse response){
		JSONObject jso = new  JSONObject();
		 Integer sign=3;
		wmhNews.setSign(sign);

		//查询新闻列表
		try {
			List<Object> list = this.wmhNewsService.selectNewsByCrateTimeMobileListPage(wmhNews);
			List<Map<String, Object>> newsList = (List<Map<String, Object>>) list.get(0);
	        
	        for (Map<String, Object> map2 : newsList) {
	            String createTime = ((String) map2.get("create_time")).replaceAll("-", ".");
	            map2.put("yearMonth", createTime.substring(0, 7));
	            map2.put("day", createTime.substring(8, 10));
	        }
	        jso.put("list", newsList);
	        WebUtil.write(response, jso.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/web/syncNews.htm")
	@MethodLog(logKey="新闻同步",logTag="新闻同步",logRemark="新闻同步")
	public void syncNews(HttpServletRequest request,HttpServletResponse response){
		JSONObject jso = wmhNewsService.syncNews(request);
		WebUtil.write(response, jso.toString());
	}
	
	@RequestMapping("/web/newsInfo.htm")
	public String newsInfo(HttpServletRequest request,ModelMap map,String id){
		map.put("news", wmhNewsService.getNewsInfo(id));
		return "/wmh/web/news/news_info";
	}
	
	@RequestMapping("/wx/newsInfo.htm")
	public String wxNewsInfo(HttpServletRequest request,ModelMap map,String id){
		map.put("news", wmhNewsService.getNewsInfo(id));
		return "/wmh/wx/news_info";
	}
	
	/**
	 * 微信端后台新闻详情
	 * @param request
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping("/wx/newsInfoWx.htm")
	public String wxNewsInfoWx(HttpServletRequest request,ModelMap map,String id){
		map.put("news", wmhNewsService.getNewsInfo(id));
		return "/wmh/wx/manage/news/newsDetail";
	}
}
