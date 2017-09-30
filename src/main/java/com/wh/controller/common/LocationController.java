package com.wh.controller.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.wh.constants.Constants;
import com.wh.controller.common.BaseController;
import com.wh.util.StringUtil;
import com.wh.util.base.HttpClientUtils;

/**
 * 
 * @author Administrator Leo
 *
 */

@Controller
@RequestMapping("/location")
public class LocationController extends BaseController {

	/**
	 * 百度API精确定位ip地址
	 * PC端
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/position.htm", method = { RequestMethod.POST, RequestMethod.GET })
	public String position(HttpServletRequest req, HttpServletResponse resp, ModelMap modelmap) {
		String position = "";
		String ip = req.getParameter("ip");

		if (StringUtil.isNotEmpty(ip)) {
			String url = "http://api.map.baidu.com/highacciploc/v1?qcip=" + ip + "&qterm=pc&ak=" + Constants.BDWebAK
					+ "&coord=bd09ll&extensions=3";

			position = HttpClientUtils.post(url, null);
			if (StringUtil.isNotEmpty(position)) {
				Gson gson = new Gson();
				Map<String, Object> map = new HashMap<String, Object>();
				map = gson.fromJson(position, map.getClass());
				log.info("百度高精度API返回数据:" + map);
				if (StringUtil.isNotEmpty(map)) {
					if (map.containsKey("content")) {
						Map<String, Object> contMap = (Map<String, Object>) map.get("content");

						if (StringUtil.isNotEmpty(contMap) && contMap.containsKey("formatted_address")
								&& contMap.containsKey("confidence")) {

							String address = contMap.get("formatted_address").toString();
							log.info("address:" + address);
							String confidence = contMap.get("confidence").toString();
							log.info("confidence:" + confidence);
							position = "地址:" + address + "    可信度:" + confidence;
							log.info("UTF-8格式    position:" + position);

							// UTF-8转GBK
							/*
							 * try { byte[] temp = position.getBytes("utf-8");
							 * byte[] newtemp = new String(temp,
							 * "utf-8").getBytes("GBK"); position = new
							 * String(newtemp, "GBK"); log.info(
							 * "GBK格式    position:" + position); return
							 * position; } catch (UnsupportedEncodingException
							 * e) { log.info("GBK格式  转换错误:" + e); }
							 */

							/*
							 * 
							 * String tranData642=null; try { String
							 * tranData=new String(position.getBytes("utf-8"));
							 * position = new
							 * sun.misc.BASE64Encoder().encode(tranData.getBytes
							 * ("GBK")); log.info("GBK格式    position:" +
							 * position); return position; } catch
							 * (UnsupportedEncodingException e) { // TODO
							 * Auto-generated catch block e.printStackTrace(); }
							 * System.out.println("正确的："+position);
							 */

						} else {
							position = "获取的定位信息不全!";
						}
					} else {
						position = "ip地址未录入,定位失败!";
					}

				}
			}

		} else {
			position = "ip地址不能为空!";
		}
		modelmap.put("position", position);
		return "/common/position";

	}
	
	
	
	/**
	 * 百度API精确定位ip地址
	 * 手机端
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/position2.htm", method = { RequestMethod.POST, RequestMethod.GET })
	public String positionMb(HttpServletRequest req, HttpServletResponse resp, ModelMap modelmap) {
		String position = "";
		String ip = req.getParameter("ip");

		if (StringUtil.isNotEmpty(ip)) {
			String url = "http://api.map.baidu.com/highacciploc/v1?qcip=" + ip + "&qterm=mb&ak=" + Constants.BDWebAK
					+ "&coord=bd09ll&extensions=3";

			position = HttpClientUtils.post(url, null);
			if (StringUtil.isNotEmpty(position)) {
				Gson gson = new Gson();
				Map<String, Object> map = new HashMap<String, Object>();
				map = gson.fromJson(position, map.getClass());
				log.info("百度高精度API返回数据:" + map);
				if (StringUtil.isNotEmpty(map)) {
					if (map.containsKey("content")) {
						Map<String, Object> contMap = (Map<String, Object>) map.get("content");

						if (StringUtil.isNotEmpty(contMap) && contMap.containsKey("formatted_address")
								&& contMap.containsKey("confidence")) {

							String address = contMap.get("formatted_address").toString();
							log.info("address:" + address);
							String confidence = contMap.get("confidence").toString();
							log.info("confidence:" + confidence);
							position = "地址:" + address + "    可信度:" + confidence;
							log.info("UTF-8格式    position:" + position);

						} else {
							position = "获取的定位信息不全!";
						}
					} else {
						position = "ip地址未录入,定位失败!";
					}

				}
			}

		} else {
			position = "ip地址不能为空!";
		}
		modelmap.put("position", position);
		return "/common/position";

	}
	
	
	@RequestMapping(value = "/getCode.htm", method = { RequestMethod.POST, RequestMethod.GET })
    public static void getCode(HttpServletRequest request,HttpServletResponse response) {  
        // 此处可以添加获取持久化的数据，如企业号id等相关信息  
        String corpid = "wxcb34702222b6d5c5";  
        String redirect_uri="http://wmh.uni-uni.cn/wmh/wmhUser/wx/goIndex.htm?sign=1";
        try {
			redirect_uri = java.net.URLEncoder.encode(redirect_uri, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
        String oauth2Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + corpid + "&redirect_uri=" + redirect_uri  
                + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
        
        try {
			((HttpServletResponse) response).sendRedirect(oauth2Url);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
    } 
	

}
