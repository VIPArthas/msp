package com.wh.service.wmh.impl;

import com.wh.controller.common.BaseController;
import com.wh.dao.wmh.WmhUserMapper;
import com.wh.entity.WmhUser;
import com.wh.service.wmh.WmhUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WmhUser on 2017/3/23.
 */
@Service
public class WmhUserServiceImpl implements WmhUserService {

	@Autowired
	WmhUserMapper wmhUserMapper;

	@Override
	public WmhUser selectUserById(String userId) {
		WmhUser user = null;
		if (!StringUtils.isEmpty(userId)) {
			user = wmhUserMapper.selectByPrimaryKey(userId);
		}
		return user;
	}

	@Override
	public List<WmhUser> selectUserByTagId(String tagId) throws Exception {
		List<WmhUser> list = wmhUserMapper.selectUserList(tagId);
		return list;
	}

	/**
	 * 根据手机号 查询user表记录
	 *
	 * @param user
	 * @return
	 */
	public List<WmhUser> selectUserByPhone(WmhUser user) {
		if (null != user && !StringUtils.isEmpty(user.getPhone())) {
			return this.wmhUserMapper.selectBySelective(user);
		}
		return new ArrayList<>();
	}

	@Override
	public void insertUserByJzOwner(WmhUser wmhUser) {
		this.wmhUserMapper.insertSelective(wmhUser);
	}

	@Override
	public void updateUser(WmhUser wmhUser) throws Exception {
		this.wmhUserMapper.updateByPrimaryKeySelective(wmhUser);
	}

	@Override
	public int insertSelective(WmhUser wmhUser) throws Exception {
		return this.wmhUserMapper.insertSelective(wmhUser);
	}

	/**
	 * 根据用户名获取用户(可能重名)
	 * 
	 * @param userName
	 *            用户名
	 * @return
	 */
	@Override
	public List<WmhUser> getUserByUserName(String userName) {

		return wmhUserMapper.getUserByUserName(userName);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.wmhUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public boolean deleteByPhone(String phone) {

		return wmhUserMapper.deleteByPhone(phone) > 0 ? true : false;
	}

}
