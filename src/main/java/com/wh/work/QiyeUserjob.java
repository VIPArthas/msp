package com.wh.work;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wh.base.AjaxJson;
import com.wh.base.PageBounds;
import com.wh.mspentity.MspDepartment;
import com.wh.mspentity.MspPlatform;
import com.wh.mspentity.MspUser;
import com.wh.service.msp.MspDepartmentService;
import com.wh.service.msp.MspPlatformService;
import com.wh.service.msp.MspUserService;
import com.wh.util.StringUtil;
import com.wh.util.msp.Constants;
import com.wh.util.msp.EnumMethod;
import com.wh.util.msp.HttpRequestUtil;

import net.sf.json.JSONObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 企业号成员定时维护
 * (成员部门变更,只新增)(可启用/禁用,可修改手机号)
 * @author Administrator
 *
 */
@Component
public class QiyeUserjob {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MspPlatformService mspPlatformService;

	@Resource
	private MspDepartmentService mspDepartmentService;

	@Resource
	private MspUserService mspUserService;

	// 每天凌晨2点触发一次 对MSP项目成员进行新增,更新,删除
	//@Scheduled(cron = "0/20 * * * * ? ")
	@Scheduled(cron = "0 0 2 * * ? ")
	public void updateAccessToken() {
		log.info("移动校园平台成员管理定时维护开始...");
		long begin = System.currentTimeMillis();
		try {
			// 新增,更新部门发生变化的成员     简单的成员查询url
			userToQiye();
			
			// 删除   (不删除   禁用)
			//deleteUserFromQiye();
		} catch (Exception e) {
			log.error("{}", e);
		}
		long end = System.currentTimeMillis();
		log.info("移动校园平台成员管理定时任务结束，共耗时：[" + (end - begin) / 1000 + "]秒");

	}

	/**
	 * 定时保持成员一直(本地---->企业号) 新增,更新操作
	 * 
	 * @param req
	 * @param resp
	 * @param muList
	 * @return
	 */

	public void userToQiye() {
		// 获取本地组织架构id
		Map<String, Object> map = new HashMap<String, Object>();
		List<MspDepartment> localDepList = mspDepartmentService.findList(map, new PageBounds());
		for (MspDepartment localDep : localDepList) {
			String id = localDep.getId();
			log.info("MSP项目定时更新成员信息,开始:部门id----->" + id);
			// 获取本地人员
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("departmentId", id);
			//map1.put("userStatus", 1);
			List<MspUser> localUserList = mspUserService.findList(map1, new PageBounds());

			// 获取企业号数据(详细数据,不需要考虑多部门情况)
			List<MspUser> qyUserList = GetDepMemberListFromQiYe(id);

			// 如果企业号有则去除

			for (int i = 0; i < localUserList.size(); i++) {
				MspUser localUser = localUserList.get(i);
				for (int j = 0; j < qyUserList.size(); j++) {
					MspUser qyUser = qyUserList.get(j);
					// 整理部门id
					String department = qyUser.getDepartment().toString();
					department = department.replace("[", "").replace("]", "");
					qyUser.setDepartmentId(department);

					// 如果有相同的,则去除,剩余的是需要新增的
					if (qyUser.getUserid().equals(localUser.getUserId())&&qyUser.getName().equals(localUser.getName())) {
						
						//是否启用    企业号2为禁用         本地0为禁用且更改用户信息  0为禁用      数据为推送过来的,不再考虑解禁
					if ((qyUser.getStatus()!=2 &&localUser.getUserStatus()==0) ||(qyUser.getStatus()==2 &&localUser.getUserStatus()==1)){
						updateUser(localUser);
					}
					
					//手机号发生变化
					if (!qyUser.getMobile().equals(localUser.getMobile())) {
						updateUser(localUser);
					}
					
						localUserList.remove(i);
						qyUserList.remove(j);
						i = i - 1;
						j = j - 1;
						break;
					}
				}
			}
			boolean flag=false;
			//对本地的进行新增,更新
			for (MspUser localUser : localUserList) {
				if (StringUtil.isNotEmpty(localUser.getUserId())&&StringUtil.isNotEmpty(localUser.getMobile())) {
					flag = addUser(localUser);
					if (!flag) {
						flag = updateUser(localUser);
					}
				}
			}
			
			
			log.info("MSP项目定时更新成员信息,结束:部门id----->" + id);
		}


	}
	
	
	public void userToQiye1() {
		// 获取本地组织架构id
		Map<String, Object> map = new HashMap<String, Object>();
		List<MspDepartment> localDepList = mspDepartmentService.findList(map, new PageBounds());
		for (MspDepartment localDep : localDepList) {
			String id = localDep.getId();
			log.info("MSP项目定时更新成员信息,开始:部门id----->" + id);
			// 获取本地人员
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("departmentId", id);
			List<MspUser> localUserList = mspUserService.findList(map1, new PageBounds());

			// 获取企业号数据
			List<MspUser> qyUserList = GetDepMemberListFromQiYe1(id);

			// 如果企业号有则去除

			for (int i = 0; i < localUserList.size(); i++) {
				MspUser localUser = localUserList.get(i);
				for (int j = 0; j < qyUserList.size(); j++) {
					MspUser qyUser = qyUserList.get(j);
					// 整理部门id
					String department = qyUser.getDepartment().toString();
					department = department.replace("[", "").replace("]", "");
					qyUser.setDepartmentId(department);
				
					// 如果有相同的,则去除,剩余的是需要新增的       API简单信息内无电话信息,详情内无具体部门信息,两全不能齐美啊   
					if (qyUser.getUserid().equals(localUser.getUserId())&&qyUser.getName().equals(localUser.getName())
							&& qyUser.getMobile().equals(localUser.getMobile())) {
						localUserList.remove(i);
						qyUserList.remove(j);
						i = i - 1;
						j = j - 1;
						break;
					}
				}
			}
			boolean flag=false;
			//对本地的进行新增,更新
			for (MspUser localUser : localUserList) {
				if (StringUtil.isNotEmpty(localUser.getUserId())) {
				System.out.println(localUser.getName());
				flag = addUser(localUser);
				if (!flag) {
					flag = updateUser(localUser);
				}
			}
			}
			
			log.info("MSP项目定时更新成员信息,结束:部门id----->" + id);
		}
	}
	
	

	/**
	 * 获取某个部门下用户数据 企业号
	 * 
	 * @param req
	 * @param resp
	 * @param map
	 * @return
	 */

	public List<MspUser> GetDepMemberListFromQiYe(String id) {

		JSONObject jsonObject = null;
		List<MspUser> userlist = null;
		MspPlatform mspPlatform = mspPlatformService.load(Constants.ALPLATFORMID);
		String accessToken = mspPlatform.getAccessToken();
		
		//获取部门成员详情(但无完整部门信息)
		String requestUrl = Constants.GET_DEPMEMBER_LIST_URL;
		
		//获取部门成员(简单信息,有部门信息)
		//String requestUrl = Constants.GET_SIMPLE_DEPMEMBER_LIST_URL;
		if (StringUtil.isNotEmpty(accessToken)) {
			requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("DEPARTMENT_ID", id)
					.replace("FETCH_CHILD", "1");

			jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);

			if (jsonObject == null) {
				jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
			}
			// 如果请求成功
			if (null != jsonObject) {
				int errcode = jsonObject.getInt("errcode");
				if (errcode == 0) {

					Gson gson = new GsonBuilder().create();
					userlist = gson.fromJson(jsonObject.get("userlist").toString(), new TypeToken<List<MspUser>>() {
					}.getType());
				} else {
					log.error("企业号获取某个部门下用户数据 errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
				}
			}
		} else {
			log.info("根据accesstoken企业号获取某个部门下用户数据:accessToken获取为空!");
		}
		return userlist;
	}
	
	
	public List<MspUser> GetDepMemberListFromQiYe1(String id) {

		JSONObject jsonObject = null;
		List<MspUser> userlist = null;
		MspPlatform mspPlatform = mspPlatformService.load(Constants.ALPLATFORMID);
		String accessToken = mspPlatform.getAccessToken();
		
		//获取部门成员详情(但无完整部门信息)
		String requestUrl = Constants.GET_DEPMEMBER_LIST_URL;
		
		//获取部门成员(简单信息,有部门信息)
		//String requestUrl = Constants.GET_SIMPLE_DEPMEMBER_LIST_URL;
		if (StringUtil.isNotEmpty(accessToken)) {
			requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("DEPARTMENT_ID", id)
					.replace("FETCH_CHILD", "1");

			jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);

			if (jsonObject == null) {
				jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
			}
			// 如果请求成功
			if (null != jsonObject) {
				int errcode = jsonObject.getInt("errcode");
				if (errcode == 0) {

					Gson gson = new GsonBuilder().create();
					userlist = gson.fromJson(jsonObject.get("userlist").toString(), new TypeToken<List<MspUser>>() {
					}.getType());
				} else {
					log.error("企业号获取某个部门下用户数据 errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
				}
			}
		} else {
			log.info("根据accesstoken企业号获取某个部门下用户数据:accessToken获取为空!");
		}
		return userlist;
	}
	
	
	

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	public void deleteUserFromQiye() {
		// 获取本地组织架构id
		Map<String, Object> map = new HashMap<String, Object>();
		List<MspDepartment> localDepList = mspDepartmentService.findList(map, new PageBounds());
		for (MspDepartment localDep : localDepList) {
			String id = localDep.getId();
			log.info("MSP项目定时删除成员信息,开始:部门id----->" + id);
			// 获取本地人员
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("departmentId", id);
			List<MspUser> localUserList = mspUserService.findList(map1, new PageBounds());
			// 获取企业号数据
			List<MspUser> qyUserList = GetDepMemberListFromQiYe1(id);
			// 如果企业号有则去除
			for (int i = 0; i < localUserList.size(); i++) {
				MspUser localUser = localUserList.get(i);
				for (int j = 0; j < qyUserList.size(); j++) {
					MspUser qyUser = qyUserList.get(j);
					// 整理部门id
					String department = qyUser.getDepartment().toString();
					department = department.replace("[", "").replace("]", "");
					qyUser.setDepartmentId(department);								 
					if (qyUser.getName().equals(localUser.getName())) {
						localUserList.remove(i);
						qyUserList.remove(j);
						i = i - 1;
						j = j - 1;
						break;
					}
				}
			}
			boolean flag=false;
			//对企业号的进行删除
			
			for (MspUser qyUser : qyUserList) {
				System.out.println(qyUser.getName());
				flag = deleteUser(qyUser);
			
			}

			log.info("MSP项目定时删除成员信息,结束:部门id----->" + id);
		}


	}


	/**
	 * 添加用户
	 * 
	 * @param mspUser
	 * @return
	 */
	public boolean addUser(MspUser mspUser) {
		JSONObject jsonObject = null;
		MspPlatform mspPlatform = mspPlatformService.load(Constants.ALPLATFORMID);
		String requestUrl = Constants.CREATE_USER_URL;
		String accessToken = mspPlatform.getAccessToken();
		if (StringUtil.isNotEmpty(accessToken)) {
			requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
			String PostData = "{\"userid\":\"%s\",\"name\":\"%s\",\"mobile\":%s,\"department\":%s}";
			// %s 文本替代

			String departId = "[" + mspUser.getDepartmentId() + "]";
			String outputStr = String.format(PostData, mspUser.getUserId(), mspUser.getName(), mspUser.getMobile(),
					departId);
			jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.POST.name(), outputStr);
			if (jsonObject == null) {
				jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.POST.name(), outputStr);
			}
			// 如果请求成功
			if (null != jsonObject) {

				int errcode = jsonObject.getInt("errcode");
				if (errcode == 0) {
					log.info("添加成员成功!成员姓名---->" + mspUser.getName());
					return true;
				} else {
					log.error("添加成员"+mspUser.getName()+"失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
				}
			}
		} else {
			log.info("添加成员:accessToken获取为空!");
		}

		return false;
	}

	/**
	 * 更新成员
	 * 
	 * @param mspDepartment
	 * @return
	 */
	public boolean updateUser(MspUser mspUser) {

		JSONObject jsonObject = null;
		MspPlatform mspPlatform = mspPlatformService.load(Constants.ALPLATFORMID);
		String requestUrl = Constants.UPDATE_USER_URL;
		String accessToken = mspPlatform.getAccessToken();
		if (StringUtil.isNotEmpty(accessToken)) {
			requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
			//String PostData = "{\"name\":\"%s\",\"parentid\":%s,\"id\":%s}";
			
			String PostData = "{\"userid\":\"%s\",\"name\":\"%s\",\"mobile\":\"%s\",\"department\":%s,\"enable\":%s}";
			// %s 文本替代
			String departId = "[" + mspUser.getDepartmentId() + "]";
			String outputStr = String.format(PostData, mspUser.getUserId(), mspUser.getName(), mspUser.getMobile(),
					departId,mspUser.getUserStatus());
		
			jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.POST.name(), outputStr);
			if (jsonObject == null) {
				jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.POST.name(), outputStr);
			}
			// 如果请求成功
			if (null != jsonObject) {

				int errcode = jsonObject.getInt("errcode");
				if (errcode == 0) {
					log.info("更新成员成功!成员姓名---->" + mspUser.getName());
					return true;
				} else {
					log.error("更新成员"+mspUser.getName()+"失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
				}
			}
		} else {
			log.info("更新成员:accessToken获取为空!");
		}
		return false;
	}

	/**
	 * 删除用户
	 * 
	 * @param mspDepartment
	 * @return
	 */
	public boolean deleteUser(MspUser mspUser) {

		JSONObject jsonObject = null;
		MspPlatform mspPlatform = mspPlatformService.load(Constants.ALPLATFORMID);
		String requestUrl = Constants.DELETE_USER_URL;
		String accessToken = mspPlatform.getAccessToken();
		System.out.println("mspUser.getUserId()"+mspUser.getName()+"--------------------"+mspUser.getUserid());
		if (StringUtil.isNotEmpty(accessToken)) {
			requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("USERID",
					mspUser.getUserid());

			jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
			if (jsonObject == null) {
				jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
			}
			// 如果请求成功
			if (null != jsonObject) {
				int errcode = jsonObject.getInt("errcode");
				if (errcode == 0) {
					log.info("删除成员成功!用户名称---->" + mspUser.getName());
					return true;
				} else {
					log.error("删除成员"+mspUser.getName()+"失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
				}
			}
		} else {
			log.info("删除成员:accessToken获取为空!");
		}
		return false;
	}

}
