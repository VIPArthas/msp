package com.wh.service.xlwapp;

import java.util.List;
import java.util.Map;

import com.wh.dto.system.UserSearchDto;
import com.wh.entity.*;

/**
 * Created by Administrator on 2016-4-11.
 */
public interface UserService {
	/**
	 * 插入user
	 */
	 void saveUser(User user, UserRegister userRegister, TbWchatuser wchatUser) throws Exception;
	/**
	 * 更新user
	 */
	void updateUser(User user) throws Exception;
	/**
	 * 更新UserReal中信息
	 */
	void updateUserReal(UserReal ur) throws Exception;
	/**
	 * 更新UserStudent中信息
	 */
	void updateUserStudent(UserStudent us) throws Exception;
	
	/**
	 * 根据手机号 查询user表记录
	 * @param user
	 * @return
	 */
	 List<User> selectUserByPhone(User user) throws Exception;
	/**
	 * 用户中心更新信息 包括user，user_real, user_student
	 * @param user
	 * @param userReal
	 * @param userStudent
	 */
	void updateUserRealStudent(User user, UserReal userReal, UserStudent userStudent) throws Exception;
	/**
	 *  基础实名认证 用户中心更新信息 包括user，user_real
	 * @param user
	 * @param userReal
	 */
	void updateUserReal(User user, UserReal userReal) throws Exception;

	/**
	 * 根据phone password 查询数据
	 * @param user
	 * @return
	 */
	List<User> selectPhonePassword(User user) throws Exception;
	/**
	 * 根据userId查询user,user_real, user_student三张表中的数据
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> selectUserRealStudent(String userId) throws  Exception;
	/**
	 * 根据手机号修改user表信息
	 * @param user
	 * @throws Exception
	 */
	void updateByPhoneSelective(User user) throws Exception;

	/**
	 * 根据userMap 查询user, user_real, user_student 三张表数据 userMap可为空
	 * @param
	 * @return
	 * @throws Exception
	 */
	List<Object> selectUserRealStudentByActiveListPage(UserSearchDto userSearchDto) throws Exception;

	/**
	 * 根据userId删除 user, user_real, user_student 三张表中的数据
	 * @param
	 */
	void deleteUserRealStudent(UserDel userDel) throws Exception;

	/**
	 * 根据userId查询user对象
	 * @param userId
	 * @return
	 * @author 徐优优
	 * @date 2016年4月27日
	 */
	public User selectUserById(String userId);
	
	/**
	 * 用户信息分页查询
	 */
	List<Object> selectUserListPage(UserInfo userInfo) throws Exception;
	
	/**
	 * 根据userId查询手机号
	 */
	public String selectPhoneById(String userId);
	
	/**
	 * 根据userId查询密码
	 */
	public String selectPwdById(String userId);

	/**
	 * 插入一条user信息，兼职信息后台增加的兼职兼职发布人手机号
	 * @param user
     */
	void insertUserByJzOwner(User user);
	/**
	 * 根据学校名称查找是否有该学校
	 */
	public boolean selectSchool(String schoolName) throws  Exception;
	/**
	 * 检索查询该学校
	 */
	public  Map<String,Object> selectSchoolByLikeName(String likeSchoolName) throws Exception;
	
	/**
	 * 绑定手机号时添加一个注册用户
	 * @author 王鹏翔
	 * @Date 2016年12月9日  下午1:48:27
	 * @param record
	 * @return
	 * @throws Exception
	 */
	int insertSelective(User record) throws Exception;
	
	/**
     * 根据用户id查询相关信息
     * @author 王鹏翔
     * @Date 2016年12月12日  下午2:35:20
     * @param userId
     * @return
     */
    Map<String, Object> selectUserReal(String userId) throws Exception;
    
    /**
     * genjuuserId查询用户真实信息
     * @author 王鹏翔
     * @Date 2017年1月9日  上午11:49:11
     * @param userId
     * @return
     * @throws Exception
     */
    UserReal selectByUserId(String userId) throws Exception;
    
    /**
     * 添加一条userReal数据
     * @author 王鹏翔
     * @Date 2017年1月9日  下午1:10:22
     * @param record
     * @return
     */
    int insertSelective(UserReal record) throws Exception;


	/**
	 * 根据标签id查询相关的人员
	 */
	List<User> selectUserByTagId(String tagId) throws Exception;
}
