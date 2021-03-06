/*
 * 功  能：简单说明该类的功能
 * 
 * 文件名：WmhMessageServiceImpl.java
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

package com.wh.service.wmh.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.RowBounds;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.wh.constants.Constants;
import com.wh.dao.wmh.WmhNewsMapper;
import com.wh.entity.WmhNews;
import com.wh.service.wmh.WmhNewsService;
import com.wh.util.BaseModel;
import com.wh.util.HttpClient;
import com.wh.util.PaginationInterceptor;
import com.wh.util.StringUtil;
import com.wh.util.UUIDUtil;
import com.wh.util.base.HttpClientUtils;

import net.sf.json.JSONObject;

/**
 * 新闻管理
 *
 * <p>
 * <a href="WmhMessageServiceImpl.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:">wd</a>
 *
 * @version Revision: 1.0 Date: 2017年3月7日 下午2:00:13
 *
 */
@Service
public class WmhNewsServiceImpl implements WmhNewsService {
	@Autowired
	WmhNewsMapper wmhNewsMapper;

	/**
	 * 新闻列表分页
	 */
	@Override
	public List<Object> selectWmhNewsListPage(WmhNews news) {
		int pageSize = Constants.wmhPageSize;
		int startNum = pageSize * (news.getCurrentpage() - 1);
		RowBounds rowBounds = new RowBounds(startNum, pageSize);
		PaginationInterceptor.startPage(news.getCurrentpage(), news.getRscount());
		List<WmhNews> newList = wmhNewsMapper.selectWmhNewsListPage(news, rowBounds);
		BaseModel baseModel = PaginationInterceptor.endPage();
		List<Object> list = new ArrayList<Object>();
		list.add(newList);
		list.add(baseModel.getCurrentpage());
		list.add(baseModel.getRscount());
		return list;
	}

	@Override
	public WmhNews getNewsInfo(String id) {
		return wmhNewsMapper.selectByPrimaryKey(id);
	}

	@Override
	public JSONObject syncNews(HttpServletRequest request) {
		JSONObject jso = new JSONObject();

		// 判断登陆的是哪个学校 如 sign=1河南师范大学新联学院 sign=2代表河南职业技术学院
		Integer sign = Integer.valueOf(request.getSession(false).getAttribute("sign").toString());

		// sign=2;
		// 获取某学校新闻URL
		String schoolURL = "";
		if (sign == null || sign == 1) {
			schoolURL = WmhNewsService.XLXY_NEWS_URL;
		} else if (sign == 2) {
			schoolURL = WmhNewsService.ZYJS_NEWS_URL;
		} else {
			schoolURL = WmhNewsService.XLXY_NEWS_URL;
		}
		// 模拟请求
		String htmlData = doGet(schoolURL, "utf-8");

		if (StringUtils.isEmpty(htmlData)) {
			jso.put("code", 0);
			jso.put("msg", "请求数据失败！");
			return jso;
		}

		// 2.查询新闻表数据
		List<WmhNews> allNewsOfDB = wmhNewsMapper.selectAllNews();

		// 3.解析新闻列表数据
		List<String> newsHref = new ArrayList<String>();
		if (sign == null || sign == 1) {
			newsHref = doHtmlNewsData(allNewsOfDB, htmlData);
		} else if (sign == 2) {
			newsHref = doHtmlNewsData1(allNewsOfDB, htmlData);
		} else {
			newsHref = doHtmlNewsData(allNewsOfDB, htmlData);
		}

		if (CollectionUtils.isEmpty(newsHref)) {
			jso.put("code", 0);
			jso.put("msg", "没有新的数据需要同步！");
			return jso;
		}

		// 4.根据url，处理新闻的标题、来源单位、新闻发布时间、新闻点击次数、底部文字、同步时间
		List<WmhNews> news = new ArrayList<WmhNews>();
		if (sign == null || sign == 1) {
			news = doHtmlNewsInfoData(newsHref);
		} else if (sign == 2) {
			news = doHtmlNewsInfoData1(newsHref);
		} else {
			news = doHtmlNewsInfoData(newsHref);
		}
		// 5.保存数据到数据库
		saveNewsData(news);
		jso.put("code", 1);
		jso.put("msg", "本次成功同步了" + newsHref.size() + "条新闻！");
		return jso;
	}

	/**
	 * 
	 * @Title: doGet
	 * @Description: 模拟请求该学院的新闻列表网页数据
	 * @author wd
	 * @Date 2017年3月9日 上午11:40:45
	 * @param url
	 * @return
	 * @return String 返回类型
	 */
	private String doGet(String url, String charSet) {
		String result = "";
		BufferedReader in = null;
		StringBuffer buffer = new StringBuffer();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();

			// 定义BufferedReader输入流来读取URL的响应
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charSet);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;

			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			result = buffer.toString();
		} catch (Exception e) {
			System.out.println("发送 GET 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: doHtmlNewsData
	 * @Description: 解析新闻列表数据
	 * @author wd
	 * @Date 2017年3月9日 下午1:15:56
	 * @param allNewsOfDB
	 * @param htmlData
	 * @return
	 * @return List<String> 返回类型
	 */
	private List<String> doHtmlNewsData(List<WmhNews> allNewsOfDB, String htmlData) {
		Document doc = Jsoup.parse(htmlData);

		// 获取新闻列表ul标签
		Element ul_html = doc.getElementsByClass("label_ul_b").get(0);

		// 获取单条新闻的a标签
		Elements as_html = ul_html.getElementsByTag("a");

		// 组装数据库里的url
		List<String> newsInfosOfDB = new ArrayList<String>();
		for (int i = 0; i < allNewsOfDB.size(); i++) {
			WmhNews news = allNewsOfDB.get(i);
			newsInfosOfDB.add(news.getUrl());
		}

		List<String> newsHref = new ArrayList<String>();
		for (int i = 0; i < as_html.size(); i++) {
			Element a_html = as_html.get(i);
			String a_href = a_html.attr("href");

			if (StringUtils.isEmpty(a_href)) {
				continue;
			}

			// a_href = WmhNewsService.XLXY_HOME_URL + "/" + a_href;
			// 如果数据库里已存在该url，则继续匹配下一个url
			if (newsInfosOfDB.contains(a_href)) {
				continue;
			}

			// 如果数据库里不存在：则需要请求该url，暂时将该url存起来，后续处理
			newsHref.add(a_href);
		}
		return newsHref;
	}

	/**
	 * 
	 * @Title: doHtmlNewsData1
	 * @Description: 解析河南职业技术学院新闻列表数据
	 * @author Leo
	 * @Date 2017年3月31日
	 * @param allNewsOfDB
	 * @param htmlData
	 * @return
	 * @return List<String> 返回类型
	 */
	private List<String> doHtmlNewsData1(List<WmhNews> allNewsOfDB, String htmlData) {

		Document doc = Jsoup.parse(htmlData);
		// 获取新闻列表a标签
		Elements as_html = doc.getElementsByClass("c67180");
		// 组装数据库里的url
		List<String> newsInfosOfDB = new ArrayList<String>();
		for (int i = 0; i < allNewsOfDB.size(); i++) {
			WmhNews news = allNewsOfDB.get(i);
			newsInfosOfDB.add(news.getUrl());
		}

		List<String> newsHref = new ArrayList<String>();
		for (int i = 0; i < as_html.size(); i++) {
			Element a_html = as_html.get(i);
			String a_href = a_html.attr("href");

			if (StringUtils.isEmpty(a_href)) {
				continue;
			}

			a_href = WmhNewsService.ZYJS_HOME_URL + "/" + a_href;
			// 如果数据库里已存在该url，则继续匹配下一个url
			if (newsInfosOfDB.contains(a_href)) {
				continue;
			}

			// 如果数据库里不存在：则需要请求该url，暂时将该url存起来，后续处理
			newsHref.add(a_href);
		}
		return newsHref;
	}

	/**
	 * 
	 * @Title: doHtmlNewsInfoData
	 * @Description: 根据url，处理新闻的标题、来源单位、新闻发布时间、新闻点击次数、底部文字、同步时间
	 * @author wd
	 * @Date 2017年3月9日 下午1:47:00
	 * @param newsHref
	 * @return List<WmhNews> 返回类型
	 */
	private List<WmhNews> doHtmlNewsInfoData(List<String> newsHref) {
		List<WmhNews> news = new ArrayList<WmhNews>();
		for (int i = 0; i < newsHref.size(); i++) {
			String href = newsHref.get(i);

			// 获取新闻详情页html
			String newsInfoHtml = doGet(href, "gbk");
			Document doc = Jsoup.parse(newsInfoHtml);

			WmhNews newsInfoData = new WmhNews();

			// 设置唯一标识
			newsInfoData.setId(UUIDUtil.getUUID());

			// 设置新闻链接
			newsInfoData.setUrl(href);

			// 获取新闻标题
			Element news_title_html = doc.getElementsByClass("layout_txtcontent_title").get(0);
			String news_title = news_title_html.text();
			newsInfoData.setNewsTitle(news_title);

			// 获取新闻来源单位
			// Element news_source_div_html = doc.getElementById("Lab_vFrom");
			// String source_div = news_source_div_html.text();
			newsInfoData.setSourceDiv("admin");

			// 获取新闻发稿时间
			Element news_create_time_html = doc.getElementsByClass("layout_txtcontent_info").get(0);
			String create_time = news_create_time_html.text();
			create_time = create_time.substring(3, 22);
			newsInfoData.setCreateTime(create_time);

			// 获取新闻点击次数
			Element news_click_num_html = doc.getElementsByClass("item_views").get(0);
			String click_num = news_click_num_html.text();
			newsInfoData.setClickNum(Integer.parseInt(click_num));

			Element newsContent = doc.getElementsByClass("layout_txtcontent_content").get(0);

			// //移除第一个元素：<div class="pic_content"> <span
			// id="Lab_pic"></span></div>
			// newsContent.child(0).remove();
			//
			// //移除第二个元素：<p></p>
			// newsContent.child(0).remove();

			// 获取所有的P标签
			Elements ps_html = newsContent.getElementsByTag("p");

			// 新闻正文
			String newsContentOfHtml = "";
			for (int j = 0; j < ps_html.size(); j++) {
				// 每一个段落的内容
				Element p_html = ps_html.get(j);

				String content_of_p = "";

				String text = p_html.text();
				if (!StringUtils.isEmpty(text)) {
					content_of_p = text;
				} else {
					Elements childrenOfP = p_html.children();

					if (childrenOfP.size() > 0) {
						content_of_p = doHtml(childrenOfP, content_of_p);
					}
				}

				if (!StringUtils.isEmpty(content_of_p)) {
					String style = p_html.attr("style");
					if (style.contains("text-align: center") || style.contains("text-align:center")) {
						content_of_p = "<p style='text-align: center;'>" + content_of_p + "</p>";
					} else {
						content_of_p = "<p>" + content_of_p + "</p>";
					}

					newsContentOfHtml = newsContentOfHtml + content_of_p;
				}
			}
			newsInfoData.setNewsContent(newsContentOfHtml);

			// 处理新闻底部文字
			String[] pHtmls = newsContentOfHtml.split("</p>");
			String bottom_text = pHtmls[pHtmls.length - 1].trim().replace("&nbsp;", "");
			bottom_text = bottom_text.replace("<p>", "");
			if (bottom_text.startsWith("（") && bottom_text.endsWith("）")) {
				newsInfoData.setBottomText(bottom_text);
				newsContentOfHtml = newsContentOfHtml.replace(bottom_text, "");
				newsInfoData.setNewsContent(newsContentOfHtml);
			}

			// 设置同步时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sync_date = sdf.format(new Date());
			newsInfoData.setSyncTime(sync_date);

			news.add(newsInfoData);
		}
		return news;
	}

	/**
	 * 
	 * @Title: doHtmlNewsInfoData
	 * @Description: 根据url，处理新闻的标题、来源单位、新闻发布时间、新闻点击次数、底部文字、同步时间
	 * @author wd
	 * @Date 2017年3月9日 下午1:47:00
	 * @param newsHref
	 * @return List<WmhNews> 返回类型
	 */
	private List<WmhNews> doHtmlNewsInfoData1(List<String> newsHref) {

		List<WmhNews> news = new ArrayList<WmhNews>();
		for (int i = 0; i < newsHref.size(); i++) {
			String href = newsHref.get(i);

			// 获取新闻详情页html
			String newsInfoHtml = doGet(href, "utf-8");
			Document doc = Jsoup.parse(newsInfoHtml);

			WmhNews newsInfoData = new WmhNews();

			// 设置唯一标识
			newsInfoData.setId(UUIDUtil.getUUID());

			// 设置新闻链接
			newsInfoData.setUrl(href);

			// 获取新闻标题
			Element news_title_html = doc.getElementsByClass("titlestyle68794").get(0);

			String news_title = news_title_html.text();
			newsInfoData.setNewsTitle(news_title);

			// 获取新闻来源单位

			Element news_source_div_html = doc.getElementsByClass("authorstyle68794").get(0);
			String a = news_source_div_html.html(); // <p> &nbsp;</p> 去除空格转义成?
													// 的问题
			String source_div = news_source_div_html.text().substring(1);
			newsInfoData.setSourceDiv(source_div);

			// 获取新闻发稿时间
			Element news_create_time_html = doc.getElementsByClass("timestyle68794").get(0);
			String create_time = news_create_time_html.text();
			newsInfoData.setCreateTime(create_time);

			// 获取新闻点击次数 因点击次数在js中,jsoup无法抓取

			/** HtmlUnit请求web页面 */
			/*
			 * WebClient wc = new WebClient(BrowserVersion.CHROME);
			 * wc.getOptions().setUseInsecureSSL(true);
			 * wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
			 * wc.getOptions().setCssEnabled(false); // 禁用css支持
			 * wc.getOptions().setThrowExceptionOnScriptError(false); //
			 * js运行错误时，是否抛出异常 wc.getOptions().setTimeout(100000); // 设置连接超时时间
			 * ，这里是10S。如果为0，则无限期等待 wc.getOptions().setDoNotTrackEnabled(false);
			 * HtmlPage page=null; try { page = wc.getPage(href); } catch
			 * (FailingHttpStatusCodeException | IOException e) {
			 * e.printStackTrace(); }
			 * 
			 * 
			 * DomElement links = page.getElementById("n11127"); String
			 * num=links.asText();
			 */
			/*
			 * Element
			 * news_click_num_html=news_source_div_html.nextElementSibling().
			 * nextElementSibling().child(0);
			 * 
			 * 
			 * String click_num =news_click_num_html.text();
			 * 
			 * if (StringUtil.isNotEmpty(click_num)) {
			 * newsInfoData.setClickNum(Integer.parseInt(click_num)); }
			 */
			Integer click_num = 263 + i * 2;
			newsInfoData.setClickNum(click_num);

			Element newsContent = doc.getElementsByClass("contentstyle68794").get(0);

			// 获取所有的P标签
			Elements ps_html = newsContent.getElementsByTag("p");

			// 新闻正文
			String newsContentOfHtml = "";
			for (int j = 0; j < ps_html.size(); j++) {
				// 每一个段落的内容
				Element p_html = ps_html.get(j);

				String content_of_p = "";
				String text = p_html.text();
				String htmlcontent = p_html.html();
				// <p> &nbsp;</p> 去除空格转义成? 的问题
				if ("&nbsp;".equals(htmlcontent)) {
					text = "";
				}
				if (!StringUtils.isEmpty(text)) {
					content_of_p = text;
				}

				Elements childrenOfP = p_html.children();

				if (childrenOfP.size() > 0) {
					content_of_p = doHtml1(childrenOfP, content_of_p);
				}

				if (!StringUtils.isEmpty(content_of_p)) {
					String style = p_html.attr("style");
					if (style.contains("text-align: center") || style.contains("text-align:center")) {
						content_of_p = "<p style='text-align: center;'>" + content_of_p + "</p>";
					} else {
						content_of_p = "<p>" + content_of_p + "</p>";
					}

					newsContentOfHtml = newsContentOfHtml + content_of_p;
				}
			}
			newsInfoData.setNewsContent(newsContentOfHtml);

			// 设置同步时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sync_date = sdf.format(new Date());
			newsInfoData.setSyncTime(sync_date);
			newsInfoData.setSign(2);
			news.add(newsInfoData);
		}
		return news;
	}

	private String doHtml(Elements children, String content_of_p) {
		for (int j = 0; j < children.size(); j++) {
			Element child_of_p_html = children.get(j);

			// 替换图片路径为绝对路径
			if (child_of_p_html.hasAttr("src")) {
				String img_src = child_of_p_html.attr("src");
				// img_src = WmhNewsService.XLXY_HOME_URL + img_src;

				// String img_title = child_of_p_html.attr("title");
				// String img_alt = child_of_p_html.attr("alt");

				content_of_p = content_of_p + "<div class='img'><img src='" + img_src + "' ></div>";
			}

			Elements childrenOfP = child_of_p_html.children();
			if (childrenOfP.size() > 0) {
				content_of_p = content_of_p + doHtml(childrenOfP, content_of_p);
			}
		}
		return content_of_p;
	}

	private String doHtml1(Elements children, String content_of_p) {
		for (int j = 0; j < children.size(); j++) {
			Element child_of_p_html = children.get(j);

			// 替换图片路径为绝对路径
			if (child_of_p_html.hasAttr("src")) {
				String img_src = child_of_p_html.attr("src");
				if (img_src.contains("../../")) {
					img_src = WmhNewsService.ZYJS_HOME_URL + img_src.substring(5);
				}

				String img_title = child_of_p_html.attr("title");
				String img_alt = child_of_p_html.attr("alt");

				content_of_p = content_of_p + "<div class='img'><img src='" + img_src + "' title='" + img_title
						+ "' alt='" + img_alt + "'></div>";
			}

			Elements childrenOfP = child_of_p_html.children();
			if (childrenOfP.size() > 0) {
				content_of_p = content_of_p + doHtml1(childrenOfP, content_of_p);
			}
		}
		return content_of_p;
	}
	
	
	

	
	
	

	/**
	 * 
	 * @Title: saveNewsData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author wd
	 * @Date 2017年3月9日 下午1:49:19
	 * @param news
	 * @return void 返回类型
	 */
	private void saveNewsData(List<WmhNews> news) {
		for (int i = 0; i < news.size(); i++) {
			WmhNews newData = news.get(i);
			wmhNewsMapper.insertSelective(newData);
		}
	}

	@Override
	public List<Object> selectNewsByCrateTimeMobileListPage(WmhNews news) {
		int pageSize = Constants.wmhPageSize;
		int startNum = pageSize * (news.getCurrentpage() - 1);
		RowBounds rowBounds = new RowBounds(startNum, pageSize);
		PaginationInterceptor.startPage(news.getCurrentpage(), news.getRscount());
		List<Map<String, Object>> newList = this.wmhNewsMapper.selectNewsByCrateTimeMobileListPage(news, rowBounds);
		BaseModel baseModel = PaginationInterceptor.endPage();
		List<Object> list = new ArrayList<Object>();
		list.add(newList);
		list.add(baseModel.getCurrentpage());
		list.add(baseModel.getRscount());
		return list;
	}

	@Override
	public void selectSyncTimeAndTotalPages(Map model, WmhNews news) {
		Map<String, Object> data = wmhNewsMapper.selectSyncTimeAndNewsCount(news);
		if (CollectionUtils.isEmpty(data)) {
			model.put("syncTime", "");
			model.put("totalPage", "0");
		} else {
			model.put("syncTime", data.get("sync_time"));
			long news_count = (long) data.get("news_count");
			long totalPage = 0;
			if (news_count % Constants.wmhPageSize == 0) {
				totalPage = news_count / Constants.wmhPageSize;
			} else {
				totalPage = news_count / Constants.wmhPageSize + 1;
			}
			model.put("count", news_count);
			model.put("totalPage", totalPage);
		}
	}

	@Override
	public Long selectNewsCount(WmhNews news) {
		return this.wmhNewsMapper.selectNewsCount(news);
	}

	/**
	 * 旅职专用
	 */
	@Override
	public JSONObject syncNews3(HttpServletRequest req) {
		JSONObject jso = new JSONObject();
		Integer sign = 3;
		// 获取某学校新闻URL
		String schoolURL = com.wh.util.msp.Constants.LZNEWS_URL;
		// 模拟请求
		String htmlData = doGet(schoolURL, "utf-8");

		if (StringUtils.isEmpty(htmlData)) {
			jso.put("code", 0);
			jso.put("msg", "请求数据失败！");
			return jso;
		}

		// 2.查询新闻表数据
		List<WmhNews> allNewsOfDB = wmhNewsMapper.selectAllNews();

		// 3.解析新闻列表数据
		List<String> newsHref = new ArrayList<String>();
		newsHref = doHtmlNewsData3(allNewsOfDB, htmlData);

		if (CollectionUtils.isEmpty(newsHref)) {
			jso.put("code", 0);
			jso.put("msg", "没有新的数据需要同步！");
			return jso;
		}

		// 4.根据url，处理新闻的标题、来源单位、新闻发布时间、新闻点击次数、底部文字、同步时间
		List<WmhNews> news = new ArrayList<WmhNews>();
		news = doHtmlNewsInfoData3(newsHref);

		// 5.保存数据到数据库
		saveNewsData(news);
		jso.put("code", 1);
		jso.put("msg", "本次成功同步了" + newsHref.size() + "条新闻！");
		return jso;
	}

	/**
	 * 旅职使用
	 * 
	 * @param allNewsOfDB
	 * @param htmlData
	 * @return
	 */
	private List<String> doHtmlNewsData3(List<WmhNews> allNewsOfDB, String htmlData) {

		Document doc = Jsoup.parse(htmlData);
		// 获取新闻列表a标签
		// Elements as_html = doc.getElementsByClass("c67180");
		// 获取所有tr标签
		Elements tbodys = doc.getElementsByTag("tbody");

		Element tbody = tbodys.get(0);
		Element tr = tbody.child(1);
		Elements as_html = tr.getElementsByTag("a");

		// 组装数据库里的url
		List<String> newsInfosOfDB = new ArrayList<String>();
		for (int i = 0; i < allNewsOfDB.size(); i++) {
			WmhNews news = allNewsOfDB.get(i);
			newsInfosOfDB.add(news.getUrl());
		}

		List<String> newsHref = new ArrayList<String>();
		for (int i = 0; i < as_html.size(); i++) {
			Element a_html = as_html.get(i);
			String a_href = a_html.attr("href");

			if (StringUtils.isEmpty(a_href)) {
				continue;
			}

			a_href = com.wh.util.msp.Constants.LZ_URL + a_href;
			// 如果数据库里已存在该url，则继续匹配下一个url
			if (newsInfosOfDB.contains(a_href)) {
				continue;
			}

			// 如果数据库里不存在：则需要请求该url，暂时将该url存起来，后续处理
			newsHref.add(a_href);
		}
		return newsHref;
	}

	/**
	 * 旅职新闻处理
	 * 
	 * @param newsHref
	 * @return
	 */
	private List<WmhNews> doHtmlNewsInfoData3(List<String> newsHref) {

		List<WmhNews> news = new ArrayList<WmhNews>();
		for (int i = 0; i < newsHref.size(); i++) {
			String href = newsHref.get(i);
			// 获取新闻详情页html
			String newsInfoHtml = doGet(href, "utf-8");
			Document doc = Jsoup.parse(newsInfoHtml);
			WmhNews newsInfoData = new WmhNews();
			// 设置唯一标识
			newsInfoData.setId(UUIDUtil.getUUID());
			// 设置新闻链接
			newsInfoData.setUrl(href);
			Elements metas = doc.getElementsByTag("meta");
			for (Element meta : metas) {
				String name = meta.attr("name");
				if ("title".equals(name)) {
					// 获取新闻标题
					String news_title = meta.attr("content");
					newsInfoData.setNewsTitle(news_title);
					continue;
				}
				if ("source".equals(name)) {
					// 获取新闻来源单位
					String source_div = meta.attr("content");
					newsInfoData.setSourceDiv(source_div);
					continue;
				}
				if ("pubDate".equals(name)) {
					// 获取新闻来源单位
					String create_time = meta.attr("content");
					newsInfoData.setCreateTime(create_time);
					continue;
				}
			}

			//
			Elements tbodys = doc.getElementsByTag("tbody");
			Element tbody = tbodys.get(9);
			Element td = tbody.getElementsByTag("td").get(2);

			String html = td.html();
			System.out.println("html---------------->" + html);

			// 设置责任编辑
			String author = html.substring(html.indexOf("begin-->") + 8, html.indexOf("<!--<$[信息录入人]>end-->"));
			if (StringUtil.isEmpty(author)) {
				author = "郭防";
			}
			newsInfoData.setAuthor(author);

			// 设置点击量

			String clickstr = html.substring(html.indexOf("src=") + 4, html.indexOf("> </script>"));
			String click1 = clickstr.substring(clickstr.indexOf("/") + 1, clickstr.indexOf("&"));
			String click2 = clickstr.substring(clickstr.indexOf(";") + 1, clickstr.length() - 1);

			String clickUrl = com.wh.util.msp.Constants.LZ_URL + "/" + click1 + "&" + click2;
			System.out.println("点击量地址---------------->"+clickUrl);

			String datastr = HttpClient.sendGet(clickUrl, null);
			//document.write("1");
			if (StringUtil.isNotEmpty(datastr)) {				
				String[] tempArr = datastr.split("\"");			
				Integer click_num = Integer.valueOf(tempArr[1].trim());
				newsInfoData.setClickNum(click_num);
			}
			

			Element zoom=doc.getElementById("zoom");
			
			// 获取所有的P标签
			Elements ps_html = zoom.getElementsByTag("p");

			// 新闻正文
			String newsContentOfHtml = "";
			for (int j = 0; j < ps_html.size(); j++) {
				// 每一个段落的内容
				Element p_html = ps_html.get(j);

				String content_of_p = "";
				String text = p_html.text();
				String htmlcontent = p_html.html();
				// <p> &nbsp;</p> 去除空格转义成? 的问题
				if ("&nbsp;".equals(htmlcontent)) {
					text = "";
				}
				if (!StringUtils.isEmpty(text)) {
					content_of_p = text;
				}

				Elements childrenOfP = p_html.children();

				if (childrenOfP.size() > 0) {
					content_of_p = doHtml3(childrenOfP, content_of_p);
				}

				if (!StringUtils.isEmpty(content_of_p)) {
					String style = p_html.attr("style");
					if (style.contains("text-align: center") || style.contains("text-align:center")) {
						content_of_p = "<p style='text-align: center;'>" + content_of_p + "</p>";
					} else {
						content_of_p = "<p>" + content_of_p + "</p>";
					}

					newsContentOfHtml = newsContentOfHtml + content_of_p;
				}
			}
			newsInfoData.setNewsContent(newsContentOfHtml);

			// 设置同步时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sync_date = sdf.format(new Date());
			newsInfoData.setSyncTime(sync_date);
			newsInfoData.setSign(3);
			news.add(newsInfoData);
		}
		return news;
	}
	
	
	private String doHtml3(Elements children, String content_of_p) {
		for (int j = 0; j < children.size(); j++) {
			Element child_of_p_html = children.get(j);

			// 替换图片路径为绝对路径
			if (child_of_p_html.hasAttr("src")) {
				String img_src = child_of_p_html.attr("src");
				img_src=com.wh.util.msp.Constants.LZ_URL + img_src;
				System.out.println("img_src------------->:"+img_src);
				String img_title = child_of_p_html.attr("title");
				String img_alt = child_of_p_html.attr("alt");

				content_of_p = content_of_p + "<div class='img'><img src='" + img_src + "' title='" + img_title
						+ "' alt='" + img_alt + "'></div>";
			}

			Elements childrenOfP = child_of_p_html.children();
		/*	if (childrenOfP.size() > 0) {
				content_of_p = content_of_p + doHtml3(childrenOfP, content_of_p);
			}*/
		}
		return content_of_p;
	}

}
