package com.wh.service.xlwapp.impl;

import com.wh.constants.Constants;
import com.wh.dao.xlwapp.*;
import com.wh.dto.UserRouteDto;
import com.wh.dto.system.UserSearchDto;
import com.wh.entity.*;
import com.wh.service.rgpp.WchatuserPlatService;
import com.wh.service.xlwapp.UserService;

import com.wh.util.BaseModel;
import com.wh.util.PaginationInterceptor;
import com.wh.util.UUIDUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by Administrator on 2016-3-16.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRegisterMapper userRegisterMapper;

	@Autowired
	private UserRealMapper userRealMapper;

	@Autowired
	private UserStudentMapper userStudentMapper;

	@Autowired
	private UserDelMapper userDelMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private WchatuserPlatService wchatuserPlatService;
	

	/**
	 * 插入user
	 */
	public void saveUser(User user, UserRegister userRegister, TbWchatuser wchatUser) {
		UserReal userReal = new UserReal();
		UserStudent userStudent = new UserStudent();
		if (null != user && StringUtils.isEmpty(user.getId())) {
			String userId = UUIDUtil.getUUID();
			user.setId(userId);
			user.setCreateTime(new Date());

			userRegister.setId(UUIDUtil.getUUID());
			userRegister.setRegisterTime(new Date());
			userRegister.setUserId(userId);

			userReal.setId(UUIDUtil.getUUID());
			userReal.setUserId(userId);
			userReal.setRealName(user.getRealName());
			userReal.setCreateTime(new Date());

			userStudent.setId(UUIDUtil.getUUID());
			userStudent.setUserId(userId);
			userStudent.setCreateTime(new Date());
			userStudent.setSchoolName(user.getSchoolName());
			
			if(null != wchatUser){
				wchatUser.setUserId(userId);
				wchatuserPlatService.update(wchatUser);
			}

			this.userRegisterMapper.insertSelective(userRegister);
			this.userMapper.insertSelective(user);
			this.userRealMapper.insertSelective(userReal);
			this.userStudentMapper.insertSelective(userStudent);
		}
	}

	/**
	 * 更新user
	 */
	public void updateUser(User user) {
		if (null != user && !StringUtils.isEmpty(user.getId())) {
			this.userMapper.updateByPrimaryKeySelective(user);
		}

	}
	/**
	 * 更新UserReal中信息
	 */
	public void updateUserReal(UserReal ur) throws Exception{
		if (null != ur) {
			this.userRealMapper.updateByUserId(ur);
		}

	}
	/**
	 * 更新UserStudent中信息
	 */
	public void updateUserStudent(UserStudent us) throws Exception{
		if (null != us) {
			this.userStudentMapper.updateByUserId(us);
		}

	}
	/**
	 * 根据手机号 查询user表记录
	 * 
	 * @param user
	 * @return
	 */
	public List<User> selectUserByPhone(User user) {
		if (null != user && !StringUtils.isEmpty(user.getPhone())) {
			return this.userMapper.selectBySelective(user);
		}
		return new ArrayList<>();
	}

	/**
	 * 用户中心更新信息 包括user，user_real, user_student
	 * 
	 * @param user
	 * @param userReal
	 * @param userStudent
	 */
	public void updateUserRealStudent(User user, UserReal userReal, UserStudent userStudent) {
		this.userRealMapper.updateByUserId(userReal);
		this.userStudentMapper.updateByUserId(userStudent);
		this.userMapper.updateByPrimaryKeySelective(user);

	}
	/**
	 *  基础实名认证 用户中心更新信息 包括user，user_real
	 * @param user
	 * @param userReal
	 */
	public void updateUserReal(User user, UserReal userReal) {
		this.userRealMapper.updateByUserId(userReal);
		this.userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * 根据phone password 查询数据
	 * 
	 * @param user
	 * @return
	 */
	public List<User> selectPhonePassword(User user) {
		if (null != user) {
			List<User> result = this.userMapper.selectPhonePassword(user);
			return result;
		}
		return new ArrayList<>();
	}

	/**
	 * 根据userId查询user,user_real, user_student三张表中的数据
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectUserRealStudent(String userId) {
		if (null != userId && !StringUtils.isEmpty(userId)) {
			return this.userMapper.selectUserRealStudent(userId);
		}
		return new HashMap<>();
	}

	/**
	 * 根据手机号修改user表信息
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void updateByPhoneSelective(User user) {
		if (null != user || !StringUtils.isEmpty(user.getPhone())) {
			this.userMapper.updateByPhoneSelective(user);
		}
	}

	/**
	 * 根据userMap 查询user, user_real, user_student 三张表数据 userMap可为空
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Object> selectUserRealStudentByActiveListPage(UserSearchDto userSearchDto) throws Exception {
		int pageSize = Constants.pageSize;
		int startNum = pageSize * (userSearchDto.getCurrentpage() - 1);
		RowBounds rowBounds = new RowBounds(startNum, pageSize);
		PaginationInterceptor.startPage(userSearchDto.getCurrentpage(), userSearchDto.getRscount());

		List<Map<String, Object>> listMaps = this.userMapper.selectUserRealStudentByActiveListPage(userSearchDto,
				rowBounds);

		BaseModel baseModel = PaginationInterceptor.endPage();
		List<Object> poList = new ArrayList<Object>();
		poList.add(listMaps);
		poList.add(baseModel.getCurrentpage());
		poList.add(baseModel.getRscount());
		return poList;
	}

	/**
	 * 根据userId删除 user, user_real, user_student 三张表中的数据
	 * 
	 * @param
	 */
	public void deleteUserRealStudent(UserDel userDel) {
		if (null != userDel && !StringUtils.isEmpty(userDel.getId())) {
			String id = userDel.getId();
			this.userDelMapper.insertSelective(userDel);

			this.userMapper.deleteByPrimaryKey(id);
			this.userRealMapper.deleteByUserId(id);
			this.userStudentMapper.deleteByUserId(id);
		}
	}

	@Override
	public User selectUserById(String userId) {
		User user = null;
		if(!StringUtils.isEmpty(userId)){
			user = userMapper.selectByPrimaryKey(userId);
		}
		return user;
	}
	
	/**
	 * 用户信息分页查询
	 */
	@Override
	public List<Object> selectUserListPage(UserInfo userInfo) throws Exception {
		
		int pageSize = Constants.pageSize;
		int startNum = pageSize * (userInfo.getCurrentpage() - 1);
		RowBounds rowBounds = new RowBounds(startNum, pageSize);
		PaginationInterceptor.startPage(userInfo.getCurrentpage(), userInfo.getRscount());
		List<Map<Object, Object>> listMaps = userInfoMapper.selectUserListPage(userInfo,rowBounds);
		BaseModel baseModel = PaginationInterceptor.endPage();
		List<Object> userList = new ArrayList<Object>();
		userList.add(listMaps);
		userList.add(baseModel.getCurrentpage());
		userList.add(baseModel.getRscount());
		return userList;
	}

	@Override
	public String selectPhoneById(String userId) {
		String phone = null;
		if(!StringUtils.isEmpty(userId)){
			phone = userMapper.selectPhoneById(userId);
		}
		return phone;
	}

	@Override
	public String selectPwdById(String userId) {
		String pwd = null;
		if(!StringUtils.isEmpty(userId)){
			pwd = userMapper.selectpwdById(userId);
		}
		return pwd;
		
	}

	@Override
	public void insertUserByJzOwner(User user) {
		this.userMapper.insertSelective(user);
	}

	/**
	 *
	 * @param schoolName
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean selectSchool(String schoolName) throws Exception {
		boolean isExist=false;
		UserStudent userStudent=new UserStudent();
		userStudent=userStudentMapper.selectBySchoolName(schoolName);
		if(userStudent!=null){
			isExist=true;
		}else{
			isExist=false;
		}
		return isExist;
	}

	/**
	 *  根据输入的名字进行检索查询
	 * @param likeSchoolName
	 * @return
	 * @throws Exception
	 */
	@Override
	public  Map<String,Object>selectSchoolByLikeName(String likeSchoolName) throws Exception {
		Map<String,Object> schoolNames=userStudentMapper.selectByLikeName(likeSchoolName);
		return schoolNames;
	}

	@Override
	public int insertSelective(User record) throws Exception {
		return this.userMapper.insertSelective(record);
	}

	@Override
	public Map<String, Object> selectUserReal(String userId) throws Exception {
		return this.userMapper.selectUserReal(userId);
	}

	@Override
	public UserReal selectByUserId(String userId) throws Exception {
		return this.userRealMapper.selectByUserId(userId);
	}

	@Override
	public int insertSelective(UserReal record) {
		return this.userRealMapper.insertSelective(record);
	}

	@Override
	public List<User> selectUserByTagId(String tagId) throws Exception {
		List<User> list=userMapper.selectUserList(tagId);
		return list;
	}
}
