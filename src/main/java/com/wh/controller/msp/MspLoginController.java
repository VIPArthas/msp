package com.wh.controller.msp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.base.AjaxJson;
import com.wh.base.PageBounds;
import com.wh.controller.common.BaseController;
import com.wh.mspentity.MspPlatform;
import com.wh.mspentity.MspUser;
import com.wh.service.msp.MspPlatformService;
import com.wh.service.msp.MspUserService;
import com.wh.util.StringUtil;
import com.wh.util.msp.Constants;
import com.wh.util.msp.EnumMethod;
import com.wh.util.msp.HttpRequestUtil;
import com.wiscom.ldapvalidate.ldapCheck;

import net.sf.json.JSONObject;

/**
 * 仅内网可用
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/msp/mspLogin")
public class MspLoginController extends BaseController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private MspPlatformService mspPlatformService;
	@Resource
	private MspUserService mspUserService;

	@RequestMapping(value = "/wx/login.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson login(HttpServletRequest req, HttpServletResponse resp, String username, String password) {

		AjaxJson json = new AjaxJson();
		boolean flag = false;
		/*ldapCheck test = ldapCheck.getInstance("D:\\tomcat8\\webapps\\ROOT\\WEB-INF\\classes\\config.properties");

		flag = test.checkPassword(username, password);*/
		flag=true;
		if (flag) {
			// 将用户wxid与username及密码存入
			
			String wxid=(String)req.getSession().getAttribute("wxid");

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memeberId", username);
			List<MspUser> muList = mspUserService.findList(map, new PageBounds());
			if (StringUtil.isNotEmpty(wxid)) {
				if (muList != null && muList.size() > 0) {
					MspUser mspUser = muList.get(0);
					mspUser.setUserId(wxid);
					flag = mspUserService.update(mspUser);
					if (flag) {
						req.getSession().setAttribute("wxid", mspUser.getUserId());
						req.getSession().setAttribute("userid", mspUser.getId());
						req.getSession().setAttribute("mspUser", mspUser);
						json.setObj(mspUser);
					} else {
						json.setMsg("MSP登陆----->绑定用户信息失败");
					}
				} else {
					flag = false;
					json.setMsg("数据库不存在此memberId的用户------>memberId:" + username);
				}
			} else {
				flag = false;
				json.setMsg("MSP登陆----->session内wxid不存在");
			}
		}
		/*
		 * String dn = test.getUserDN(username); Set hs =
		 * test.getUserAttribute(username, "cn"); System.out.println(dn);
		 * System.out.println("set:" + hs);
		 */

		json.setSuccess(flag);
		return json;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/wx/loginOut.htm")
	public String loginOut() {

		return "/wmh/wx/wxlogin";
	}
	
	
	@RequestMapping(value = "/wx/getAvatar.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public  String  getAvatar(String userid) {
		String avatar="";
		String url =Constants.GET_USER_URL;
		MspPlatform mspPlatform = mspPlatformService.load(Constants.ALPLATFORMID);
		String accessToken=mspPlatform.getAccessToken();
		url=url.replace("ACCESS_TOKEN", accessToken).replace("USERID", userid);
		JSONObject jsonObject = HttpRequestUtil.httpRequest(url, EnumMethod.GET.name(), null);
		if (jsonObject!=null) {
			Integer errcode=jsonObject.getInt("errcode");
			if (errcode!=null &&errcode==0) {
				avatar=jsonObject.getString("avatar");
			}
		}
		return avatar;
	}
	
	

}
