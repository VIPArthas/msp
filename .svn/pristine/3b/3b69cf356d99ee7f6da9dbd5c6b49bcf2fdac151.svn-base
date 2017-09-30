package com.wh.controller.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
@RequestMapping("/GetUserId")
public class GetUserIdController extends BaseController {

	/**
	 * 
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/userId.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public  void getUserId(HttpServletRequest req, HttpServletResponse resp, ModelMap modelmap) {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxcb34702222b6d5c5&redirect_uri=wmh.uni-uni.cn&response_type=code&scope=SCOPE&agentid=1000002&state=STATE#wechat_redirect";
	
	}

}
