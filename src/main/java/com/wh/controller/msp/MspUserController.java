package com.wh.controller.msp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wh.base.AjaxJson;
import com.wh.base.PageBounds;
import com.wh.controller.common.BaseController;
import com.wh.mspentity.MspDepartment;
import com.wh.mspentity.MspMessage;
import com.wh.mspentity.MspPlatform;
import com.wh.mspentity.MspUser;
import com.wh.service.msp.MspDepartmentService;
import com.wh.service.msp.MspPlatformService;
import com.wh.service.msp.MspUserService;
import com.wh.util.HttpClient;
import com.wh.util.StringUtil;
import com.wh.util.UUIDUtil;
import com.wh.util.base.SerializeUtil;
import com.wh.util.msp.Constants;
import com.wh.util.msp.EnumMethod;
import com.wh.util.msp.HttpRequestUtil;
import com.wh.util.msp.MspUtil;
import com.wh.util.msp.QiYeUtil;
import com.wh.util.msp.Result;

import net.sf.json.JSONObject;

/**
 * 企业号用户管理
 * 
 * @author Administrator Leo
 *
 */
@Controller
@RequestMapping("/msp/mspUser")
public class MspUserController extends BaseController {

	@Resource
	private MspPlatformService mspPlatformService;
	@Resource
	private MspUserService mspUserService;

	@Resource
	private MspDepartmentService mspDepartmentService;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/web/goXiaoli.htm")
	public String goSendPhoto() {
		return "/wmh/web/xiaoli/xiaoli";
	}

	@RequestMapping("/web/goSSP.htm")
	public String goSSP() {
		return "/wmh/web/ssp/ssp";
	}

	/**
	 * 获取组织架构 本地
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "/web/DepList.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson DepList(HttpServletRequest req, HttpServletResponse resp) {
		AjaxJson json = new AjaxJson();
		// order 为特殊关键字,需去除,要不无法执行sql语句

		Map<String, Object> map = new HashMap<String, Object>();
		String depID = req.getParameter("depID");
		if (StringUtil.isEmpty(depID)) {
			depID = Constants.LV_ROOTDEP_ID;
		}
		map.put("id", depID);

		List<MspDepartment> mspDepartmentList = mspDepartmentService.findList(map, new PageBounds());
		json.setObj(mspDepartmentList);
		return json;
	}

	/**
	 * 获取组织架构 企业号
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "/web/DepListFromQY.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson DepListFromQY(HttpServletRequest req, HttpServletResponse resp) {
		AjaxJson json = new AjaxJson();
		JSONObject jsonObject = null;
		MspPlatform mspPlatform = mspPlatformService.load(Constants.ALPLATFORMID);
		String requestUrl = Constants.DEPARTMENT_LIST_URL;
		String accessToken = mspPlatform.getAccessToken();
		if (StringUtil.isNotEmpty(accessToken)) {
			requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);

			jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);

			if (jsonObject == null) {
				jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
			}
			// 如果请求成功
			if (null != jsonObject) {

				int errcode = jsonObject.getInt("errcode");
				if (errcode == 0) {
					Gson gson = new GsonBuilder().create();
					List<MspDepartment> departmentList = gson.fromJson(jsonObject.get("department").toString(),
							new TypeToken<List<MspDepartment>>() {
							}.getType());
					json.setObj(departmentList);
				} else {
					log.error("获取部门list errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
				}
			}
		} else {
			log.info("根据accesstoken获取部门list:accessToken获取为空!");
		}

		return json;
	}

	public List<MspDepartment> DepListFromQY1(HttpServletRequest req, HttpServletResponse resp) {
		AjaxJson json = new AjaxJson();
		JSONObject jsonObject = null;
		List<MspDepartment> departmentList = null;
		MspPlatform mspPlatform = mspPlatformService.load(Constants.ALPLATFORMID);
		String requestUrl = Constants.DEPARTMENT_LIST_URL;
		String accessToken = mspPlatform.getAccessToken();

		if (StringUtil.isNotEmpty(accessToken)) {
			requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
			jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
			if (jsonObject == null) {
				jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
			}
			// 如果请求成功
			if (null != jsonObject) {

				int errcode = jsonObject.getInt("errcode");
				if (errcode == 0) {
					Gson gson = new GsonBuilder().create();
					departmentList = gson.fromJson(jsonObject.get("department").toString(),
							new TypeToken<List<MspDepartment>>() {
							}.getType());

					json.setObj(departmentList);
				} else {
					log.error("获取部门list errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
				}
			}
		} else {
			log.info("根据accesstoken获取部门list:accessToken获取为空!");
		}

		return departmentList;
	}

	/**
	 * 获取某个部门下用户数据 企业号
	 * 
	 * @param req
	 * @param resp
	 * @param map
	 * @return
	 */

	public List<Map<String, Object>> GetDepMemberListFromQiYe(HttpServletRequest req, HttpServletResponse resp) {
		String depID = req.getParameter("depID");
		if (StringUtil.isEmpty(depID)) {
			depID = Constants.LV_ROOTDEP_ID;
		}

		JSONObject jsonObject = null;
		List<Map<String, Object>> userlist = null;
		MspPlatform mspPlatform = mspPlatformService.load(Constants.ALPLATFORMID);
		String accessToken = mspPlatform.getAccessToken();
		String requestUrl = Constants.GET_DEPMEMBER_LIST_URL;
		if (StringUtil.isNotEmpty(accessToken)) {
			requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("DEPARTMENT_ID", depID)
					.replace("FETCH_CHILD", "1");

			jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);

			if (jsonObject == null) {
				jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
			}
			// 如果请求成功
			if (null != jsonObject) {

				int errcode = jsonObject.getInt("errcode");
				if (errcode == 0) {
					userlist = (List<Map<String, Object>>) jsonObject.get("userlist");

				} else {
					log.error("企业号获取某个部门下用户数据 errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));

				}
			}
		} else {
			log.info("根据accesstoken企业号获取某个部门下用户数据:accessToken获取为空!");
		}
		return userlist;
	}

	/**
	 * 根据部门id获取部门下用户数据 多条件筛选模糊查询(如手机号,人名)
	 * 
	 * @param req
	 * @param resp
	 * @param start
	 *            起始页码
	 * @param length
	 *            每页条数
	 * @return
	 */
	@RequestMapping(value = "/web/GetDepMemberList.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson GetDepMemberList(HttpServletRequest req, HttpServletResponse resp, Integer start, Integer length) {
		AjaxJson json = new AjaxJson();
		Map<String, Object> map = new HashMap<String, Object>();
		String depID = req.getParameter("depID");
		if (StringUtil.isEmpty(depID)) {
			depID = Constants.LV_ROOTDEP_ID;
		}
		// 搜索条件
		String search = req.getParameter("search");
		if (StringUtil.isNotEmpty(search)) {
			map.put("search", search);
		}

		// 根据部门id获取用户数据
		if (!Constants.LV_ROOTDEP_ID.equals(depID)) {
			map.put("departmentId", depID);
		}
		List<MspUser> muList = null;
		if (start == null && length == null) {
			muList = mspUserService.findList(map, new PageBounds());
		} else {

			muList = mspUserService.findList(map, new PageBounds(start, length));
		}
		Map<String, Object> userMap = new HashMap<String, Object>();
		if (muList != null && muList.size() > 0) {
			// json.setObj(muList);
			userMap.put("muList", muList);
		}
		int count = mspUserService.countList(map);
		if (count != 0) {
			userMap.put("count", count);
			json.setAttributes(userMap);
		}
		return json;
	}

	/**
	 * 同步部门下数据 不支持删除 数据以三楼自研中心 数据为准,所以在微信端是不可编辑,新增,删除的,只支持查看,及自有字段的编辑修改
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "/web/syncMemberList.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson syncMemberList(HttpServletRequest req, HttpServletResponse resp) {

		AjaxJson json = new AjaxJson();
		String depID = req.getParameter("depID");
		if (StringUtil.isEmpty(depID)) {
			depID = Constants.LV_ROOTDEP_ID;
		}
		boolean flag = true;
		// 根据部门id获取用户数据
		Map<String, Object> map = new HashMap<String, Object>();
		if (Constants.LV_ROOTDEP_ID.equals(depID)) {
		} else {
			map.put("departmentId", depID);
		}

		// 获取本地数据库数据
		List<MspUser> muList = mspUserService.findList(map, new PageBounds());

		// 获取企业号数据
		List<Map<String, Object>> userlist = GetDepMemberListFromQiYe(req, resp);
		// 对数据库数据进行新增及更新
		if (userlist != null && userlist.size() > 0) {
			for (Map<String, Object> user : userlist) {
				String userid = user.get("userid").toString();
				String name = user.get("name").toString();
				String department = user.get("department").toString();

				String avatar = user.get("avatar").toString();
				String gender = user.get("gender").toString();
				String mobile = user.get("mobile").toString();
				String email = user.get("email").toString();

				department = department.replace("[", "").replace("]", "");
				if (StringUtil.isEmpty(department)) {
					department = Constants.LV_ROOTDEP_ID;
				}
				int size = muList.size();

				for (int i = 0; i < size; i++) {
					MspUser mspuser = muList.get(i);
					if (mspuser != null) {

						String mspUserId = mspuser.getUserId();
						String mspName = mspuser.getName();
						String mspdepartment = mspuser.getDepartmentId();

						String mspAvatar = mspuser.getAvatar();
						String mspGender = mspuser.getGender();
						String mspEmail = mspuser.getEmail();
						String mspMobile = mspuser.getMobile();

						if (userid.equals(mspUserId)) {
							// id相同,查看部门及姓名是否变化,若变化,需进行调整
							if (!name.equals(mspName) || !department.equals(mspdepartment) || !avatar.equals(mspAvatar)
									|| !gender.equals(mspGender) || !email.equals(mspEmail)
									|| !mobile.equals(mspMobile)) {

								mspuser.setName(name);
								mspuser.setDepartmentId(department);
								mspuser.setCreateTime(new Date());
								mspuser.setAvatar(avatar);
								mspuser.setGender(gender);
								mspuser.setEmail(email);
								mspuser.setMobile(mobile);
								flag = mspUserService.update(mspuser);
								if (!flag) {
									json.setSuccess(flag);
									json.setMsg("同步失败!");
									return json;
								}
							}

							muList.remove(i);
							break;
						}
					}
				}
				// 如何没有重复,则新增
				if (size == muList.size()) {

					MspUser mspUser = new MspUser();
					mspUser.setUserId(userid);
					mspUser.setId(UUIDUtil.getUUID());
					mspUser.setCreateTime(new Date());
					mspUser.setName(name);
					mspUser.setDepartmentId(department);
					mspUser.setAvatar(avatar);
					mspUser.setGender(gender);
					mspUser.setEmail(email);
					mspUser.setMobile(mobile);
					flag = mspUserService.save(mspUser);
					if (!flag) {
						json.setSuccess(flag);
						json.setMsg("同步失败!");
						return json;
					}
				}
			}
		}

		return json;
	}

	/**
	 * 更新用户信息 只支持父母姓名,父母手机号编辑
	 * 
	 * @param req
	 * @param resp
	 * @param mspUser
	 * @return
	 */
	@RequestMapping(value = "/wx/updateUser.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson updateUser(HttpServletRequest req, HttpServletResponse resp, MspUser mspUser) {
		AjaxJson json = new AjaxJson();
		boolean flag = false;
		String id = req.getParameter("id");

		/*
		 * id="e394f34c9acf45b8831b01afca648899"; mspUser.setId(id);
		 * mspUser.setFatherName("刘四"); mspUser.setMotherName("刘好好");
		 * mspUser.setFatherPhone("13592575511");
		 */

		if (StringUtil.isNotEmpty(id)) {
			if (mspUser != null) {
				// 获取用户主键id,在登录时会存入数据库

				flag = mspUserService.update(mspUser);
				if (!flag) {
					json.setMsg("编辑失败!");
				}
			} else {
				json.setMsg("未传递参数或参数名称不对");
			}
		} else {
			json.setMsg("用户id不存在!");
		}

		json.setSuccess(flag);
		return json;
	}

	/**
	 * 解绑功能
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "/web/unBindUser.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson unBindUser(HttpServletRequest req, HttpServletResponse resp) {
		AjaxJson json = new AjaxJson();
		boolean flag = false;
		String id = MspUtil.getMspId(req);

		// id="3c6ab5a0509d4972aaf31e22b674ba0f";

		if (StringUtil.isNotEmpty(id)) {
			MspUser mspUser = mspUserService.load(id);
			if (mspUser != null) {
				if (mspUser.getUserId() != null && mspUser.getMemeberId() != null) {
					mspUser.setUserId(null);
					flag = mspUserService.updateUserIdNull(id);
				} else {
					json.setMsg("用户userId不存在或工号不存在!");
				}
			} else {
				json.setMsg("用户不存在!");
				log.error("用户不存在!--------->id:" + id);
			}
		} else {
			json.setMsg("用户id不存在!");
		}

		json.setSuccess(flag);
		return json;
	}

	/**
	 * 绑定用户
	 * 
	 * @param req
	 * @param resp
	 * @param mspUser
	 * @return
	 */
	@RequestMapping(value = "/web/bindUser.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson bindUser(HttpServletRequest req, HttpServletResponse resp, MspUser mspUser) {
		AjaxJson json = new AjaxJson();
		boolean flag = false;
		// 参数 id, userId
		/*
		 * mspUser.setId("3c6ab5a0509d4972aaf31e22b674ba0f");
		 * mspUser.setUserId("lixiaofei");
		 */
		String id = req.getParameter("id");

		/*
		 * id="e394f34c9acf45b8831b01afca648899"; mspUser.setId(id);
		 * mspUser.setFatherName("刘四"); mspUser.setMotherName("刘好好");
		 * mspUser.setFatherPhone("13592575511");
		 */

		if (StringUtil.isNotEmpty(id)) {
			if (mspUser != null) {
				// 获取用户主键id,在登录时会存入数据库

				flag = mspUserService.update(mspUser);
				if (!flag) {
					json.setMsg("编辑失败!");
				}
			} else {
				json.setMsg("未传递参数或参数名称不对");
			}
		} else {
			json.setMsg("用户id不存在!");
		}

		json.setSuccess(flag);
		return json;
	}

	/**
	 * 展示通讯录管理页面
	 * 
	 * @param req
	 * @param resp
	 * @param start
	 *            起始页
	 * @param length
	 *            每页页数
	 * @return
	 */
	@RequestMapping(value = "/web/showAddressBook.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson showAddressBook(HttpServletRequest req, HttpServletResponse resp,
			@RequestParam(required = true) Integer start, @RequestParam(required = true) Integer length) {
		AjaxJson json = new AjaxJson();
		Map<String, Object> map = new HashMap<String, Object>();
		String depID = req.getParameter("depID");
		if (StringUtil.isEmpty(depID)) {
			depID = Constants.LV_ROOTDEP_ID;
		}
		map.put("id", depID);
		// 获取本地组织架构
		List<MspDepartment> mspDepartmentList = mspDepartmentService.findList(map, new PageBounds());

		MspDepartment parentDep = new MspDepartment();
		if (mspDepartmentList != null && mspDepartmentList.size() > 0) {

			// 查询出其子部门
			List<MspDepartment> childDepList = new ArrayList<MspDepartment>();
			for (MspDepartment mspDepartment : mspDepartmentList) {
				if (depID.equals(mspDepartment.getId())) {
					parentDep = mspDepartment;
				}
				if (depID.equals(mspDepartment.getParentid())) {
					childDepList.add(mspDepartment);
				}
			}
			if (childDepList != null & childDepList.size() > 0) {
				parentDep.setChildDepList(childDepList);
			}
		}
		json.setObj(parentDep);

		Map<String, Object> muMap = new HashMap<String, Object>();
		;

		Map<String, Object> map1 = new HashMap<String, Object>();

		if (!Constants.LV_ROOTDEP_ID.equals(depID)) {
			map1.put("departmentId", depID);
		}
		map1.put("userStatus", 1);
		// 获取本地人员 查询出本部门人员
		List<MspUser> muList = mspUserService.findList(map1, new PageBounds(start, length));
		if (muList != null && muList.size() > 0) {
			muMap.put("muList", muList);

		}
		int count = mspUserService.countList(map1);
		if (count != 0) {
			muMap.put("count", count);
			json.setAttributes(muMap);
		}

		return json;
	}

	/**
	 * 从本地向企业号导入人员数据
	 * 
	 * @param req
	 * @param resp
	 * @param mspUser
	 * @return
	 */
	@RequestMapping(value = "/web/MemberToQiye.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson MemberToQiye(HttpServletRequest req, HttpServletResponse resp, List<MspUser> muList) {
		AjaxJson json = new AjaxJson();
		boolean flag = false;

		json.setSuccess(flag);
		return json;
	}

	/**
	 * 根据用户主键id获取用户信息
	 * 
	 * @param req
	 * @param resp
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/wx/myInfo.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson myInfo(HttpServletRequest req, HttpServletResponse resp, String userid) {
		AjaxJson json = new AjaxJson();
		boolean flag = false;
		// userid="3c6ab5a0509d4972aaf31e22b674ba0f";
		if (StringUtil.isEmpty(userid)) {
			userid = (String) req.getSession().getAttribute("userid");
		}
		Map<String, Object> map = mspUserService.getMyInfo(userid);
		if (map != null) {
			flag = true;
			json.setObj(map);
		}
		json.setSuccess(flag);
		return json;
	}

	/**
	 * 
	 * @param req
	 * @param resp
	 * @param type
	 *            1 个人中心 2消息通知 3 代班信息 4随手拍  5学生缴费 6 图书借阅 7课程安排 8成绩查询
	 */
	@RequestMapping(value = "/wx/goLogin.htm", method = { RequestMethod.POST, RequestMethod.GET })
	public void goLogin(HttpServletRequest req, HttpServletResponse resp, String type) {
		log.info("MSP登陆起始页--------------1---------------");
		String userid = (String) req.getSession().getAttribute("userid");
		String redirectUrl = "";
		if (StringUtil.isNotEmpty(type)) {
			switch (type) {
			case "1":
				redirectUrl = Constants.PERSON_HTML_URL;
				break;
			case "2":
				redirectUrl = Constants.MESSAGE_HTML_URL;
				break;
			case "3":
				redirectUrl = Constants.DAIBAN_HTML_URL;
				break;
			case "4":
				redirectUrl = Constants.SUISHOUPAI_HTML_URL;
				break;
			case "5":
				redirectUrl = Constants.XSJF_HTML_URL;
				break;
			case "6":
				redirectUrl = Constants.TSJY_HTML_URL;
				break;
			case "7":
				redirectUrl = Constants.KCAP_HTML_URL;
				break;
			case "8":
				redirectUrl = Constants.CJCX_HTML_URL;
				break;
			default:
				redirectUrl = Constants.PERSON_HTML_URL;
				break;
			}
		}

		if (StringUtil.isEmpty(userid)) {
			String code = req.getParameter("code");
			if (StringUtil.isNotEmpty(code)) {
				log.info("MSP登陆起始页用户code:" + code);
				MspPlatform mspPlatform = mspPlatformService.load(Constants.MSPPLATFORMID);
				Result result = QiYeUtil.oAuth2GetUserByCode(mspPlatform.getAccessToken(), code, Constants.MSPAGENTID);
				String wxid = (String) result.getObj();
				// 获取到用户的应用内userid 看是否绑定有用户 有直接继续,没有就跳转
				if (StringUtil.isNotEmpty(wxid)) {
					req.getSession().setAttribute("wxid", wxid);
					userid = getUserid(req, wxid);
					try {
						log.info("MSP登陆起始页用户redirectUrl:" + redirectUrl);
						resp.sendRedirect(redirectUrl);
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			} else {
				// 获取code
				QiYeUtil.getMyCode1(req, resp,type);
				return;
			}
		} else {
			try {
				resp.sendRedirect(redirectUrl);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * 处理userid,头像
	 * 
	 * @param request
	 * @param wxid
	 * @return
	 */
	public String getUserid(HttpServletRequest request, String wxid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", wxid);
		String userid = "";
		List<MspUser> muList = mspUserService.findList(map, new PageBounds());
		if (muList != null && muList.size() > 0) {
			MspUser mspUser = muList.get(0);
			userid = mspUser.getId();
			request.getSession().setAttribute("userid", userid);
			//1 教师 2学生
			Integer type=mspUser.getType();
			request.getSession().setAttribute("type", type);
			
			String classId=mspUser.getClassId();
			request.getSession().setAttribute("classId", classId);
			
			log.info("根据用户wxid:" + wxid + ",获取的userid:" + userid);
						
			String avatar = mspUser.getAvatar();
			if (StringUtil.isEmpty(avatar)) {
				avatar = getAvatar(wxid);
				log.info("------------处理一下头像是否获取---------------新avatar:" + avatar);
				if (StringUtil.isNotEmpty(avatar)) {
					mspUser.setAvatar(avatar);
					boolean flag = mspUserService.update(mspUser);
					if (flag) {
						log.info("更新头像成功!----->wxid:" + wxid);
					}
				}
			}
		}
		return userid;
	}

	public String getAvatar(String userid) {
		String avatar = "";
		String url = com.wh.util.msp.Constants.GET_USER_URL;
		MspPlatform mspPlatform = mspPlatformService.load(Constants.ALPLATFORMID);
		String accessToken = mspPlatform.getAccessToken();
		url = url.replace("ACCESS_TOKEN", accessToken).replace("USERID", userid);
		JSONObject jsonObject = HttpRequestUtil.httpRequest(url, EnumMethod.GET.name(), null);
		if (jsonObject != null) {
			Integer errcode = jsonObject.getInt("errcode");
			if (errcode != null && errcode == 0) {
				avatar = jsonObject.getString("avatar");
			}
		}
		return avatar;
	}

}
