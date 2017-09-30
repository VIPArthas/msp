/*
 * 功  能：简单说明该类的功能
 * 
 * 文件名：UserStudentService.java
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

package com.wh.service.xlwapp;

import javax.servlet.http.HttpServletRequest;

import com.wh.entity.UserStudent;
import com.wh.model.RealNameModel;


/**
 *  学生信息
 *
 * <p>
 * <a href="UserStudentService.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:">wd</a>
 *
 * @version Revision: 1.0  Date: 2016年12月7日 上午11:31:11 
 *
 */

public interface UserStudentService {
	
	/**
	 * 实名认证状态(0未认证)
	 */
	static final int REALNAME_STATUS_NORZ = 0;
	
	/**
	 * 实名认证状态(1基础认证 )
	 */
	static final int REALNAME_STATUS_JCRZ = 1;
	
	/**
	 * 实名认证状态(2完整认证)
	 */
	static final int REALNAME_STATUS_WZRZ = 2;
	
	/**
	 * 实名认证状态(3完整认证申请中)
	 */
	static final int REALNAME_STATUS_RZING = 3;
	/**
	 * 实名认证状态( 4完整认证失败)
	 */
	static final int REALNAME_STATUS_RZSB = 4;
	
	
	/**
	 * 郑州大学学生信息地址
	 */
	static final String ZZDX_XSXX_URL = "http://jw.zzu.edu.cn/scripts/stuinfo.dll/check";
	
	static final String ZZDX_DES = "郑州大学";
	
	/**
	 * 郑州大学本学期课表信息地址
	 */
	static final String ZZDX_BXQKB_URL = "http://jw.zzu.edu.cn/pks/pkisapi2.dll/kbofstu";
	
	/**
	 * 
	 * @Title: checkRealNameData 
	 * @Description: 校验实名认证填写的数据
	 * @author wd
	 * @Date 2016年12月6日  下午4:06:57 
	 * @param realNameModel
	 * @return
	 * @return Map<String,Object>    返回类型
	 */
	String checkRealNameData(RealNameModel realNameModel);
	
	/**
	 * 
	 * @Title: doZzdxStudentInfo 
	 * @Description: 处理郑州大学学生信息
	 * @author wd
	 * @Date 2016年12月28日  上午11:40:02 
	 * @param request
	 * @param realNameModel
	 * @return
	 * @return String    返回类型
	 */
	String doZzdxStudentInfo(HttpServletRequest request,RealNameModel realNameModel);
	
	/**
	 * 
	 * @Title: saveStudentData 
	 * @Description: 保存实名认证填写的信息
	 * @author wd
	 * @Date 2016年12月28日  上午11:39:52 
	 * @param request
	 * @param realNameModel
	 * @return
	 * @return String    返回类型
	 */
	String saveStudentData(HttpServletRequest request,RealNameModel realNameModel);
	
	/**
	 * 根据userid查询学生信息
	 * @author 王鹏翔
	 * @Date 2016年12月9日  下午5:07:38
	 * @param userId
	 * @return
	 */
	UserStudent selectByUserId(String userId) throws Exception;
    //添加一条学校信息
	void saveUserStudent(UserStudent userStudent) throws  Exception;
}
