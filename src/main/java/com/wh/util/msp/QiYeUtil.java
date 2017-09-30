package com.wh.util.msp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wh.base.AjaxJson;
import com.wh.entity.SmsVerify;
import com.wh.mspentity.MspDepartment;
import com.wh.mspentity.MspPlatform;
import com.wh.service.msp.MspPlatformService;
import com.wh.service.rgpp.PlatformService;
import com.wh.service.xlwapp.SmsService;
import com.wh.util.MessageCode;
import com.wh.util.StringUtil;
import com.wh.util.WebUtil;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 微信企业号调用类 {"errcode":0,"errmsg":"ok"} 此结果表示调用方法成功返回
 * 
 * @author Sunlight
 * 
 */

public class QiYeUtil {

	private static Logger log = LoggerFactory.getLogger(QiYeUtil.class);

	@Autowired
	private static SmsService smsService;

	
	@Autowired
	private static MspPlatformService platformService;
	
	/**
	 * 获取企业号AccessToken
	 * 
	 * @param CorpID
	 * @param CorpSecret
	 * @return
	 */
	public static AccessToken getAccessToken(String CorpID, String CorpSecret) {
		AccessToken accessToken = WechatAccessToken.getAccessToken(CorpID, CorpSecret, 1);
		return accessToken;
	}

	/**
	 * OAuth2验证接口根据code获取成员信息
	 * 
	 * @param token
	 * @param code
	 * @param AgentID
	 * @return
	 */
	public static Result<String> oAuth2GetUserByCode(String token, String code, int AgentID) {
		Result<String> result = new Result<String>();
		JSONObject jo = WechatOAuth2.getUserByCode(token, code, AgentID);
		if (jo != null) {
			try {
				String userId = jo.getString("UserId");
				if (userId != null && userId.length() > 0) {
					log.info("OAuth2验证接口根据code获取成员信息----> UserId" + userId);
					result.setErrmsg("");
					result.setErrcode("0");
					result.setObj(userId);
				} else {
					result.setErrmsg(jo.getString("errmsg"));
					result.setErrcode(jo.getString("errcode"));
				}

			} catch (Exception e) {
				result.setErrmsg("accessToken 超时......");
				result.setErrcode("42001");
			}
		}
		return result;
	}
	
	/**
	 * 此方法有问题,静态service不执行
	 * 读取用户信息,获取用户头像
	 * @param token
	 * @param userid
	 * @return
	 */
/*	public static String  getAvatar(String userid) {
		String avatar="";
		String url =Constants.GET_USER_URL;
		MspPlatform mspPlatform = platformService.load(Constants.ALPLATFORMID);
		String accessToken=mspPlatform.getAccessToken();
		url=url.replace("ACCESS_TOKEN", accessToken).replace("USERID", userid);
		JSONObject jsonObject = HttpRequestUtil.httpRequest(url, EnumMethod.GET.name(), null);
		if (jsonObject!=null) {
			Integer errcode=jsonObject.getInt("errcode");
			if (errcode!=null &&errcode==0) {
				avatar=jsonObject.getString("avatar");
			}
		}
		return avatar;
	}*/
	
	

	/**
	 * 获取code
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void getCode(HttpServletRequest request, HttpServletResponse response) {
		// 此处可以添加获取持久化的数据，如企业号id等相关信息
		String corpid = Constants.CORPID;
		String redirect_uri = Constants.REDIRECT_URL;
		try {
			redirect_uri = java.net.URLEncoder.encode(redirect_uri, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error("企业号获取code,重定向地址编码错误:" + e.toString());

		}
		String oauth2Url = Constants.OAUTH2_URL;
		oauth2Url = oauth2Url.replace("CORPID", corpid).replace("REDIRECT_URL", redirect_uri);
		log.info("----------获取code---------------oauth2Url:"+oauth2Url);
		try {
			response.sendRedirect(oauth2Url);
		} catch (IOException e) {
			log.error("企业号获取code,重定向失败:" + e.toString());
		}

	}
	
	public static void getMyCode(HttpServletRequest request, HttpServletResponse response) {
		// 此处可以添加获取持久化的数据，如企业号id等相关信息
		String corpid = Constants.CORPID;
		String redirect_uri = Constants.PERSON_REDIRECT_URL;
		try {
			redirect_uri = java.net.URLEncoder.encode(redirect_uri, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error("企业号获取code,重定向地址编码错误:" + e.toString());

		}
		String oauth2Url = Constants.OAUTH2_URL;
		oauth2Url = oauth2Url.replace("CORPID", corpid).replace("REDIRECT_URL", redirect_uri);
		log.info("----------获取code---------------oauth2Url:"+oauth2Url);
		try {
			response.sendRedirect(oauth2Url);
		} catch (IOException e) {
			log.error("企业号获取code,重定向失败:" + e.toString());
		}

	}
	
	public static void getMyCode1(HttpServletRequest request, HttpServletResponse response,String type) {
		// 此处可以添加获取持久化的数据，如企业号id等相关信息
		String corpid = Constants.CORPID;
		String redirect_uri =Constants.LOGIN_REDIRECT_URL;
		redirect_uri=redirect_uri.replace("TYPE",type);
		try {
			redirect_uri = java.net.URLEncoder.encode(redirect_uri, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error("企业号获取code,重定向地址编码错误:" + e.toString());

		}
		String oauth2Url = Constants.OAUTH2_URL;
		oauth2Url = oauth2Url.replace("CORPID", corpid).replace("REDIRECT_URL", redirect_uri);
		log.info("----------获取code---------------oauth2Url:"+oauth2Url);
		try {
			response.sendRedirect(oauth2Url);
		} catch (IOException e) {
			log.error("企业号获取code,重定向失败:" + e.toString());
		}

	}

	// 发送短信调用的方法
	public static void sendSmsMethod(HttpServletRequest request, String phone, String content) {
		try {
			if (com.taobao.api.internal.util.StringUtils.isEmpty(phone)) {
				return;
			} else if (!StringUtil.isMobile(phone)) {
				return;
			}
			if (com.taobao.api.internal.util.StringUtils.isEmpty(content)) {
				return;
			}
			String ip = WebUtil.getIp(request);
			SmsVerify smsVerify = new SmsVerify("", phone, content, "", null, ip, null);
			smsVerify.setOperateIp(ip);
			smsVerify = new MessageCode().sendSMS(smsVerify);
			if (smsVerify != null) {// 发送成功
				smsService.saveSms(smsVerify);
			}
		} catch (Exception e) {
			log.error("异常日志：", e);
		}
	}

	// 发送邮件的方法
	/**
	 *
	 * @param receiveMailAccount
	 *            收件人邮箱
	 * @param userName
	 *            收件人名称
	 * @param mailTitle
	 *            邮件主题
	 * @param mailContent
	 *            邮件内容
	 */
	public static void sendMailMethod(String receiveMailAccount, String userName, String mailTitle,
			String mailContent) {
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		Properties props = new Properties(); // 参数配置
		props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.host", com.wh.util.Constants.MY_EMAIL_SMTP_HOST); // 发件人的邮箱的
																						// SMTP
																						// 服务器地址
		props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log
		try {
			// 3. 创建一封邮件
			MimeMessage message = createMimeMessage(session, com.wh.util.Constants.MY_EMAIL_ACCOUNT, receiveMailAccount,
					userName, mailTitle, mailContent);
			// 4. 根据 Session 获取邮件传输对象
			Transport transport = session.getTransport();
			transport.connect(com.wh.util.Constants.MY_EMAIL_ACCOUNT, com.wh.util.Constants.MY_EMAIL_PASSWORD);
			// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients()
			// 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
			transport.sendMessage(message, message.getAllRecipients());
			// 7. 关闭连接
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMailAccount,
			String userName, String mailTitle, String mailContent) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人
		message.setFrom(new InternetAddress(sendMail, "移动校园平台", "UTF-8"));

		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, userName, "UTF-8"));

		// 4. Subject: 邮件主题
		message.setSubject(mailTitle, "UTF-8");

		// 5. Content: 邮件正文（可以使用html标签）
		message.setContent(mailContent, "text/html;charset=UTF-8");

		// 6. 设置发件时间
		message.setSentDate(new Date());

		// 7. 保存设置
		message.saveChanges();

		return message;
	}

}
