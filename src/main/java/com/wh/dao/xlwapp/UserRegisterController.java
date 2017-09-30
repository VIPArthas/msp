package com.wh.dao.xlwapp;

import java.util.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wh.constants.Constants;
import com.wh.controller.common.BaseController;
import com.wh.entity.*;
import com.wh.framework.MethodLog;
import com.wh.service.rgpp.WchatuserPlatService;
import com.wh.service.xlwapp.SmsService;
import com.wh.util.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wh.service.xlwapp.UserService;


@Controller
@RequestMapping("/common/userRegister")
public class UserRegisterController extends BaseController {
   
	@Autowired
	private UserService userService;



	@Autowired
	private SmsService smsService;

	@Autowired
	private WchatuserPlatService wchatuserPlatService;

	/**
	 * 跳到注册页面
	 * @return
	 */
	@RequestMapping("/wx/reg.htm")
	@MethodLog(logRemark="跳到注册页面", logTag="跳到注册页面", logKey="校联网平台前台会员管理", logType=1)
	public String userReg() {
		return "xlwapp/reg";
	}
	/**
	 * 注册用户(安卓端)
	 * @param
	 * @param response
	 */
	@RequestMapping("/android/saveUser.htm")
	@MethodLog(logRemark="注册用户通知是否成功并返回原因", logTag="注册用户通知回调函数", logKey="校联网平台前台会员管理", logType=3)
	public void saveUser(HttpServletResponse response,HttpServletRequest request,String phone,String realName,String authCode,String password,String registerSource) {
		JSONObject object = new JSONObject();
		try{
			
			if ( StringUtils.isEmpty(phone) ) {
				object.put("code", 1);
				object.put("msg", "请输入手机号");
				WebUtil.write(response, object.toString());
				return;
			}
			if ( StringUtils.isEmpty(realName)) {
				object.put("code", 1);
				object.put("msg", "请输入真实姓名");
				WebUtil.write(response, object.toString());
				return;
			}
			if ( StringUtils.isEmpty(password)) {
				object.put("code", 1);
				object.put("msg", "请输入密码");
				WebUtil.write(response, object.toString());
				return;
			}
			if ( !StringUtils.isEmpty(phone)) {
				List<User> users = new ArrayList<User>();
				User user1 = new User();
				user1.setPhone(phone);
				users = this.userService.selectUserByPhone(user1);
				UserRegister userRegister = new UserRegister();
				if (users.size() > 0) {
					object.put("code", 1);
					object.put("msg", "手机号已被注册");
					WebUtil.write(response, object.toString());
					return;
				}else{
					String ip = WebUtil.getIp(request);
					userRegister.setRegisterIp(ip);
					String url = request.getRequestURI();
					User user = new User();
					user.setPhone(phone);
					user.setRealName(realName);
//					user.setPassword(MD5Util.string2MD5(password));
					user.setPassword(Base64Util.encode(password, "utf-8"));
					userRegister.setRegisterTerminal(WebUtil.getRequestTerminal(url));
					userRegister.setRegisterSource(registerSource);
					//TODO 用户注册来源模块
					//userRegister.setRegisterSource(WebUtil.getRequestTerminalCode(url));
					this.userService.saveUser(user,userRegister,null);
					object.put("realName", user.getRealName());
					object.put("phone", user.getPhone());
					object.put("password", user.getPassword());
					
					object.put("code", 0);
					object.put("msg", "注册成功");
				} 
			}else {
				object.put("code", 1);
				object.put("msg", "注册失败，请联系管理员");
			}
			WebUtil.write(response, object.toString());
		} catch (Exception e) {
			log.error("异常日志：", e);
		}
		WebUtil.write(response, object.toString());
	}
	
	@RequestMapping("/android/sendMsgCode.htm")
	public void sendMsgCode2(HttpSession session, HttpServletRequest request,HttpServletResponse response,String phone,String codeType,String userId){
		JSONObject jso = new JSONObject();
		try{
			if(StringUtils.isEmpty(phone) || "".equals(phone)){
				jso.put("code", 1);
				jso.put("msg", "请填写手机号!");
				WebUtil.write(response, jso.toString());
				return;
			}
			if ("0".equals(codeType)) {   // 注册手机号
				User user = new User();
				user.setPhone(phone);
				List<User> users = this.userService.selectUserByPhone(user);
				if ( null != users && users.size() > 0) {
					jso.put("code", 1);
					jso.put("msg", "手机号已被注册，请更换手机号!");
					WebUtil.write(response, jso.toString());
					return;
				}
			}
			if ("1".equals(codeType)) {   // 修改手机号时发送
				User users = this.userService.selectUserById(userId);
				String p=users.getPhone();
				if(p.equals(phone)){
					jso.put("code", 1);
					jso.put("msg", "手机号已被注册!");
					WebUtil.write(response, jso.toString());
					return;
				}
			}
			if("2".equals(codeType)) {     //修改密码时发送
				User users = this.userService.selectUserById(userId);
				if(users.getPhone().equals(phone)){
					jso.put("code", 1);
					jso.put("msg", "您输入的手机号有误，请重新输入!");
					WebUtil.write(response, jso.toString());
					return;
				}
			}
			if (phone.length()==11){
//				String code = "12345";
				String code = MessageCode.genVerifyCode();
				String ip = WebUtil.getIp(request);
				Integer operateTerminal = WebUtil.getRequestTerminal(request.getRequestURI());
				SmsVerify smsVerify = new SmsVerify(userId, phone, code, null, null, ip, operateTerminal);
				smsVerify = MessageCode.sendCode(smsVerify);
				if(smsVerify != null){//发送成功
					smsService.saveSms(smsVerify);
					session.setAttribute("codeMsg",code);
					session.setAttribute("phone",phone);
					jso.put("code", 0);
					jso.put("msg", "发送成功!");
					WebUtil.write(response, jso.toString());
				}
			}else{
				jso.put("code", 1);
				jso.put("msg", "你输入的手机号不合法!");
			}
		}catch (Exception e) {
			log.error("异常日志：", e);
		}
		WebUtil.write(response, jso.toString());
	}
	/**
	 * @Description: ${todo}(注册手机的时候检查手机号、发送验证码)
	 * @author lp
	 * @date
	 * @return ${return_type}    返回类型
	 *
	 */
	@RequestMapping("/wx/checkPhone.htm")
	public void checkPhone(HttpServletRequest request,HttpServletResponse response,String phone,String codeType,HttpSession session){
		JSONObject jso = new JSONObject();
		try{
			if(StringUtils.isEmpty(phone) || "".equals(phone)){
				jso.put("code", 1);
				jso.put("msg", "请填写手机号!");
				WebUtil.write(response, jso.toString());
				return;
			}
			if ("0".equals(codeType)) {   // 注册手机号
				User user = new User();
				user.setPhone(phone);
				List<User> users = this.userService.selectUserByPhone(user);
				if ( null != users && users.size() > 0) {
					jso.put("code", 1);
					jso.put("msg", "手机号已被注册，请更换手机号!");
					WebUtil.write(response, jso.toString());
					return;
				}
			}
			if (phone.length()==11){
				String code = MessageCode.genVerifyCode();
				String ip = WebUtil.getIp(request);
				Integer operateTerminal = WebUtil.getRequestTerminal(request.getRequestURI());
				User user=WebUtil.getUser(request);
				SmsVerify smsVerify = new SmsVerify(user.getId(), phone, code, null, null, ip, operateTerminal);
				smsVerify = MessageCode.sendCode(smsVerify);
				if(smsVerify != null){//发送成功
					smsService.saveSms(smsVerify);
					session.setAttribute("codeMsg",code);
					session.setAttribute("new_phone",phone);
					jso.put("code", 0);
					jso.put("msg", "发送成功!");
					WebUtil.write(response, jso.toString());
				}
			}else{
				jso.put("code", 1);
				jso.put("msg", "你输入的手机号不合法!");
			}
		}catch (Exception e) {
			log.error("异常日志：", e);
		}
		WebUtil.write(response, jso.toString());
	}
	/**
	 * 注册用户
	 * @param user
	 * @param response
	 */
	@RequestMapping("/wx/saveUser.htm")
	@MethodLog(logRemark="注册用户通知是否成功并返回原因", logTag="注册用户通知回调函数", logKey="校联网平台前台会员管理", logType=3)
	public void saveUser(User user, HttpServletResponse response,HttpServletRequest request, HttpSession session) {
		JSONObject object = new JSONObject();
		try{
			String code = request.getParameter("code");
			String codeMsg = (String) session.getAttribute("codeMsg");
			String regPassword = "^[A-Za-z0-9]+$";

			if ( null == user || ( user != null && StringUtils.isEmpty(user.getPhone())) ) {
				object.put("code", -1);
				object.put("msg", "请输入手机号");
				WebUtil.write(response, object.toString());
				return;
			}
			if (null == user || ( user != null && StringUtils.isEmpty(user.getRealName()))) {
				object.put("code", -1);
				object.put("msg", "请输入姓名");
				WebUtil.write(response, object.toString());
				return;
			}
			if (null == user || ( user != null && StringUtils.isEmpty(user.getPassword()))) {
				object.put("code", -1);
				object.put("msg", "请输入密码");
				WebUtil.write(response, object.toString());
				return;
			} else if(StringUtils.isEmpty(user.getPassword())) {//!(user.getPassword().matches(regPassword) && user.getPassword().length() >= 6 && user.getPassword().length() <= 12)
				object.put("code", -1);
				object.put("msg", "请输入6-12位数字加字母的密码");
				WebUtil.write(response, object.toString());
				return;
			}
			if (null != user && !StringUtils.isEmpty(user.getPhone())) {
				List<User> users = new ArrayList<>();
				user.setPassword(user.getPassword());//MD5Util.string2MD5(user.getPassword())
				users = this.userService.selectUserByPhone(user);
				UserRegister userRegister = new UserRegister();
				if (users.size() > 0) {
					object.put("code", -1);
					object.put("msg", "手机号已被注册");
				}else if (null != code && code.equals(codeMsg)){
					String ip = WebUtil.getIp(request);
					userRegister.setRegisterIp(ip);
					String url = request.getRequestURI();
					userRegister.setRegisterTerminal(WebUtil.getRequestTerminal(url));
					//因为在校联网系统中，注册页面只能是从login页面中点出来，所以记录注册的来源模块就是记录登陆的来源模块
					String interceptorUrl = (String) session.getAttribute("interceptorUrl");
					if(!StringUtils.isEmpty(interceptorUrl)){
						userRegister.setRegisterSource(WebUtil.getRequestModel(interceptorUrl));
					}else{
						userRegister.setRegisterSource("1");
					}
					TbWchatuser wchatUser = null;
					Integer requestTerminal = userRegister.getRegisterTerminal();
					if(null != requestTerminal && requestTerminal == Constants.terminalWx){
						String openId = (String)session.getAttribute("openId");
						if(!StringUtils.isEmpty(openId)){
							wchatUser = wchatuserPlatService.findWchatUserByOpenId(openId);
						}
					}
					this.userService.saveUser(user,userRegister, wchatUser);
					object.put("code", 1);
					object.put("msg", "注册成功");
				} else {
					object.put("code", -1);
					object.put("msg", "验证码不正确");
				}
			}else {
				object.put("code", -1);
				object.put("msg", "注册失败，请联系管理员");
			}
			WebUtil.write(response, object.toString());
		} catch (Exception e) {
			log.error("异常日志：", e);
		}
	}
	

	/**
	 * 发送验证码
	 * @param session
	 * @param response
	 * @param request
	 */
	@RequestMapping("/wx/sendMsgCode.htm")
	public void sendMsgCode(HttpSession session,HttpServletResponse response, HttpServletRequest request){
		try{
			String result = sendVerifyCode(session, request);
			ControllerUtils.outputContent(response,result);
		}catch (Exception e) {
			log.error("异常日志：", e);
		}
	}

	/**
	 * wx端短信发送私有方法，待与手机端的合并
	 * @param session
	 * @param request
	 * @return
	 * @author 徐优优
	 * @date 2016年8月4日
	 */
	private String sendVerifyCode(HttpSession session, HttpServletRequest request){
		String result = "{\"code\":404}";
		try{
			String new_phone = request.getParameter("new_phone");
			String flag = request.getParameter("flag");

			User user = new User();
			if ("1".equals(flag)) {   // 修改手机号
				user.setPhone(new_phone);
//				List<User> users = this.userService.selectUserByPhone(user);
//				if ( null != users && users.size() > 0) {
//					result = "{'code':'-1', 'msg':'手机号已被注册'}";
//					return result;
//				}
			}

			if("2".equals(flag)) {     //找回密码
				user.setPhone(new_phone);
				List<User> users = this.userService.selectUserByPhone(user);
				if ( null == users || users.size() == 0) {
					result = "{'code':'-1', 'msg':'账号不存在'}";
					return result;
				}
			}
			if (new_phone.length()==11){
				String userId = WebUtil.getFrontUserId(request);
				//String code = "12345";
				String code = MessageCode.genVerifyCode();
				String ip = WebUtil.getIp(request);
				Integer operateTerminal = WebUtil.getRequestTerminal(request.getRequestURI());
				SmsVerify smsVerify = new SmsVerify(userId, new_phone, code, null, null, ip, operateTerminal);
				smsVerify = MessageCode.sendCode(smsVerify);
				if(smsVerify != null){//发送成功
					smsService.saveSms(smsVerify);
					session.setAttribute("codeMsg",code);
					session.setAttribute("new_phone",new_phone);
					result = "{'code':'1', 'msg':'发送成功'}";
				}
			}
		}catch (Exception e) {
			log.error("异常日志：", e);
		}
		return result;
	}
}
