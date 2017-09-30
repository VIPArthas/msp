/*
 * 功  能：学生信息
 * 
 * 文件名：UserStudentServiceImpl.java
 * 
 * 描  述：
 * 
 * [变更履历]
 * Version   变更日         		部署              作者           变更内容
 * -----------------------------------------------------------------------------
 * V1.00     2016年12月7日   		jh   	 wd     create
 * -----------------------------------------------------------------------------
 *
 *
 * Copyright (c) 2016  	jh All Rights Reserved.
 *┌─────────────────────────────────────────────────—────┐
 *│ 版权声明                               	jh      	│
 *└──────────────────────────────—————————————————————───┘
 */

package com.wh.service.xlwapp.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wh.dao.xlwapp.UserMapper;
import com.wh.dao.xlwapp.UserRealMapper;
import com.wh.dao.xlwapp.UserStudentMapper;
import com.wh.entity.User;
import com.wh.entity.UserReal;
import com.wh.entity.UserStudent;
import com.wh.entity.WhAttachements;
import com.wh.model.RealNameModel;
import com.wh.service.jzxx.WhAttachementsService;
import com.wh.service.xlwapp.UserStudentService;
import com.wh.util.StringUtil;
import com.wh.util.UUIDUtil;
import com.wh.util.WebUtil;


/**
 *  学生信息
 *
 * <p>
 * <a href="UserStudentServiceImpl.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:">wd</a>
 *
 * @version Revision: 1.0  Date: 2016年12月7日 上午11:35:07 
 *
 */
@Service
public class UserStudentServiceImpl implements UserStudentService {
	
	@Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRealMapper userRealMapper;
    
    @Autowired
    private UserStudentMapper userStudentMapper;
    
    @Autowired
	private WhAttachementsService whAttachementsService;
	
	/**
	 * 
	 * @Title: checkRealNameData 
	 * @Description: 校验并组装实名认证填写的数据
	 * @author wd
	 * @Date 2016年12月6日  下午4:06:57 
	 * @param realNameModel
	 * @return
	 * @return Map<String,Object>    返回类型
	 */
	@Override
	public String checkRealNameData(RealNameModel realNameModel){
		if(realNameModel == null){
			return "请先完善个人资料！";
		}
		
		String realName = realNameModel.getRealName();
		if(StringUtils.isEmpty(realName) || "".equals(realName)){
			return "真实姓名不能为空！";
		}else{
			if(realName.length() > 100){
				return "真实姓名长度不能超过100！";
			}
		}
		
		Integer schoolCode = realNameModel.getSchoolCode();
		String schoolName = realNameModel.getSchoolName();
		if(StringUtils.isEmpty(schoolCode) || "".equals(schoolCode)){
//			if(StringUtils.isEmpty(schoolName) || "".equals(schoolName)){
				return "请完善学校信息！";
//			}
		}else{
			if((schoolCode == 0 || schoolCode == -1) 
					&& (StringUtils.isEmpty(schoolName) || "".equals(schoolName))){
				return "请完善学校信息！";
			}
		}
		
		int belongGrade = realNameModel.getBelongGrade();
		if(belongGrade == 0){
			return "请选择年级！";
		}
		
		String edusysName = realNameModel.getEdusysName();
		if(StringUtils.isEmpty(edusysName) || "".equals(edusysName)){
			return "教务系统账号不能为空！";
		}else{
			if(edusysName.length() > 20){
				return "教务系统账号长度不能超过20！";
			}
		}
		
		String edusysPwd = realNameModel.getEdusysPwd();
		if(StringUtils.isEmpty(edusysPwd) || "".equals(edusysPwd)){
			return "教务系统密码不能为空！";
		}else{
			if(edusysPwd.length() > 20){
				return "教务系统密码长度不能超过20！";
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
     * 处理郑州大学学生信息
     *
     * @param url   发送请求的 URL
     * @param realNameModel 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    @Override
	public String doZzdxStudentInfo(HttpServletRequest request,RealNameModel realNameModel) {
    	
    	//1.模拟登陆，发送请求
    	//组装登陆的参数
        String param = "xuehao="+realNameModel.getEdusysName()+"&nianji="+realNameModel.getBelongGrade()+"&mima="+realNameModel.getEdusysPwd();
    	
        //查询学生信息
        String studentInfoResult = doPost(UserStudentService.ZZDX_XSXX_URL,param);
            
        //2.如果返回的页面没有包含“郑州大学学生信息”，则说明，请求失败
        if(studentInfoResult.indexOf("郑州大学学生信息") < 0){
        	updateRealnameStatus(request,realNameModel.getUserId(),UserStudentService.REALNAME_STATUS_RZSB);
        	saveStudentDataFail(request,realNameModel);
        	return "未通过认证！";
        }
        
        //3.解析教务系统返回的HTML，并提取教务系统里的学生信息
        //查询本学期课表信息
        String bxqkbInfoResult = doPost(UserStudentService.ZZDX_BXQKB_URL,param);
        
        Map<String,Object> data = setZzdxReturnRealData(studentInfoResult,bxqkbInfoResult);
        
        //4.校验信息是否真实
        boolean flag = checkDataIsReal(data,realNameModel);
        if(!flag){
        	updateRealnameStatus(request,realNameModel.getUserId(),UserStudentService.REALNAME_STATUS_RZSB);
        	saveStudentDataFail(request,realNameModel);
        	return "您填写的信息不真实，未通过认证！";
        }
        
        //5.保存真实的数据到数据库
        updateStudentData(request,data,realNameModel);
        
        
        //6.更新完整实名认证状态
        return updateRealnameStatus(request,realNameModel.getUserId(),UserStudentService.REALNAME_STATUS_WZRZ);
    }
    
    /**
     * 
     * @Title: doPost 
     * @Description: 模拟登陆，发送请求
     * @author wd
     * @Date 2016年12月7日  下午2:31:12 
     * @param url
     * @param param
     * @return
     * @return String    返回类型
     */
    private String doPost(String url,String param){
    	String result = "";
    	OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            OutputStream outputStream = conn.getOutputStream();
            
            
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
            result = buffer.toString();
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
        return result;
    } 
    
    /**
     * 
     * @Title: setZzdxReturnRealData 
     * @Description: 解析教务系统返回的HTML，并提取教务系统里的学生信息和本学期课表信息
     * @author wd
     * @Date 2016年12月7日  下午2:29:59 
     * @param studentInfoResult
     * @param bxqkbInfoResult
     * @return
     * @return RealNameModel    返回类型
     */
    private Map<String,Object> setZzdxReturnRealData(String studentInfoResult,String bxqkbInfoResult){
    	Document doc = Jsoup.parse(studentInfoResult);
        Element content = doc.getElementById("AutoNumber1");
        Elements tds = content.getElementsByTag("td");
        List<String> infos = new ArrayList<String>();
        for (int i = 0; i < tds.size(); i++) {
        	Element td = tds.get(i);
        	if(td.childNodeSize() == 1){
        		if(td.hasText()){
            		infos.add(td.text());
            	}
        	}
		}
        
        //提取教务系统里的学生信息
        User user = new User();
        UserReal userReal = new UserReal();
        UserStudent userStudent = new UserStudent();
        
        for (int i = 0; i < infos.size(); i++) {
        	String info = infos.get(i);
        	if(!(StringUtils.isEmpty(info) || "".equals(info))){
        		String[] keyValue = info.split("：");
        		String key = keyValue[0];
        		String value = "";
        		if(keyValue.length == 2){
        			value = keyValue[1];
        		}
        		if("姓    名".equals(key)){
        			userReal.setRealName(value);
        			continue;
        		}else if("身份证号".equals(key)){
        			userReal.setCardId(value);
        			continue;
        		}else if("性    别".equals(key)){
        			if("男".equals(value)){
        				user.setSex(0);
        			}else if("女".equals(value)){
        				user.setSex(1);
        			}
        			continue;
        		}else if("籍    贯".equals(key)){
        			userStudent.setNativePlace(value);
        			continue;
        		}else if("来源地区".equals(key)){
        			userStudent.setSourceRegion(value);
        			continue;
        		}else if("所属班号".equals(key)){
        			userStudent.setClassNumber(value);
        			continue;
        		}else if("学    号".equals(key)){
        			userStudent.setStudentId(value);
        			continue;
        		}
        	}
		}
        
        
        doc = Jsoup.parse(bxqkbInfoResult);
        content = doc.getElementById("table2");
        Element font = content.child(0).child(0).child(0).child(0).child(0);
        String text = font.text();
        String[] info = text.split(" .");
        userStudent.setMajorName(info[0]+info[1]);
        
        
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("user", user);
        data.put("userReal", userReal);
        userStudent.setStudentHtml(studentInfoResult);
        userStudent.setStudyHtml(bxqkbInfoResult);
        data.put("userStudent", userStudent);
        return data;
    }
    


    /**
     * 
     * @Title: checkDataIsReal 
     * @Description: 校验填写的信息是否属实
     * @author wd
     * @Date 2016年12月7日  下午2:58:42 
     * @param data
     * @return
     * @return boolean    返回类型
     */
    private boolean checkDataIsReal(Map<String,Object> data,RealNameModel realNameModel){
    	UserReal userReal = (UserReal) data.get("userReal");
        if(userReal.getRealName().equals(realNameModel.getRealName()) && userReal.getCardId().equals(realNameModel.getCardId())){
        	return true;
        }
        return false;
    }
    
    /**
     * 
     * @Title: updateStudentData 
     * @Description: 保存真实的数据到数据库
     * @author wd
     * @Date 2016年12月7日  下午3:07:15 
     * @param data
     * @param userId
     * @return void    返回类型
     */
    private void updateStudentData(HttpServletRequest request,Map<String,Object> data,RealNameModel realNameModel){
    	String userId = realNameModel.getUserId();
    	User user = (User) data.get("user");
    	user.setId(userId);
    	userMapper.updateByPrimaryKeySelective(user);
    	WebUtil.setUser(request, user);
    	
    	UserReal ur = userRealMapper.selectByUserId(userId);//姓名、身份证号、
    	String id = UUIDUtil.getUUID();
    	UserReal userReal = (UserReal) data.get("userReal");
    	userReal.setUserId(userId);
		if(ur != null){
	    	userRealMapper.updateByUserId(userReal);
		}else{
			userReal.setId(id);
			userRealMapper.insertSelective(userReal);
		}
		
		UserStudent us = userStudentMapper.selectByUserId(userId);
		UserStudent userStudent = (UserStudent) data.get("userStudent");
        userStudent.setUserId(userId);
        userStudent.setEdusysName(realNameModel.getEdusysName());
        userStudent.setEdusysPwd(realNameModel.getEdusysPwd());
        userStudent.setSchoolCode(realNameModel.getSchoolCode());
        if(realNameModel.getSchoolCode() == 1){
        	realNameModel.setSchoolName(UserStudentService.ZZDX_DES);
        }
        if(us != null){
        	userStudentMapper.updateByUserId(userStudent);
		}else{
			userStudent.setId(id);
			userStudentMapper.insertSelective(userStudent);
		}
//        saveSfzPhoto(realNameModel.getFiles(),userId);
    }
    
    /**
     * 
     * @Title: saveSfzPhoto 
     * @Description: 保存身份证照片
     * @author wd
     * @Date 2016年12月8日  下午3:04:02 
     * @param fileList
     * @param userId
     * @return String    返回类型
     */
//    private void saveSfzPhoto(List<String> fileList,String userId){
//    	//保存身份证照片到数据库
//        if (fileList != null && fileList.size() > 0) {
//			for (String aFileList : fileList) {
//				String uploadUrl = aFileList;
//				String newName = uploadUrl.substring(uploadUrl.lastIndexOf(".") + 1).toLowerCase();
//				WhAttachements whAttachements = new WhAttachements();
//				whAttachements.setId(UUIDUtil.getUUID());
//				whAttachements.setLinkId("sfz_"+userId);//身份证照片：sfz_userId
//				whAttachements.setFileName(newName);
//				whAttachements.setFilePath(uploadUrl);
//				whAttachements.setCreateTime(new Date());
//				this.whAttachementsService.save(whAttachements);
//			}
//		}
//    }
    
    @Override
    public String saveStudentData(HttpServletRequest request,RealNameModel realNameModel){
    	String userId = realNameModel.getUserId();
    	User user = new User();
    	user.setId(userId);
    	user.setRealnameStatus(UserStudentService.REALNAME_STATUS_RZING);//认证申请中
    	userMapper.updateByPrimaryKeySelective(user);
    	WebUtil.setUser(request, user);
    	
    	
    	UserReal ur = userRealMapper.selectByUserId(userId);
    	UserReal userReal = new UserReal();
    	String id = UUIDUtil.getUUID();
    	userReal.setUserId(userId);
    	userReal.setRealName(realNameModel.getRealName());
    	userReal.setCardId(realNameModel.getCardId());
    	if(ur != null){
	    	userRealMapper.updateByUserId(userReal);
		}else{
			userReal.setId(id);
			userRealMapper.insertSelective(userReal);
		}
    	
    	UserStudent us = userStudentMapper.selectByUserId(userId);
        UserStudent userStudent = new UserStudent();
        userStudent.setUserId(userId);
        userStudent.setSchoolCode(realNameModel.getSchoolCode());
        userStudent.setSchoolName(realNameModel.getSchoolName());
        userStudent.setBelongGrade(realNameModel.getBelongGrade());
        userStudent.setEdusysName(realNameModel.getEdusysName());
        userStudent.setEdusysPwd(realNameModel.getEdusysPwd());
        if(us != null){
        	userStudentMapper.updateByUserId(userStudent);
		}else{
			userReal.setId(id);
			userStudentMapper.insertSelective(userStudent);
		}
        
//        saveSfzPhoto(realNameModel.getFiles(),userId);
        
        return "您的实名认证申请已提交，1个工作日后，我们将会处理完毕~";
    }
    
    public void saveStudentDataFail(HttpServletRequest request,RealNameModel realNameModel){
    	String userId = realNameModel.getUserId();
    	User user = new User();
    	user.setId(userId);
    	user.setRealnameStatus(UserStudentService.REALNAME_STATUS_RZSB);//认证失败
    	userMapper.updateByPrimaryKeySelective(user);
    	WebUtil.setUser(request, user);
    	
    	
    	UserReal ur = userRealMapper.selectByUserId(userId);
    	UserReal userReal = new UserReal();
    	String id = UUIDUtil.getUUID();
    	userReal.setUserId(userId);
    	userReal.setRealName(realNameModel.getRealName());
    	userReal.setCardId(realNameModel.getCardId());
    	if(ur != null){
	    	userRealMapper.updateByUserId(userReal);
		}else{
			userReal.setId(id);
			userRealMapper.insertSelective(userReal);
		}
    	
    	UserStudent us = userStudentMapper.selectByUserId(userId);
        UserStudent userStudent = new UserStudent();
        userStudent.setUserId(userId);
        userStudent.setSchoolCode(realNameModel.getSchoolCode());
        userStudent.setSchoolName(realNameModel.getSchoolName());
        userStudent.setBelongGrade(realNameModel.getBelongGrade());
        userStudent.setEdusysName(realNameModel.getEdusysName());
        userStudent.setEdusysPwd(realNameModel.getEdusysPwd());
        if(us != null){
        	userStudentMapper.updateByUserId(userStudent);
		}else{
			userReal.setId(id);
			userStudentMapper.insertSelective(userStudent);
		}
        
    }
    
    /**
     * 
     * @Title: updateRealnameStatus 
     * @Description: 更新完整实名认证状态
     * @author wd
     * @Date 2016年12月7日  下午3:10:52 
     * @param userId
     * @return String    返回类型
     */
    private String updateRealnameStatus(HttpServletRequest request,String userId,int status){
    	User user = new User();
    	user.setId(userId);
    	user.setRealnameStatus(status);
        
        int i = userMapper.updateByPrimaryKeySelective(user);
        if(i != 1){
        	return "实名认证状态修改失败！";
        }else{
        	WebUtil.setUser(request,user);
        	return "";
        }
    }


	@Override
	public UserStudent selectByUserId(String userId) {
		return this.userStudentMapper.selectByUserId(userId);
	}

	@Override
	public void saveUserStudent(UserStudent userStudent) throws Exception {
		userStudentMapper.insertSelective(userStudent);
	}
}
