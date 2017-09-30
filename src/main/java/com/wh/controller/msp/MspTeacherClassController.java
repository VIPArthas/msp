package com.wh.controller.msp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wh.base.AjaxJson;
import com.wh.base.PageBounds;
import com.wh.controller.common.BaseController;
import com.wh.mspentity.MspUser;
import com.wh.service.msp.MspClassService;
import com.wh.service.msp.MspTeacherClassService;
import com.wh.service.msp.MspUserService;
import com.wh.util.StringUtil;

@Controller
@RequestMapping("/msp/mspTeacherClass")
public class MspTeacherClassController extends BaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private MspUserService mspUserService;

	@Resource
	private MspClassService mspClassService;

	@Resource
	private MspTeacherClassService mspTeacherClassService;

	/**
	 * 教师代班信息
	 * 
	 * @param req
	 * @param resp
	 * @param userid
	 *            用户主键id
	 * @return
	 */
	@RequestMapping(value = "/wx/teacherClassList.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson teacherClassList(HttpServletRequest req, HttpServletResponse resp,
			 String userid) {
		AjaxJson json = new AjaxJson();
		if (StringUtil.isEmpty(userid)) {
			userid = (String) req.getSession().getAttribute("userid");
		}
		MspUser mspUser = mspUserService.load(userid);
		String memberId = null;
		boolean flag = false;
		if (mspUser != null) {
			memberId = mspUser.getMemeberId();
			if (StringUtil.isNotEmpty(memberId)) {
				flag = true;
				List<Map<String, Object>> classList = mspTeacherClassService.tecClassListByMembId(memberId);
				if (classList != null && classList.size() > 0) {
					Map<String, Object> class1 = classList.get(0);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("classId", class1.get("mcid"));
					map.put("userStatus", 1);
					List<MspUser> muList = mspUserService.findList(map, new PageBounds());
					if (muList != null & muList.size() > 0) {
						Map<String, Object> map1 = new HashMap<String, Object>();
						map1.put("muList", muList);
						json.setAttributes(map1);
					}
				}
				json.setObj(classList);
			} else {
				json.setMsg("用户工号为空");
			}
		}
		json.setSuccess(flag);

		return json;
	}

	/**
	 * 根据班级id查找人员
	 * 
	 * @param req
	 * @param resp
	 * @param classId
	 *            班级id
	 * @param search
	 *            模糊查询(人名或手机号)
	 * @return
	 */
	@RequestMapping(value = "/wx/classMemberList.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson classMemberList(HttpServletRequest req, HttpServletResponse resp,
			@RequestParam(required = true) String classId, String search) {
		AjaxJson json = new AjaxJson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("classId", classId);
		if (StringUtil.isNotEmpty(search)) {
			map.put("search", search);
		}
		List<MspUser> muList = mspUserService.findList(map, new PageBounds());

		if (muList != null & muList.size() > 0) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("muList", muList);
			json.setAttributes(map1);
		}

		return json;
	}

}
