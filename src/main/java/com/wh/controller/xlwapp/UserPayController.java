package com.wh.controller.xlwapp;

import com.wh.constants.Constants;
import com.wh.controller.common.BaseController;
import com.wh.entity.User;
import com.wh.entity.UserCash;
import com.wh.entity.UserPay;
import com.wh.framework.MethodLog;
import com.wh.service.xlwapp.UserCashService;
import com.wh.service.xlwapp.UserPayService;
import com.wh.service.xlwapp.UserService;
import com.wh.util.*;
import com.wh.util.wxpay.Configure;
import com.wh.util.wxpay.HttpsRequest;
import com.wh.util.wxpay.Util;
import com.wh.util.wxpay.protocol.TransfersProtocol.TransfersReqProtocol;
import com.wh.util.wxpay.protocol.TransfersProtocol.TransfersResProtocol;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/common/userPay")
public class UserPayController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserPayService userPayService;

	/**
	 * 校币托管
	 * @return
	 * @author lyk
	 * @date 2016年10月10日
	 */
	@RequestMapping("/w/moneyPay.htm")
	public void viewCash(HttpServletRequest request, HttpSession session, HttpServletResponse response, Double payNum, String code, String password){
		JSONObject object = new JSONObject();
		try {
			User user = WebUtil.getFrontUser(request);
			if (StringUtils.isEmpty(user.getId())) {
				object.put("code", -2);
				object.put("msg", "用户未登陆");
				WebUtil.write(response, object.toString());
				return;
			}
			String payCode = (String) session.getAttribute("codeMsg");
			if (StringUtils.isEmpty(payCode) || StringUtils.isEmpty(code) || !payCode.equals(code)) {
				object.put("code", -1);
				object.put("msg", "验证码不正确");
				WebUtil.write(response, object.toString());
				return;
			}
			user = this.userService.selectUserById(user.getId());
			//根据原型图上，暂时匹配是登陆密码

			if (StringUtils.isEmpty(password) || !Base64Util.encode(password,"utf-8").equals(user.getPassword())) {
				object.put("code", -3);
				object.put("msg", "密码不正确");
				WebUtil.write(response, object.toString());
				return;
			}
			if (StringUtils.isEmpty(payNum)) {
				object.put("code", -4);
				object.put("msg", "校币数量不能为空");
				WebUtil.write(response, object.toString());
				return;
			}
			if (StringUtils.isEmpty(user.getSchoolMoney()) || user.getSchoolMoney() - payNum < 0) {
				object.put("code", -4);
				object.put("msg", "校币数量不足");
				WebUtil.write(response, object.toString());
				return;
			}
			UserPay userPay = new UserPay();
			Long paySwift = this.userPayService.selectNowMaxNum();
			if (StringUtils.isEmpty(paySwift)) {
				// 8位日期，加上三位流水号
				userPay.setPayNum(DateUtil.swiftDateNum(new Date(), "yyyyMMdd")*100 + 1);
			}else {
				userPay.setPayNum(DateUtil.swiftDateNum(new Date(), "yyyyMMdd") + paySwift + 1);
			}
			userPay.setId(UUIDUtil.getUUID());
			userPay.setUserId(user.getId());
			userPay.setReceiverId(Constants.xlwAdminSchoolMoney);	//接收人id(付费给校联网admin)
			userPay.setPaySchoolMoney(payNum);
			userPay.setSchoolMoney(user.getSchoolMoney() - payNum);
			userPay.setPayTime(new Date());
			userPay.setOrderInfo("st");
			userPay.setPayStatus(0); //系统托管
			userPay.setPayRemark("付费成功"); //付费记录(付费成功,失败原因)
			userPay.setPayNote("校园任务校币托管"); //付费备注信息
			this.userPayService.updatePay(userPay);
			object.put("code", 1);
			object.put("msg", "支付成功");

			session.setAttribute("codeMsg","");	//清空session内的验证码信息
		}catch (Exception e) {
			log.error("异常日志：", e);
			object.put("code", -5);
			object.put("msg", "支付异常，请联系管理员");
			WebUtil.write(response, object.toString());
			return;
		}
		WebUtil.write(response, object.toString());
	}
	

	
	
}
