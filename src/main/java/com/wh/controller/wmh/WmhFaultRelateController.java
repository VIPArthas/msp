package com.wh.controller.wmh;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.base.AjaxJson;
import com.wh.base.PageBounds;
import com.wh.constants.Constants;
import com.wh.controller.common.BaseController;
import com.wh.entity.WmhFaultRelate;
import com.wh.service.wmh.WmhFaultRelateService;
import com.wh.util.StringUtil;

/**
 * 故障报修关系
 * 
 * @author Administrator Leo
 *
 */
@Controller
@RequestMapping("/wmh/faultRelate")
public class WmhFaultRelateController extends BaseController {

	@Autowired
	private WmhFaultRelateService wmhFaultRelateService;

	/**
	 * 故障维修责任人列表
	 * 
	 * @param req
	 * @param resp
	 * @param modelMap
	 * @param start
	 *            起始页 从1开始
	 * @param length
	 *            每页页数
	 * @return
	 */
	@RequestMapping(value = "/web/faultUserList.htm")
	public String faultUserList(HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Integer start,
			Integer length) {
		// start从1开始
		if (start == null) {
			start = 1;
		}
		if (length == null) {
			length = Constants.pageSize;
		}
		PageBounds pageBounds = new PageBounds(start, length);
		Map<String, Object> map = new HashMap<String, Object>();
		// 责任人模糊查询
		String respersonName = req.getParameter("respersonName");
		if (StringUtil.isNotEmpty(respersonName)) {
			map.put("respersonName", respersonName);
		}
		List<Map<String, Object>> wmhFaultRelateList = wmhFaultRelateService.findfaultUserList(map, pageBounds);

		if (wmhFaultRelateList != null && wmhFaultRelateList.size() > 0) {
			modelMap.put("wmhFaultRelateList", wmhFaultRelateList);
		}
		// BaseUtil.page(modelMap, start, count, length);

		// modelMap.put("count", count);
		modelMap.put("respersonName", respersonName);

		return "/lx/web/manager/user";
	}

	/**
	 * 删除负责人
	 * 
	 * @param req
	 * @param res
	 * @param id
	 *            此区域此物品下的负责人
	 * @return
	 */
	@RequestMapping(value = "/web/deleteById.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson deleteById(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(required = true) Integer id) {
		AjaxJson json = new AjaxJson();
		// 获取此区域此物品下的负责人
		WmhFaultRelate wmhFaultRelate = wmhFaultRelateService.load(id);
		wmhFaultRelate.setRespersonId("");
		wmhFaultRelate.setRespersonName("");
		Boolean flag = wmhFaultRelateService.update(wmhFaultRelate);
		json.setSuccess(flag);
		return json;
	}

	/**
	 * 只能编辑责任人 其他不可变动 可变更原先负责人
	 * 
	 * @param req
	 * @param res
	 * @param id
	 *            负责人id
	 * @return
	 */
	@RequestMapping(value = "/web/updateById.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson updateById(HttpServletRequest req, HttpServletResponse res, WmhFaultRelate wmhFaultRelate) {
		AjaxJson json = new AjaxJson();
		boolean flag = false;

		/*wmhFaultRelate.setId(4);
		wmhFaultRelate.setRespersonId("7bf4978770bf4156905a93342b5a4da7");
		wmhFaultRelate.setRespersonName("李晓飞");*/

		if (wmhFaultRelate != null) {
			
			flag = wmhFaultRelateService.update(wmhFaultRelate);
		}
		json.setSuccess(flag);
		return json;
	}

	/**
	 * 新增负责人 参数:区域id,物品id,负责人id,负责人姓名
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/web/saveResperson.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxJson saveResperson(HttpServletRequest req, HttpServletResponse res, WmhFaultRelate wmhFaultRelate) {
		AjaxJson json = new AjaxJson();
		boolean flag = false;
		if (wmhFaultRelate != null) {
			// 判断此区域下此物品是否已有负责人
			Integer areaId = wmhFaultRelate.getAreaId();
			Integer goodsId = wmhFaultRelate.getGoodsId();

			/*
			 * areaId=1; goodsId=4;
			 * wmhFaultRelate.setRespersonId("7bf4978770bf4156905a93342b5a4da7")
			 * ; wmhFaultRelate.setRespersonName("李晓飞");
			 */

			Map<String, Object> map = new HashMap<String, Object>();
			if (areaId != null && goodsId != null) {

				map.put("areaId", areaId);
				map.put("goodsId", goodsId);
				List<WmhFaultRelate> wfrList = wmhFaultRelateService.findList(map, new PageBounds());
				if (wfrList != null && wfrList.size() == 1) {
					WmhFaultRelate wmhFaultRelate1 = wfrList.get(0);
					String respersonId = wmhFaultRelate1.getRespersonId();
					String respersonName = wmhFaultRelate1.getRespersonName();
					if (StringUtil.isNotEmpty(respersonId) && StringUtil.isNotEmpty(respersonName)) {
						json.setMsg("此项已有负责人存在");
					} else {
						wmhFaultRelate.setId(wmhFaultRelate1.getId());
						wmhFaultRelate.setCreateTime(new Date());
						flag = wmhFaultRelateService.update(wmhFaultRelate);
						if (flag) {
							json.setSuccess(flag);
							return json;
						} else {
							json.setMsg("保存负责人信息失败");
							return json;
						}
					}
				} else {
					json.setMsg("获取此区域及物品下的数据有误");
				}
			} else {
				json.setMsg("区域不能为空或物品不能为空");
			}
		} else {
			json.setMsg("区域不能为空或物品不能为空");
		}

		json.setSuccess(flag);
		return json;
	}

}
