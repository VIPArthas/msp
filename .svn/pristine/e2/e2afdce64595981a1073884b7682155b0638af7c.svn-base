package com.wh.controller.wmh;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wh.base.PageBounds;
import com.wh.constants.Constants;
import com.wh.controller.common.BaseController;

import com.wh.entity.WmhFaultRepair;
import com.wh.service.wmh.WmhFaultRepairService;
import com.wh.util.BaseModel;
import com.wh.util.PaginationInterceptor;
import com.wh.util.StringUtil;
import com.wh.util.base.BaseUtil;


/**
 * 微门户后台故障维修
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/wmh/malFunction")
public class MalFunctionController extends BaseController{
	
	@Resource
	private WmhFaultRepairService wmhFaultRepairService;
	
	/**
	 * 故障列表
	 * @param request
	 * @param map
	 * @param wmhNews
	 * @return
	 */
	@RequestMapping("/web/malFunctionList.htm")
	public String malFunctionList(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,Integer start,Integer length){
		// start从1开始
		if (start == null) {
			start = 1;
		}
		if (length == null) {
			length = 10;
		}
		PageBounds pageBounds = new PageBounds(start, length);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = wmhFaultRepairService.findList(map, pageBounds);
		
		int count = wmhFaultRepairService.countList();
		BaseUtil.page(modelMap, start, count, length);  
		
	
		PaginationInterceptor.startPage(start,length);
		BaseModel baseModel = PaginationInterceptor.endPage();
		int currentPage=baseModel.getCurrentpage();
		
		modelMap.put("list", list);
		modelMap.put("count", count);
		modelMap.put("currentPage", currentPage);

		return "/wmh/web/malfunction/malfunction";
	}
	
	/**
	 * 根据id获取故障详情
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/web/malById.htm")
	public String malById(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String malId=request.getParameter("malId");
		if (StringUtil.isNotEmpty(malId)) {
			modelMap.put("malId", malId);
			//获取图片路径
			List<String> picPathList =wmhFaultRepairService.getPicPathByLinkId(malId);
			if (picPathList!=null &&picPathList.size()>0) {
				modelMap.put("picPathList", picPathList);
			}
		}
		
		String place=request.getParameter("place");
		if (StringUtil.isNotEmpty(place)) {
			modelMap.put("place", place);
		}
		
		String faultInfo=request.getParameter("faultInfo");
		if (StringUtil.isNotEmpty(faultInfo)) {
			modelMap.put("faultInfo", faultInfo);
		}
		
		String faultReason=request.getParameter("faultReason");
		if (StringUtil.isNotEmpty(faultReason)) {
			modelMap.put("faultReason", faultReason);
		}

		return "/wmh/web/malfunction/malfunctiondetails";
	}
	
	
	
}
