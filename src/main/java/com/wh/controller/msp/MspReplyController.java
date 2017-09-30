package com.wh.controller.msp;

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
import com.wh.controller.common.BaseController;
import com.wh.mspentity.MspPhoto;
import com.wh.service.msp.MspUserService;


/**
 * 随手拍
 * 
 * @author Administrator Leo
 *
 */
@Controller
@RequestMapping("/msp/mspReply")
public class MspReplyController extends BaseController {

	@Resource
	private MspUserService mspUserService;

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 新闻展示
	 * @param req
	 * @param resp
	 * @param news
	 * @return
	 */
	@RequestMapping(value = "/wx/savePhoto.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson getReplyListByPhotoId(HttpServletRequest req, HttpServletResponse resp, String photoId) {
		
		AjaxJson json = new AjaxJson();
		
		return json;
	}

}
