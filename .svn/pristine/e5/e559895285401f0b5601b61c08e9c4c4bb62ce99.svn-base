package com.wh.service.wmh;

import com.wh.entity.WmhUser;

import java.util.List;

/**
 * Created by User on 2017/3/23.
 */
public interface WmhUserService {
	
	/**
	 * 1.学校类型:新联学院；
	 */
	static final int SIGN_TYPE_XINLIAN = 1;
	
	/**
	 * 2.学校类型:河职学院；
	 */
	static final int SIGN_TYPE_HEZHI = 2;
	
	
	/**
	 * xinlian:进入新联学院后台管理的密码；
	 */
	static final String PASSWORD_XINLIAN = "xinlian";
	
	/**
	 * hezhi:进入河职后台管理的密码；
	 */
	static final String PASSWORD_HEZHI = "hezhi";
	
    public WmhUser selectUserById(String userId);

    /**
     * 根据标签id查询相关的人员
     */
    List<WmhUser> selectUserByTagId(String tagId) throws Exception;
    /**
     * 根据手机号 查询user表记录
     * @param wmhUser
     * @return
     */
    List<WmhUser> selectUserByPhone(WmhUser wmhUser) throws Exception;

    /**
     * 插入一条user信息，根据手机号
     * @param wmhUser
     */
    void insertUserByJzOwner(WmhUser wmhUser);

    void updateUser(WmhUser wmhUser) throws Exception;

    int insertSelective(WmhUser wmhUser) throws Exception;
    /**
     * 根据用户名获取用户(可能重名)
     * @param userName   用户名
     * @return
     */
	public List<WmhUser> getUserByUserName(String userName);
	
	/**
	 * 后台管理删除用户
	 * @author 王鹏翔
	 * @Date 2017年4月5日  下午1:24:35
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);
	/**
	 * 根据手机号删除用户
	 * @param phone
	 * @return
	 */
	public boolean deleteByPhone(String phone);

}
