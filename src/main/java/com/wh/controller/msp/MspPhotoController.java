package com.wh.controller.msp;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.wh.base.AjaxJson;
import com.wh.base.PageBounds;
import com.wh.constants.Constants;
import com.wh.controller.common.BaseController;
import com.wh.entity.WhAttachements;
import com.wh.mspentity.MspAttachements;
import com.wh.mspentity.MspPhoto;
import com.wh.mspentity.MspReply;
import com.wh.mspentity.ds;
import com.wh.service.jzxx.WhAttachementsService;
import com.wh.service.msp.MspAttachementsService;
import com.wh.service.msp.MspPhotoService;
import com.wh.service.msp.MspReplyService;
import com.wh.service.msp.MspUserService;
import com.wh.service.msp.dsService;
import com.wh.util.DateUtil;
import com.wh.util.FileUtil;
import com.wh.util.MyFileUploadException;
import com.wh.util.StringUtil;
import com.wh.util.UUIDUtil;
import com.wh.util.WebUtil;

import net.sf.json.JSONObject;

/**
 * 随手拍
 * 
 * @author Administrator Leo
 *
 */
@Controller
@RequestMapping("/msp/mspPhoto")
public class MspPhotoController extends BaseController {

	@Resource
	private MspUserService mspUserService;
	@Resource
	private MspPhotoService mspPhotoService;
	@Resource
	private MspAttachementsService mspAttachementsService;
	@Resource
	private MspReplyService mspReplyService;

	@Resource
	private WhAttachementsService whAttachementsService;

	@Resource
	private dsService dsService;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/wx/goSendPhoto.htm")
	public String goSendPhoto() {
		return "/wmh/wx/query/sendphoto";
	}

	/**
	 * 上传保存随手拍信息
	 * 
	 * @param req
	 * @param resp
	 * @param mspPhoto
	 * @return
	 */
	@RequestMapping(value = "/wx/savePhoto.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson savePhoto(HttpServletRequest req, HttpServletResponse resp, MspPhoto mspPhoto) {
		AjaxJson json = new AjaxJson();
		boolean flag = false;
		String path = "/resource/upload/msp/" + DateUtil.format(new Date(), "yyyyMM") + "/";
		List<String> fileList = null;
		try {
			// 得到服务器存储路径List
			fileList = FileUtil.uploadFiles4SpringMVC(req, path, Constants.img_allow_type, 5);
		} catch (MyFileUploadException e) {

			log.error("随手拍上传图片出错:" + e.toString());
		}
		// 存储随手拍信息
		mspPhoto.setCreateTime(new Date());
		mspPhoto.setId(UUIDUtil.getUUID());

		flag = mspPhotoService.save(mspPhoto);
		if (flag) {
			// 存储图片信息
			if (fileList != null && fileList.size() > 0) {
				for (String uploadUrl : fileList) {
					String type = uploadUrl.substring(uploadUrl.lastIndexOf(".") + 1).toLowerCase();
					MspAttachements mspAttachements = new MspAttachements();
					mspAttachements.setLinkId(mspPhoto.getId());
					mspAttachements.setFileType(type);
					mspAttachements.setFilePath(uploadUrl);
					mspAttachements.setCreateTime(new Date());
					flag = mspAttachementsService.save(mspAttachements);
					if (!flag) {
						json.setMsg("保存随手拍图片失败!");
						return json;
					}
				}

			}
		} else {
			json.setMsg("保存随手拍信息失败!");
			return json;
		}
		return json;
	}

	/**
	 * 上传保存校历 使用whAttachements,用于保存校历,仅有一个数据
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */

	@RequestMapping(value = "/wx/saveCalendar.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson saveCalendar(HttpServletRequest req, HttpServletResponse resp) {
		AjaxJson json = new AjaxJson();
		boolean flag = false;
		String path = "/resource/upload/msp/xiaoli/";
		List<String> fileList = null;
		try {
			// 得到服务器存储路径List
			fileList = FileUtil.uploadFiles4SpringMVC(req, path, Constants.img_allow_type, 5);
		} catch (MyFileUploadException e) {

			log.error("上传校历出错:" + e.toString());
		}
		// 存储图片信息
		if (fileList != null && fileList.size() > 0) {
			for (String uploadUrl : fileList) {
				String type = uploadUrl.substring(uploadUrl.lastIndexOf(".") + 1).toLowerCase();

				// WhAttachements whAttachements=new WhAttachements();
				WhAttachements whAttachements = whAttachementsService.findById("1");
				if (whAttachements == null) {
					whAttachements = new WhAttachements();
					whAttachements.setId("1");
					whAttachements.setLinkId(com.wh.util.msp.Constants.Calendar_Link_Id);
					whAttachements.setFileName("校历");
					whAttachements.setFileType(type);
					whAttachements.setFilePath(uploadUrl);
					whAttachements.setCreateTime(new Date());
					whAttachementsService.save(whAttachements);
				} else {
					whAttachements.setFileType(type);
					whAttachements.setFilePath(uploadUrl);
					whAttachements.setCreateTime(new Date());
					whAttachementsService.update(whAttachements);
				}

				flag = true;
				if (!flag) {
					json.setMsg("上传校历失败!");
					return json;
				}
			}
		}
		return json;
	}

	/**
	 * 展示校历,获取校历路径
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "/wx/showCalendar.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson showCalendar(HttpServletRequest req, HttpServletResponse resp) {
		AjaxJson json = new AjaxJson();
		boolean flag = false;
		List<Map<String, Object>> calendarList = whAttachementsService
				.getFilePathByLinkId(com.wh.util.msp.Constants.Calendar_Link_Id);
		if (calendarList != null && calendarList.size() > 0) {
			flag = true;
			json.setObj(calendarList.get(0));
		} else {
			json.setMsg("未获取校历路径");
		}
		return json;
	}

	/**
	 * 随手拍首页展示页面
	 * 
	 * @param req
	 * @param resp
	 * @param type
	 *            1:热门, 2:最新 默认为1
	 * @return
	 */
	@RequestMapping(value = "/wx/showList.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson showList(HttpServletRequest req, HttpServletResponse resp, Integer type, Integer start,
			Integer length) {
		AjaxJson json = new AjaxJson();
		if (type == null) {
			type = 1;
		}
		if (start == null) {
			start = 1;
		}
		if (length == null) {
			length = 10;
		}
		PageBounds pages = new PageBounds(start, length);

		List<Map<String, Object>> mapList = mspPhotoService.showList(type, pages);
		if (mapList != null && mapList.size() > 0) {
			json.setObj(mapList);
		}
		return json;
	}

	/**
	 * 后台随手拍过滤 过滤敏感信息等
	 * 
	 * @param req
	 * @param resp
	 * @param type
	 *            默认为2 最新在最上面
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping(value = "/web/sspList.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson sspList(HttpServletRequest req, HttpServletResponse resp, Integer type, Integer start,
			Integer length) {
		AjaxJson json = new AjaxJson();
		if (type == null) {
			type = 2;
		}
		if (start == null) {
			start = 1;
		}
		if (length == null) {
			length = 10;
		}
		PageBounds pages = new PageBounds(start, length);

		List<Map<String, Object>> mapList = mspPhotoService.showList(type, pages);
		if (mapList != null & mapList.size() > 0) {
			json.setObj(mapList);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int count = mspPhotoService.countList(map);
		if (count != 0) {
			map.put("count", count);
			json.setAttributes(map);
		}

		return json;
	}

	/**
	 * 根据随手拍号获取图片列表
	 * 
	 * @param req
	 * @param resp
	 * @param photoId
	 *            随手拍拍号
	 * @return
	 */
	@RequestMapping(value = "/web/getPhotoListById.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson getPhotoListById(HttpServletRequest req, HttpServletResponse resp, String photoId) {
		AjaxJson json = new AjaxJson();

		if (StringUtil.isNotEmpty(photoId)) {
			List<String> photoUrlList = mspAttachementsService.getPhotoUrlListByLinkId(photoId);
			if (photoUrlList != null && photoUrlList.size() > 0) {
				json.setObj(photoUrlList);
			}
		}
		return json;
	}

	/**
	 * 根据随手拍号删除随手拍相关信息
	 * 
	 * @param req
	 * @param resp
	 * @param photoId
	 *            随手拍拍号
	 * @return
	 */
	@RequestMapping(value = "/web/deleteSSPById.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@Transactional
	@ResponseBody
	public AjaxJson deleteSSPById(HttpServletRequest req, HttpServletResponse resp, String photoId) {
		AjaxJson json = new AjaxJson();

		if (StringUtil.isNotEmpty(photoId)) {
			// 删除随手拍信息
			mspPhotoService.delete(photoId);
			// 删除图片信息
			mspAttachementsService.deleteByLinkId(photoId);
			// 删除评论信息
			mspReplyService.deleteByphotoId(photoId);
		} else {
			json.setMsg("拍号不存在!");
		}
		return json;
	}

	/**
	 * 随手拍详情页
	 * 
	 * @param req
	 * @param resp
	 * @param photoId
	 *            随手拍id
	 * @return
	 */
	@RequestMapping(value = "/wx/showInfo.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson showInfo(HttpServletRequest req, HttpServletResponse resp, String photoId) {
		AjaxJson json = new AjaxJson();
		boolean flag = false;

		// photoId = "e394f34c9acf45b8831b01afca648811";

		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(photoId)) {

			// 每请求一次详情页方法,浏览量加一
			flag = mspPhotoService.updatePageviewNum(photoId);
			if (flag) {

				// 获取随手拍及个人信息部分
				Map<String, Object> mspPhotoMap = mspPhotoService.getShowInfoByPhotoId(photoId);
				json.setObj(mspPhotoMap);
				// 获取图片部分
				List<String> photoUrlList = mspAttachementsService.getPhotoUrlListByLinkId(photoId);
				if (photoUrlList != null && photoUrlList.size() > 0) {
					map.put("photoUrlList", photoUrlList);
				}
				// 获取评论部分

				List<Map<String, Object>> replyList = mspReplyService.getReplyListByPhotoId(photoId);

				if (replyList != null && replyList.size() > 0) {
					map.put("replyList", replyList);

					map.put("replyNum", replyList.size());
				} else {
					map.put("replyNum", 0);
				}
			} else {
				json.setMsg("请求一次详情页方法,浏览量加一失败");
			}
		} else {
			json.setMsg("传入的photoId不能为空");
		}
		json.setAttributes(map);
		return json;
	}

	/**
	 * 发送评论或回复 区别在toUserid是否为null
	 * 
	 * @param req
	 * @param resp
	 * @param MspReply
	 * @return
	 */
	@RequestMapping(value = "/wx/sendReply.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson sendReply(HttpServletRequest req, HttpServletResponse resp, MspReply mspReply) {
		AjaxJson json = new AjaxJson();
		boolean flag = false;

		/*
		 * mspReply.setFromUserid("e394f34c9acf45b8831b01afca648899");
		 * mspReply.setPhotoId("e394f34c9acf45b8831b01afca648811");
		 * mspReply.setToUserid("e394f34c9acf45b8831b01afca648810");
		 * mspReply.setMsg("me too");
		 */

		if (mspReply != null) {
			mspReply.setCreateTime(new Date());
			flag = mspReplyService.save(mspReply);
			if (!flag) {
				json.setMsg("保存发表的评论失败,出现异常");
			}
		} else {
			json.setMsg("未传参或参数不对");
		}
		return json;
	}

	/**
	 * 新校历保存数据
	 * 
	 * @param req
	 * @param resp
	 * @param ds
	 * @return
	 */
	@RequestMapping(value = "/web/savexiaoli.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson savexiaoli(HttpServletRequest req, HttpServletResponse resp) {
		AjaxJson json = new AjaxJson();

		// 获取所有key
		/*
		 * Enumeration<String> paramNames = req.getParameterNames();
		 * 
		 * while (paramNames.hasMoreElements()) { String name =
		 * paramNames.nextElement();
		 * 
		 * String[] values = req.getParameterValues(name);
		 * 
		 * if (values != null && values.length > 0) { StringBuilder builder =
		 * new StringBuilder(); for (int i = 0; i < values.length; i++) {
		 * builder.append(values[i] + " "); } System.out.println(name + " : " +
		 * builder.toString());
		 * 
		 * } }
		 */

		Map<String, String[]> paramMap = req.getParameterMap();
		int size = paramMap.size();
		List<String> mapList = new ArrayList<String>();
		if (size > 0 && size % 6 == 0) {
			for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
				// String paramName = entry.getKey();
				String paramValue = "";
				String[] paramValueArr = entry.getValue();
				for (int i = 0; paramValueArr != null && i < paramValueArr.length; i++) {
					if (i == paramValueArr.length - 1) {
						paramValue += paramValueArr[i];
					} else {
						paramValue += paramValueArr[i] + ",";
					}
				}
				mapList.add(paramValue);

			}
		}
		boolean flag=false;
		flag=dsService.deleteAll();
		if (mapList != null && mapList.size() > 0) {
			// 每6个创建一个对象
			for (int i = 0; i < mapList.size() / 6; i++) {
				ds ds = new ds();
				for (int j = i * 6; j < (i + 1) * 6; j++) {
					int jj = (j + 1) % 6;
					switch (jj) {
					case 1:
						ds.setId(Integer.valueOf(mapList.get(j)));
						break;
					case 2:
						ds.setName((String) mapList.get(j));
						break;
					case 3:
						ds.setLocation((String) mapList.get(j));
						break;
					case 4:
						ds.setStartDate((String) mapList.get(j));
						break;
					case 5:
						ds.setEndDate((String) mapList.get(j));
						break;
					case 0:
						ds.setColor((String) mapList.get(j));
						break;
					default:
						break;
					}
				}
				flag=dsService.save(ds);
			}
		}
		json.setSuccess(flag);
		return json;
	}
	
	
	/**
	 * 查看
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value = "/web/xlList.htm", method = { RequestMethod.POST, RequestMethod.GET })
	public void xlList(HttpServletRequest req, HttpServletResponse resp) {
		JSONObject js = new JSONObject();
		Map<String,Object> map =new HashMap<String,Object>();
		//List<ds> ds= dsService.findList(map, new PageBounds());
		
		List<Map<String,Object>> ds=dsService.findList1(map, new PageBounds());
		// 按照自定义的顺序排列key值(前端需求)
		List<LinkedHashMap<String,Object>> lkds=new ArrayList<LinkedHashMap<String,Object>>();
		
		if (ds!=null&&ds.size()>0) {
			for (Map<String, Object> map2 : ds) {
				LinkedHashMap<String,Object> map1 =new LinkedHashMap<String,Object>();
				map1.put("id", map2.get("id"));
				map1.put("name", map2.get("name"));
				map1.put("location", map2.get("location"));
				map1.put("startDate", map2.get("startDate"));
				map1.put("endDate", map2.get("endDate"));
				map1.put("color", map2.get("color"));			
				lkds.add(map1);
			}
			
			js.put("ds", lkds);
		}
		WebUtil.write(resp, js.toString());
	}
	
	
	/**
	 * 删除
	 * @param req
	 * @param resp
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/web/deleteXL.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson deleteXL(HttpServletRequest req, HttpServletResponse resp, @RequestParam(required = true)Integer id) {
		AjaxJson json = new AjaxJson();
		boolean flag = false;
		flag=dsService.delete(id);
		json.setSuccess(flag);
		return json;
	}
	
	
	
	/**
	 * 更新校历
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value = "/web/updateXL.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson updateXL(HttpServletRequest req, HttpServletResponse resp) {
		AjaxJson json = new AjaxJson();
		Map<String, String[]> paramMap = req.getParameterMap();
		int size = paramMap.size();
		List<String> mapList = new ArrayList<String>();
		if (size > 0 && size % 6 == 0) {
			for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
				String paramValue = "";
				String[] paramValueArr = entry.getValue();
				for (int i = 0; paramValueArr != null && i < paramValueArr.length; i++) {
					if (i == paramValueArr.length - 1) {
						paramValue += paramValueArr[i];
					} else {
						paramValue += paramValueArr[i] + ",";
					}
				}
				System.out.println(paramValue);
				mapList.add(paramValue);
			}
		}
		boolean flag=false;
		if (mapList != null && mapList.size() > 0) {
			// 每6个创建一个对象
			for (int i = 0; i < mapList.size() / 6; i++) {
				ds ds = new ds();
				for (int j = i * 6; j < (i + 1) * 6; j++) {
					int jj = (j + 1) % 6;
					switch (jj) {
					case 1:
						ds.setId(Integer.valueOf(mapList.get(j)));
						break;
					case 2:
						ds.setName((String) mapList.get(j));
						break;
					case 3:
						ds.setLocation((String) mapList.get(j));
						break;
					case 4:
						ds.setStartDate((String) mapList.get(j));
						break;
					case 5:
						ds.setEndDate((String) mapList.get(j));
						break;
					case 0:
						ds.setColor((String) mapList.get(j));
						break;
					default:
						break;
					}
				}
				flag=dsService.update(ds);
			}
		}
		json.setSuccess(flag);
		return json;
	}
}
