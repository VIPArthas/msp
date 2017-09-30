package com.wh.controller.wmh;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wh.entity.User;
import com.wh.service.wmh.WmhMessageService;
import com.wh.service.xlwapp.UserService;
import com.wh.util.DateUtil;
import com.wh.util.StringUtil;
import com.wh.util.WebUtil;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wh.constants.Constants;
import com.wh.controller.common.BaseController;
import com.wh.entity.UserPay;
import com.wh.service.xlwapp.UserPayService;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台支付管理
 *
 * @param
 * @author 王鹏翔
 * @date 2017年3月7日 下午2:34:44
 * @return
 */
@Controller
@RequestMapping("/wmh/payManage")
public class WmhPayManageController extends BaseController {

	@Autowired
	private UserPayService userPayService;

	/**
	 * 支付历史记录列表数据
	 *
	 * @param request
	 * @param map
	 * @param pay
	 * @return
	 * @author 王鹏翔
	 * @Date 2017年3月7日 下午5:47:03
	 */
	@RequestMapping("/web/goPayRecordPage.htm")
	public String goPayRecordPage(HttpServletRequest request, ModelMap map, UserPay pay) {
		if (null != pay.getPayType() && 0 != pay.getPayType()) {
			map.put("payType", pay.getPayType());
		}
		if (null != pay.getPayTime()) {
			Date payDate = pay.getPayTime();
			String beginPayTime = DateUtil.format(payDate, "yyyy-MM-dd");
			String time = beginPayTime;
			beginPayTime = beginPayTime + " 00:00:00"; // 搜索的开始时间
			// 获取搜索支付日期的第二天时间
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(payDate);
			calendar.add(calendar.DATE, +1);
			payDate = calendar.getTime();
			String endPayTime = DateUtil.format(payDate, "yyyy-MM-dd");
			endPayTime = endPayTime + " 00:00:00";// 搜索的结束时间
			Date payDate1 = DateUtil.parseSimple(beginPayTime);
			Date payDate2 = DateUtil.parseSimple(endPayTime);
			pay.setBeginPayTime(payDate1);
			pay.setEndPayTime(payDate2);
			map.put("payData", time);
		}
		try {
			List<Object> payRecordList = this.userPayService.selectWmhPayListPage(pay);
			map.put("payRecordList", payRecordList);
			if (null != pay && !StringUtils.isEmpty(pay.getRealName())) {
				map.put("realName", pay.getRealName());
			}
			map.put("userPay", pay);
		} catch (Exception e) {
			log.error("异常日志：", e);
		}
		return "/wmh/web/pay/onlinePay";
	}

	/**
	 * 微信端后台 支付历史记录列表数据
	 * 
	 * @param request
	 * @param map
	 * @param pay
	 * @return
	 */
	@RequestMapping("/web/goPayRecordPageWx.htm")
	public void goPayRecordPageWx(HttpServletRequest request,HttpServletResponse response, ModelMap map, UserPay pay) {
		
		JSONObject json = new  JSONObject();
		if (null != pay.getPayType() && 0 != pay.getPayType()) {
			map.put("payType", pay.getPayType());
		}
		if (null != pay.getPayTime()) {
			Date payDate = pay.getPayTime();
			String beginPayTime = DateUtil.format(payDate, "yyyy-MM-dd");
			String time = beginPayTime;
			beginPayTime = beginPayTime + " 00:00:00"; // 搜索的开始时间
			// 获取搜索支付日期的第二天时间
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(payDate);
			calendar.add(calendar.DATE, +1);
			payDate = calendar.getTime();
			String endPayTime = DateUtil.format(payDate, "yyyy-MM-dd");
			endPayTime = endPayTime + " 00:00:00";// 搜索的结束时间
			Date payDate1 = DateUtil.parseSimple(beginPayTime);
			Date payDate2 = DateUtil.parseSimple(endPayTime);
			pay.setBeginPayTime(payDate1);
			pay.setEndPayTime(payDate2);
			map.put("payData", time);
		}
		try {
			List<Object> payRecordList = this.userPayService.selectWmhPayListPageWx(pay);
			map.put("payRecordList", payRecordList);
			json.put("payRecordList", payRecordList);
			if (null != pay && !StringUtils.isEmpty(pay.getRealName())) {
				map.put("realName", pay.getRealName());
			}
			map.put("userPay", pay);
		} catch (Exception e) {
			log.error("异常日志：", e);
		}
		
		WebUtil.write(response, json.toString());
	
	}

	
	
	/**
	 * 根据payType 分类查询
	 * @param request
	 * @param response
	 * @param map
	 * @param pay
	 */
	@RequestMapping("/web/goPayRecordPageWx1.htm")
	public String goPayRecordPageWx1(HttpServletRequest request,HttpServletResponse response, ModelMap map, UserPay pay) {
		
		JSONObject json = new  JSONObject();
		if (null != pay.getPayType() && 0 != pay.getPayType()) {
			map.put("payType", pay.getPayType());
		}
		if (null != pay.getPayTime()) {
			Date payDate = pay.getPayTime();
			String beginPayTime = DateUtil.format(payDate, "yyyy-MM-dd");
			String time = beginPayTime;
			beginPayTime = beginPayTime + " 00:00:00"; // 搜索的开始时间
			// 获取搜索支付日期的第二天时间
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(payDate);
			calendar.add(calendar.DATE, +1);
			payDate = calendar.getTime();
			String endPayTime = DateUtil.format(payDate, "yyyy-MM-dd");
			endPayTime = endPayTime + " 00:00:00";// 搜索的结束时间
			Date payDate1 = DateUtil.parseSimple(beginPayTime);
			Date payDate2 = DateUtil.parseSimple(endPayTime);
			pay.setBeginPayTime(payDate1);
			pay.setEndPayTime(payDate2);
			map.put("payData", time);
		}
		try {
			List<Object> payRecordList = this.userPayService.selectWmhPayListPageWx(pay);
			map.put("payRecordList", payRecordList);
			
			if (payRecordList!=null && payRecordList.size()>0) {
				List list=	(List) payRecordList.get(0);
				if (list!=null && list.size()>0) {
					
					Integer totalPage = 0;
					Integer userCount=list.size();
					
					if (userCount % Constants.wmhPageSize == 0) {
						totalPage = userCount /Constants.wmhPageSize;
					} else {
						totalPage = userCount / Constants.wmhPageSize + 1;
					}
					map.put("totalPage", totalPage);
				}else{
					map.put("totalPage", 1);
				}
			}else{
				map.put("totalPage", 1);
			}

			json.put("payRecordList", payRecordList);
			if (null != pay && !StringUtils.isEmpty(pay.getRealName())) {
				map.put("realName", pay.getRealName());
			}
			map.put("userPay", pay);
		} catch (Exception e) {
			log.error("异常日志：", e);
		}
		
	return "/wmh/wx/manage/pay/pay";
	
	}
	
	
	
	
	
	
	
	
	/**
	 * 根据条件获取分页的页数
	 * 
	 * @param request
	 * @param map
	 * @param pay
	 * @return
	 */
	@RequestMapping("/web/getPayCountPageWx.htm")
	public String getPayCountPageWx(HttpServletRequest request, ModelMap modelMap, UserPay pay) {

		Map<String, Object> map = new HashMap<String, Object>();
		String payType = request.getParameter("payType");
		if (StringUtil.isNotEmpty(payType)) {
			map.put("payType", payType);
			modelMap.put("payType", payType);
		}else{
			//原先默认是全部,现将全部去除,默认为考试费
			modelMap.put("payType", "12");
		}
		Integer payCount = userPayService.getPayCountPageWx(map);
		Integer totalPage = 0;
		if (payCount % 20 == 0) {
			totalPage = payCount / 20;
		} else {
			totalPage = payCount / 20 + 1;
		}
		modelMap.put("totalPage", totalPage);
		return "/wmh/wx/manage/pay/pay";
	}

}
