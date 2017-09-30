package com.wh.service.xlwapp.impl;

import com.wh.dao.xlwapp.*;
import com.wh.entity.*;
import com.wh.service.xlwapp.UserLoginService;
import com.wh.util.DateUtil;
import com.wh.util.UUIDUtil;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Administrator on 2016-3-16.
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserLoginMapper userLoginMapper;


	/**
	 * 插入UserLogin数据, 如果数据库有数据就更新，没有就插入
	 * @param userLogin
	 */
	public int updateUseLoginByUserId(UserLogin userLogin) {

		if (null != userLogin && !StringUtils.isEmpty(userLogin.getUserId())) {
			List<UserLogin> userLogins = this.userLoginMapper.selectByUserId(userLogin.getUserId());
			if (null != userLogins && userLogins.size() > 0) {
				return this.userLoginMapper.updateByUserId(userLogin);
			} else {
				userLogin.setId(UUIDUtil.getUUID());
				return this.userLoginMapper.insertSelective(userLogin);
			}
		}

		return -1;
	}


	@Override
	public List<Map<String, Object>> queryUserActiveDegree() throws Exception {
		return userLoginMapper.queryUserActiveDegree();
	}

	@Override
	public Map<String, Object> queryUserActive30DLine() throws Exception {
		List<Map<String, Object>> queryRes = userLoginMapper.queryUserActive30DLine();
		//生成当前日前之前的30天的日期放入到list
		Map<String, Object> res = new HashMap<String, Object>();
		//List<String> nameRes = new ArrayList<String>();
		JSONArray nameRes = new JSONArray();
		List<Integer> dataRes = new ArrayList<Integer>();
		Date curr = new Date();
		for(int i=29;i>=0;i--){
			Date temp = DateUtil.getDateBefore(curr, i);
			String tempStr = DateUtil.format(temp, "yyyy年MM月dd日");
			nameRes.add(tempStr);
			boolean flag = false;
			//查找当前日期的userCount
			for(int j=0;j<queryRes.size();j++){
				Map<String, Object> resTemp = queryRes.get(j);
				String dateTemp = (String) resTemp.get("lastUseSourceTime");
				if(dateTemp.equals(tempStr)){
					Integer userCount = new Long((long) resTemp.get("userCount")).intValue();
					dataRes.add(userCount);
					flag = true;
					continue;
				}
			}
			if(!flag){
				dataRes.add(0);
			}
		}
		res.put("nameRes", nameRes);
		res.put("dataRes", dataRes);
		return res;
	}


	@Override
	public Map<String, Object> queryUserActive72HLine() throws Exception {
		List<Map<String, Object>> queryRes = userLoginMapper.queryUserActive72HLine();
		//生成当前时间之前的72个小时的日期字符串并放入到list
		Map<String, Object> res = new HashMap<String, Object>();
		JSONArray nameRes = new JSONArray();
		List<Integer> dataRes = new ArrayList<Integer>();
		Date curr = new Date();
		for(int i=71;i>=0;i--){
			Date temp = DateUtil.getTimestampHour(curr, i);
			String tempStr = new String(DateUtil.format(temp, "yyyy年MM月dd日HH时"));
			nameRes.add(tempStr);
			boolean flag = false;
			for(int j=0;j<queryRes.size();j++){
				Map<String, Object> resTemp = queryRes.get(j);
				String dateTemp = (String) resTemp.get("lastUseSourceTime");
				if(dateTemp.equals(tempStr)){
					Integer userCount = new Long((long) resTemp.get("userCount")).intValue();
					dataRes.add(userCount);
					flag = true;
					continue;
				}
			}
			if(!flag){
				dataRes.add(0);
			}
		}
		res.put("nameRes", nameRes);
		res.put("dataRes", dataRes);
		return res;
	}


	@Override
	public int getHourDiff(String otherUserId, String myUserId) {
		
		return userLoginMapper.getHourDiff(otherUserId,myUserId);
	}
}
