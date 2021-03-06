package com.wh.controller.msp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wh.base.AjaxJson;
import com.wh.controller.common.BaseController;

import com.wh.framework.MethodLog;
import com.wh.mspentity.User;
import com.wh.service.msp.MspPlatformService;
import com.wh.service.msp.MspUserService;
import com.wh.service.wmh.WmhNewsService;
import com.wh.util.WebUtil;
import com.wh.util.msp.JdbcTest;
import com.wh.util.msp.JdbcUtils;

import net.sf.json.JSONObject;

/**
 * 企业号新闻展示
 * 
 * @author Administrator Leo
 *
 */
@Controller
@RequestMapping("/msp/mspNews")
public class MspNewsController extends BaseController {

	@Resource
	private MspPlatformService mspPlatformService;
	@Resource
	private MspUserService mspUserService;

	@Autowired
	private WmhNewsService wmhNewsService;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 新闻同步
	 * 
	 * @param req
	 * @param resp
	 * @param news
	 * @return
	 */
	@RequestMapping(value = "/web/syncNews.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@MethodLog(logKey = "新闻同步", logTag = "新闻同步", logRemark = "新闻同步")
	public void syncNews(HttpServletRequest req, HttpServletResponse resp) {

		JSONObject jso = wmhNewsService.syncNews3(req);
		WebUtil.write(resp, jso.toString());

	}

	@RequestMapping("/wx/goXiaoli.htm")
	public String goSendPhoto() {
		return "/wmh/web/xiaoli/xiaoli";
	}

	/**
	 * 测试JDBC连接不同数据库 返回user对象
	 * 
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value = "/web/userList.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson userList(HttpServletRequest req, HttpServletResponse resp) {
		AjaxJson json = new AjaxJson();
		JdbcTest jt = new JdbcTest();
		List<User> userList = jt.getUserList();
		if (userList != null && userList.size() > 0) {
			json.setObj(userList);
		}
		return json;
	}

	/**
	 * 测试JDBC连接不同数据库 返回map<String,Object>
	 * 
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value = "/web/userList1.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson userList1(HttpServletRequest req, HttpServletResponse resp) {
		AjaxJson json = new AjaxJson();
		JdbcTest jt = new JdbcTest();

		List<Map<String, Object>> userList = jt.getUserList1();
		if (userList != null && userList.size() > 0) {
			json.setObj(userList);
		}
		return json;
	}

}
