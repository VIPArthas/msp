package com.wh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wh.entity.*;
import com.wh.service.wmh.WmhUserService;
import com.wh.service.xlwapp.UserService;
import com.wh.service.xlwapp.impl.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.wh.constants.Constants;
import com.wh.dao.xlwapp.UserMapper;
import com.wh.model.RealNameModel;
import com.wh.pojo.AccessToken;

import net.sf.json.JSONObject;

/**
 * 工具类
 * @author Administrator
 *
 */
public class WebUtil {
	
	private static Logger log = LoggerFactory.getLogger(WeiXinPlatformUtil.class);

	public static UserService userService;
	public static WmhUserService wmhUserService;
	/**
	 * 判断一个请求是否为AJAX请求
	 * @param request
	 * @return
	 * 
	 */
	public static boolean isAjaxRequest(HttpServletRequest request){
		String requestType = request.getHeader("X-Requested-With");
		if (requestType!=null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
			return true;
		}
		return false;
	}
	/**
	 * 初始密码获取，身份证后六位
	 */
	public static String getInitPwd(String id_card){
		String pwd = id_card.substring(id_card.length() - 6, id_card.length());
		return MD5Util.string2MD5(pwd);
	}
	
	/**
	 * 根据字符串生成密码
	 * @author 郑爽
	 * @version 1.0
	 * @date 2015年12月24日
	 */
	public static String getInitPwdBystr(String str){
		return MD5Util.string2MD5(str);
	}
	/**
	 * 从session中获取校联网后台管理员登陆信息
	 * @Description 
	 * @param
	 * @return
	 * @date 2015年11月3日下午2:14:10
	 * @author 郑爽
	 */
	public static ManageUser getManageLoginUser(HttpServletRequest request){
		return (ManageUser)request.getSession().getAttribute(Constants.manage_session_user_info);
	}
	/**
	 * 从session中获取校联网后台管理员登陆信息
	 * @Description 
	 * @param
	 * @return
	 * @date 2015年11月3日下午2:14:10
	 * @author 郑爽
	 */
	public static UserInfo getLoginUser(HttpServletRequest request){
		return (UserInfo)request.getSession().getAttribute(Constants.session_user_info);
	}
	
	/**
	 * 以utf-8编码向客户写入信息
	 * @param str
	 */
	public static void write(HttpServletResponse response, String str){
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer =response.getWriter();
			writer.print(str);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取用户客户端ip
	 * @param request
	 * @return
	 * @author 李亚坤
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if(ip.equals("127.0.0.1")){
				//根据网卡取本机配置的IP
				InetAddress inet=null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ip= inet.getHostAddress();
			}

		}

		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15
			if(ip.indexOf(",")>0){
				ip = ip.substring(0,ip.indexOf(","));
			}
		}
		return ip;
	}
	
	/**
	 * 设置cookie
	 * @param response
	 * @param name  cookie名字
	 * @param value cookie值
	 * @param
	 * @author 李亚坤
	 */
	public static void addCookie(HttpServletResponse response,String name,String value){
		Cookie cookie = new Cookie(name,value);
		cookie.setPath("/xlw");
		cookie.setMaxAge(60 * 60 * 48); //maxAge cookie生命周期  以秒为单位

		response.addCookie(cookie);
	}
	



	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 * @author 李亚坤
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
		Map<String,Cookie> cookieMap = ReadCookieMap(request);
		if(cookieMap.containsKey(name)){
			Cookie cookie = (Cookie)cookieMap.get(name);
			return cookie;
		}else{
			return null;
		}
	}



	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 * @author 李亚坤
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		Cookie[] cookies = request.getCookies();
		if(null!=cookies){
			for(Cookie cookie : cookies){
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	/**
	 * 删除cookie
	 * @param request
	 * @param response
	 * @param name
	 * @author 李亚坤
	 */
	public static void delCookie(HttpServletRequest request,HttpServletResponse response,String name){
		Cookie[] cookies = request.getCookies();
		if(null==cookies) {
			System.out.println("没有cookie==============");
		} else{
			for(Cookie cookie : cookies){
				if(cookie.getName().equals(name)){
					cookie.setPath("/xlw");
					cookie.setValue(null);
					cookie.setMaxAge(0);// 立即销毁cookie
					response.addCookie(cookie);
					break;
				}
			}
		}
	}

	/**
	 * 查询访问的模块
	 * @param
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getRequestModel(String reqeustUrl) {
		String requestModel = "";
		if(!StringUtils.isEmpty(reqeustUrl)){
			int subIndexStart = 0;
			int subIndexEnd = 0;
			if(reqeustUrl.indexOf("/xlwWeb/") > -1){
				subIndexStart = "/xlwWeb/".length();
			}else{
				subIndexStart = 1;
			}
			subIndexEnd = reqeustUrl.indexOf("/", subIndexStart);
			if(subIndexEnd > 1 && reqeustUrl.length() > subIndexStart && reqeustUrl.length() > subIndexEnd){
				requestModel = reqeustUrl.substring(subIndexStart, subIndexEnd);
			}
			
		}
		return requestModel;
	}
	
	/**
	 * 根据url查询访问的终端值
	 * @param reqeustUrl
	 * @return
	 */
	public static int getRequestTerminal(String reqeustUrl){
		int requestTerminal = 0;
		String requestTerminalCode = "";
		if(!StringUtils.isEmpty(reqeustUrl)){
			int subIndexStart = 0;
			int subIndexEnd = 0;
			if(reqeustUrl.indexOf("/xlwWeb/") > -1){
				subIndexStart = "/xlwWeb/".length() + 1;
				subIndexStart = reqeustUrl.indexOf("/", subIndexStart) + 1;
				subIndexStart = reqeustUrl.indexOf("/", subIndexStart) + 1;
			}else{
				subIndexStart = 1;
				subIndexStart = reqeustUrl.indexOf("/", subIndexStart) + 1;
				subIndexStart = reqeustUrl.indexOf("/", subIndexStart) + 1;
			}
			subIndexEnd = reqeustUrl.indexOf("/", subIndexStart);
			if(reqeustUrl.length() > subIndexStart && reqeustUrl.length() > subIndexEnd){
				requestTerminalCode = reqeustUrl.substring(subIndexStart, subIndexEnd);
			}
			if(requestTerminalCode.equals(Constants.terminalCodeWeb)){
				requestTerminal = Constants.terminalWeb;
			}else if(requestTerminalCode.equals(Constants.terminalCodeWx)){
				requestTerminal = Constants.terminalWx;
			}else if(requestTerminalCode.equals(Constants.terminalCodeWap)){
				requestTerminal = Constants.terminalWap;
			}else if(requestTerminalCode.equals(Constants.terminalCodeIos)){
				requestTerminal = Constants.terminalIos;
			}else if(requestTerminalCode.equals(Constants.terminalCodeAndriod)){
				requestTerminal = Constants.terminalAndriod;
			}
		}
		return requestTerminal;
	}
	
	/**
	 * 根据url查询访问的终端Code
	 * @param reqeustUrl
	 * @return web wap wx ios an
	 */
	public static String getRequestTerminalCode(String reqeustUrl){
		String requestTerminalCode = "";
		if(!StringUtils.isEmpty(reqeustUrl)){
			int subIndexStart = 0;
			int subIndexEnd = 0;
			if(reqeustUrl.indexOf("/xlwWeb/") > -1){
				subIndexStart = "/xlwWeb/".length() + 1;
				subIndexStart = reqeustUrl.indexOf("/", subIndexStart) + 1;
				subIndexStart = reqeustUrl.indexOf("/", subIndexStart) + 1;
			}else{
				subIndexStart = 1;
				subIndexStart = reqeustUrl.indexOf("/", subIndexStart) + 1;
				subIndexStart = reqeustUrl.indexOf("/", subIndexStart) + 1;
			}
			subIndexEnd = reqeustUrl.indexOf("/", subIndexStart);
			if(reqeustUrl.length() > subIndexStart && reqeustUrl.length() > subIndexEnd){
				requestTerminalCode = reqeustUrl.substring(subIndexStart, subIndexEnd);
			}
		}
		return requestTerminalCode;
	}
	
	/**
	 * 根据用户的积分值获取用户的等级
	 * @param score
	 * @return
	 * @author 徐优优
	 * @date 2016年4月21日
	 */
	public static Integer getLevelByScore(Double score){
		Integer level = 0;
		if(null != score){
			if(score >= 100 && score < 400){
				level = 1;
			}else if(score >= 400 && score < 800){
				level = 2;
			}else {
				//TODO 计算具体的level等级
				//level = 1;
			}
		}
		return level;
	}
	/**
	 * 
	 * @param request
	 * @return
	 * @author 徐优优
	 * @date 2016年4月27日
	 */
	public static String getFrontUserId(HttpServletRequest request){
		String userId = null;
		HttpSession session = request.getSession();
		userId = (String) session.getAttribute("userId");
		return userId;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @author 徐优优
	 * @date 2016年4月27日
	 */
	public static User getFrontUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("frontUser");
		return user;
	}
	public static User getTest(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("frontUser");
		return user;
	}
	/**
	 * 
	 * @Title: getUser 
	 * @Description: 获取当前用户信息（目前仅供校园任务使用）
	 * @author wd
	 * @Date 2016年12月13日  下午1:10:51 
	 * @param request
	 * @return
	 * @return User    返回类型
	 */
	public static User getUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user!=null){
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println("userService:"+userService);
//			System.out.println("user.getId():"+user.getId());
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			user = userService.selectUserById(user.getId());
		}
		return user;
	}

	/**
	 *
	 * @Title: setUser
	 * @Description: 设置当前用户信息（目前仅供校园任务使用）
	 * @author wd
	 * @Date 2016年12月21日  上午9:42:25 
	 * @param request
	 * @return
	 * @return void    返回类型
	 */
	public static void setUser(HttpServletRequest request,User user){
		HttpSession session = request.getSession();
		if(user!=null){
			if(!StringUtils.isEmpty(user.getId())){
				user = userService.selectUserById(user.getId());
			}
		}
		session.setAttribute("user", user);
	}

	/**
	 *
	 * @Title: setUser
	 * @Description: 设置当前用户信息（目前仅供微门户使用）
	 * @author lp
	 * @Date 2017年3月23日18:21:59
	 * @param request
	 * @return
	 * @return void    返回类型
	 */
	public static void setWmhUser(HttpServletRequest request,WmhUser wmhUser){
		HttpSession session = request.getSession();
		if(wmhUser!=null){
			if(!StringUtils.isEmpty(wmhUser.getId())){
				wmhUser =wmhUserService.selectUserById(wmhUser.getId());
			}
		}
		session.setAttribute("wmhUser", wmhUser);
	}

	public static WmhUser getWmhUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		WmhUser wmhUser = (WmhUser) session.getAttribute("wmhUser");
		if(wmhUser!=null){
			wmhUser = wmhUserService.selectUserById(wmhUser.getId());
		}
		return wmhUser;
	}
	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI() + "?" + request.getQueryString();
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length());// 去掉项目路径
		return requestPath;
	}
	
	/**
	 * 获得请求路径和所有参数
	 * @param request
	 * @return
	 * @author 徐优优
	 * @date 2016年8月12日
	 */
	public static String getRequestPathAndParam(HttpServletRequest request) {
		String requestPath = request.getRequestURI() + "?" + request.getQueryString();
		return requestPath;
	}
	
	/**
	 * 获得请求路径和所有参数
	 * @param request
	 * @return
	 * @author 徐优优
	 * @date 2016年8月12日
	 */
	public static String getRequestPathAndServerName(HttpServletRequest request) {
		String requestPath = request.getRequestURL().toString();
		return requestPath;
	}
	
	/**
	 * 根据beanName获取Spring自动生成的bean
	 * @param request
	 * @param beanName
	 * @return
	 * @author 徐优优
	 * @date 2016年5月9日
	 */
	public static Object getBean(HttpServletRequest request, String beanName){
		Object obj = null;
		ServletContext sc = request.getSession().getServletContext();
		XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
		if(null != cxt && null != cxt.getBean(beanName)) {
			obj = cxt.getBean(beanName);
		}
		return obj;
	}


	/**
	 * ip转换数字
	 * @param ipAddress
	 * @return
     */
	public static long ipToLong(String ipAddress) {

		long result = 0;

		String[] ipAddressInArray = ipAddress.split("\\.");

		for (int i = 3; i >= 0; i--) {

			long ip = Long.parseLong(ipAddressInArray[3 - i]);
			result |= ip << (i * 8);

		}
		return result;
	}

	/**
	 * ip转换数字
	 * @param ip
	 * @return
     */
	public String longToIp(long ip) {
		StringBuilder result = new StringBuilder(15);
		for (int i = 0; i < 4; i++) {
			result.insert(0,Long.toString(ip & 0xff));
			if (i < 3) {
				result.insert(0,'.');
			}
			ip = ip >> 8;
		}
		return result.toString();
	}
	
	/**
	 * 
	 * @Title: checkAndSetRealNameData 
	 * @Description: 校验并组装实名认证填写的数据
	 * @author wd
	 * @Date 2016年12月6日  下午4:06:57 
	 * @param realNameModel
	 * @return
	 * @return Map<String,Object>    返回类型
	 */
	public static String checkAndSetRealNameData(RealNameModel realNameModel){
		if(realNameModel == null){
			return "请先完善个人资料！";
		}
		
		String schoolName = realNameModel.getSchoolName();
		if(StringUtils.isEmpty(schoolName) || "".equals(schoolName)){
			return "学校名称不能为空！";
		}else{
			if(schoolName.length() > 100){
				return "学校名称长度不能超过100！";
			}
		}
		
		String dstartTime = realNameModel.getStartTime();
		if(dstartTime == null){
			return "入学年月不能为空！";
		}
		
		String studentId = realNameModel.getStudentId();
		if(StringUtils.isEmpty(studentId) || "".equals(studentId)){
			return "学号不能为空！";
		}else{
			if(studentId.length() > 100){
				return "学号长度不能超过100！";
			}
		}
		
		String edusysName = realNameModel.getEdusysName();
		if(StringUtils.isEmpty(edusysName) || "".equals(edusysName)){
			return "教务系统账号不能为空！";
		}else{
			if(edusysName.length() > 100){
				return "教务系统账号长度不能超过100！";
			}
		}
		
		String edusysPwd = realNameModel.getEdusysPwd();
		if(StringUtils.isEmpty(edusysPwd) || "".equals(edusysPwd)){
			return "教务系统密码不能为空！";
		}else{
			if(edusysPwd.length() > 100){
				return "教务系统密码长度不能超过100！";
			}
		}
		
		String realName = realNameModel.getRealName();
		if(StringUtils.isEmpty(realName) || "".equals(realName)){
			return "真实姓名不能为空！";
		}else{
			if(realName.length() > 100){
				return "真实姓名长度不能超过100！";
			}
		}
		
		String sex = realNameModel.getSex();
		if(StringUtils.isEmpty(sex) || "".equals(sex)){
			return "请选择性别！";
		}
		
		String birthPlace = realNameModel.getBirthPlace();
		if(StringUtils.isEmpty(birthPlace) || "".equals(birthPlace)){
			return "出生地不能为空！";
		}else{
			if(birthPlace.length() > 200){
				return "出生地长度不能超过200！";
			}
		}
		
		//专业
		String majorName = realNameModel.getMajorName();
		if(StringUtils.isEmpty(majorName) || "".equals(majorName)){
			return "专业不能为空！";
		}else{
			if(majorName.length() > 100){
				return "出生地长度不能超过100！";
			}
		}
		
		//宿舍号
		String dormitoryNo = realNameModel.getDormitoryNo();
		if(StringUtils.isEmpty(dormitoryNo) || "".equals(dormitoryNo)){
			return "宿舍号不能为空！";
		}else{
			if(dormitoryNo.length() > 100){
				return "宿舍号长度不能超过100！";
			}
		}
		
		//身份证号
		String cardId = realNameModel.getCardId();
		if(StringUtils.isEmpty(cardId) || "".equals(cardId)){
			return "身份证号不能为空！";
		}else{
			boolean flag = StringUtil.checkCardId(cardId);
			if(!flag){
				return "身份证号格式不正确！";
			}
		}
		return "";
	} 
	
    
    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param realNameModel 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    @SuppressWarnings("null")
	public static JSONObject sendPost(String url, RealNameModel realNameModel) {
    	JSONObject jsonObject = new JSONObject();
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性(可以不用设置)
//            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
//            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
//            conn.setRequestProperty("Cache-Control", "max-age=0");
//            conn.setRequestProperty("Connection", "keep-alive");
//            conn.setRequestProperty("Content-Length", "137");
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            conn.setRequestProperty("Host", "jw.zzu.edu.cn");
//            conn.setRequestProperty("Origin", "http://jw.zzu.edu.cn");
//            conn.setRequestProperty("Referer", "http://jw.zzu.edu.cn/");
//            conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
//            conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            OutputStream outputStream = conn.getOutputStream();
            
            //组装登陆的参数
            String nianji = realNameModel.getStartTime().substring(0, 4);
            String param = "xuehao="+realNameModel.getEdusysName()+"&nianji="+nianji+"&mima="+realNameModel.getEdusysPwd();
            // 注意编码格式，防止中文乱码
            outputStream.write(param.getBytes("UTF-8"));
            outputStream.close();
            
            // 定义BufferedReader输入流来读取URL的响应
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "gb2312");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            
            
            String str = null;
            
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            String result = buffer.toString();
            
            //如果返回的页面没有包含“郑州大学学生信息”，则说明，请求失败
            if(result.indexOf("郑州大学学生信息") < 0){
            	jsonObject.put("code", "0");
            	jsonObject.put("msg", "未通过认证！");
            	return jsonObject;
            }
            
            //解析教务系统返回的HTML
//            Document doc = Jsoup.parse(result);
//            Element content = doc.getElementById("AutoNumber1");
//            Elements tds = content.getElementsByTag("td");
//            List<String> infos = new ArrayList<String>();
//            for (int i = 0; i < tds.size(); i++) {
//            	Element td = tds.get(i);
//            	if(td.childNodeSize() == 1){
//            		if(td.hasText()){
//                		infos.add(td.text());
//                	}
//            	}
//			}
            
            //提取教务系统里的学生信息
            RealNameModel rnm = new RealNameModel();
//            for (int i = 0; i < infos.size(); i++) {
//            	String info = infos.get(i);
//            	if(!(StringUtils.isEmpty(info) || "".equals(info))){
//            		String[] keyValue = info.split("：");
//            		String key = keyValue[0];
//            		String value = "";
//            		if(keyValue.length == 2){
//            			value = keyValue[1];
//            		}
//            		if("姓    名".equals(key)){
//            			rnm.setRealName(value);
//            			continue;
//            		}else if("性    别".equals(key)){
//            			rnm.setSex(value);
//            			continue;
//            		}else if("".equals(key)){
//            			// TODO:出生地到底匹配籍贯，还是来源地区
//            			rnm.setBirthPlace(value);
//            			continue;
//            		}else if("".equals(key)){
//            			// TODO:入学年月
//            			rnm.setStartTime(value);
//            			continue;
//            		}else if("学       号".equals(key)){
//            			rnm.setStudentId(value);
//            			continue;
//            		}else if("".equals(key)){
//            			// TODO:专业
//            			rnm.setMajorName(value);
//            			continue;
//            		}else if("".equals(key)){
//            			// TODO:宿舍号
//            			rnm.setDormitoryNo(value);
//            			continue;
//            		}else if("身份证号".equals(key)){
//            			rnm.setCardId(value);
//            			continue;
//            		}
//            	}
//			}
            
            //校验信息是否真实
            if(realNameModel.getRealName().equals(rnm.getRealName()) && realNameModel.getCardId().equals(rnm.getCardId())){
            	jsonObject.put("code", "1");
            	jsonObject.put("msg", "认证通过！");
            }else{
            	jsonObject.put("code", "0");
            	jsonObject.put("msg", "您填写的信息不真实，未通过认证！");
            }
            

            
            
            
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e); 
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return jsonObject;
    }
	// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
	// PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
	//     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
	public static String myEmailAccount = "15343826020@163.com";
	public static String myEmailPassword = "xlw12345";

	// 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
	// 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
	public static String myEmailSMTPHost = "smtp.163.com";



	//发送邮件的方法

	/**
	 *
	 * @param receiveMailAccount  收件人邮箱
	 * @param userName   收件人名称
	 * @param mailTitle  邮件主题
	 * @param mailContent  邮件内容
	 */
	public static void sendMailMethod(String receiveMailAccount,String userName,String mailTitle,String  mailContent) {
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		Properties props = new Properties();                    // 参数配置
		props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
		props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
		try {
			// 3. 创建一封邮件
			MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount, userName,mailTitle,mailContent);
			// 4. 根据 Session 获取邮件传输对象
			Transport transport = session.getTransport();
			transport.connect(myEmailAccount, myEmailPassword);
			// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
			transport.sendMessage(message, message.getAllRecipients());
			// 7. 关闭连接
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMailAccount,String userName,String mailTitle,String  mailContent) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人
		message.setFrom(new InternetAddress(sendMail, "微门户", "UTF-8"));

		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount,userName, "UTF-8"));

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
