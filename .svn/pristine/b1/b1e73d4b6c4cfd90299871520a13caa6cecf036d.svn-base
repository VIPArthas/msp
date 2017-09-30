package com.wh.service.wmh.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import com.wh.dao.wmh.WmhUserMapper;
import com.wh.entity.WmhUser;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.wh.constants.Constants;
import com.wh.dao.wchat.TbWchatuserMapper;
import com.wh.dao.wmh.WmhTagUseMapper;
import com.wh.dao.wmh.WmhUserTagMapper;
import com.wh.dao.xlwapp.UserMapper;
import com.wh.entity.User;
import com.wh.entity.WmhTagUse;
import com.wh.entity.WmhUserTag;
import com.wh.service.wmh.WmhUserManageService;
import com.wh.util.BaseModel;
import com.wh.util.DateUtil;
import com.wh.util.PaginationInterceptor;
import com.wh.util.StringUtil;
import com.wh.util.UUIDUtil;
import com.wh.util.WebUtil;

import net.sf.json.JSONObject;

/**
 * @param
 * @author 王鹏翔
 * @date 2017年3月7日 下午3:33:26
 * @return
 */
@Service
public class WmhUserManageServiceImpl implements WmhUserManageService {

	@Autowired
	private WmhUserMapper wmhUserMapper;
	@Autowired
	private WmhUserTagMapper wmhUserTagMapper;
	@Autowired
	private WmhTagUseMapper wmhTagUseMapper;
	@Autowired
	private TbWchatuserMapper tbWchatuserMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<Object> selectWmhUserListPage(WmhUser user) {
		int pageSize = Constants.wmhPageSize;
		int startNum = pageSize * (user.getCurrentpage() - 1);
		RowBounds rowBounds = new RowBounds(startNum, pageSize);
		PaginationInterceptor.startPage(user.getCurrentpage(), user.getRscount());
		List<Map<String, Object>> userList = this.wmhUserMapper.selectWmhUserListPage(user, rowBounds);

		if (userList != null & userList.size() > 0) {

			List<String> userIdList = new ArrayList<String>();

			for (Map<String, Object> map : userList) {
				userIdList.add((String) (map.get("id")));
			}

			List<Map<String, Object>> allTagList = wmhUserTagMapper.getTagListByUserIds(userIdList);

			for (Map<String, Object> map : userList) {
				List<Map<String, Object>> areaList = new ArrayList<Map<String, Object>>();
				for (Map<String, Object> tagMap : allTagList) {
					if (tagMap.containsKey("user_id") && map.get("id").equals(tagMap.get("user_id"))) {
						areaList.add(tagMap);
					}
				}
				map.put("tagList", areaList);
			}

		}
		/*
		 * for (Map<String, Object> map : userList) { //map.put("wx_status",
		 * tbWchatuserMapper.selectTbWchatsuerByUserId((String)map.get("id")));
		 * map.put("tagList",
		 * wmhUserTagMapper.selectUserTagList((String)map.get("id"))); }
		 */
		BaseModel baseModel = PaginationInterceptor.endPage();
		List<Object> list = new ArrayList<Object>();
		list.add(userList);
		list.add(baseModel.getCurrentpage());
		list.add(baseModel.getRscount());
		return list;
	}

	@Override
	public List<Map<String, Object>> selectWmhUserList(WmhUser user) throws Exception {
		return this.wmhUserMapper.selectWmhUserListPage(user);
	}

	@Override
	public Map<String, Object> selectUserDetailByUserId(String userId) {
		Map<String, Object> user = wmhUserMapper.selectUserDetailByUserId(userId);
		if (null != user) {
			user.put("tagList", wmhUserTagMapper.selectUserTagList((String) user.get("id")));
		}
		return user;
	}

	@Override
	public JSONObject checkUserInfo(HttpServletResponse response, WmhUser user) {
		JSONObject jso = new JSONObject();
		if (StringUtils.isEmpty(user.getRealName())) {
			jso.put("code", 1);
			jso.put("msg", "请填写姓名！");
			return jso;
		} else if (StringUtils.isEmpty(user.getPhone())) {
			jso.put("code", 1);
			jso.put("msg", "请填写手机号！");
			return jso;
		} else if (!StringUtils.isEmpty(user.getPhone()) && !StringUtil.isMobile(user.getPhone())) {
			jso.put("code", 1);
			jso.put("msg", "手机号格式不正确！");
			return jso;
		} else if (!StringUtils.isEmpty(user.getPhone()) && StringUtil.isMobile(user.getPhone())
				&& !verifyByPhone(user.getPhone())) {
			jso.put("code", 1);
			jso.put("msg", "新增手机号已存在！");
			return jso;
		} else if (StringUtils.isEmpty(user.getMail())) {
			jso.put("code", 1);
			jso.put("msg", "请填写邮箱！");
			return jso;
		} else if (!StringUtils.isEmpty(user.getMail()) && !StringUtil.checkEmail(user.getMail())) {
			jso.put("code", 1);
			jso.put("msg", "邮箱格式不正确！");
			return jso;
		} else if (!StringUtils.isEmpty(user.getQq())) {
			Pattern p = Pattern.compile("^[1-9][0-9]{5,14}$");
			Matcher m = p.matcher(user.getQq());
			boolean s = m.matches();
			if (!s) {
				jso.put("code", 1);
				jso.put("msg", "qq号格式不正确！");
				return jso;
			}
		}
		jso.put("code", 0);
		jso.put("msg", "校验通过！");
		return jso;
	}

	@Override
	public Map<String, Object> selectWmhUserByUserId(String userId) {
		Map<String, Object> map = this.wmhUserMapper.selectWmhUserByUserId(userId);
		map.put("tagList", wmhUserTagMapper.selectUserTagList((String) map.get("id")));
		return map;
	}

	@Override
	public JSONObject verifyUser(Map<String, Object> user) throws Exception {
		JSONObject jso = new JSONObject();
		if (StringUtils.isEmpty((String) user.get("real_name"))) {
			jso.put("code", 1);
			jso.put("msg", "姓名不能为空");
			return jso;
		} else if (StringUtils.isEmpty((String) user.get("phone"))) {
			jso.put("code", 1);
			jso.put("msg", "手机号不能为空");
			return jso;
		} else if (!StringUtils.isEmpty((String) user.get("phone"))
				&& !StringUtil.isMobile((String) user.get("phone"))) {
			jso.put("code", 1);
			jso.put("msg", "手机号格式不正确");
			return jso;
		} else if (!StringUtils.isEmpty((String) user.get("phone")) && StringUtil.isMobile((String) user.get("phone"))
				&& !verifyByPhone((String) user.get("phone"))) {
			// 验证数据库是否存在该手机号
			jso.put("code", 1);
			jso.put("msg", "新增手机号已存在");
			return jso;
		} else if (StringUtils.isEmpty((String) user.get("mail"))) {
			jso.put("code", 1);
			jso.put("msg", "邮箱不能为空");
			return jso;
		} else if (!StringUtils.isEmpty((String) user.get("mail"))
				&& !StringUtil.checkEmail((String) user.get("mail"))) {
			jso.put("code", 1);
			jso.put("msg", "邮箱格式不正确");
			return jso;
		} /*
			 * else if(StringUtils.isEmpty((String)user.get("qq"))){
			 * jso.put("code", 1); jso.put("msg", "qq号码不能为空"); return jso; }
			 */
		if (!StringUtils.isEmpty((String) user.get("qq"))) {
			Pattern p = Pattern.compile("^[1-9][0-9]{5,14}$");
			Matcher m = p.matcher((String) user.get("qq"));
			boolean s = m.matches();
			if (!s) {
				jso.put("code", 1);
				jso.put("msg", "qq号码格式不正确");
				return jso;
			}
		}
		jso.put("code", 0);
		return jso;
	}

	boolean verifyByPhone(String phone) {
		List<WmhUser> userList = this.wmhUserMapper.selectUserByPhone(phone);
		if (null == userList || (null != userList && userList.size() == 0)) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public void importUserList(List<Map<String, Object>> userList) throws Exception {
		for (Map<String, Object> map : userList) {// 遍历用户集合
			WmhUser user = new WmhUser();

			user.setRealName((String) map.get("real_name"));
			user.setPhone((String) map.get("phone"));
			user.setMail((String) map.get("mail"));
			user.setQq((String) map.get("qq"));

			User user2 = new User();
			user2.setPhone(user.getPhone());
			// 微门户导入用户时，根据用户手机号查询user表是否有数据，如果有则使用user表的userid作为微门户用户的userid，没有则生成userid
			List<User> list = this.userMapper.selectBySelective(user2);
			if (list.size() == 0) {
				user.setId(UUIDUtil.getUUID());
			} else {
				// 获取user表用户id
				user.setId(list.get(0).getId());
			}
			wmhUserMapper.insertSelective(user);

			String[] tagNames = (String[]) map.get("tags");
			for (String tagName : tagNames) {
				WmhTagUse tag1 = this.wmhTagUseMapper.selectTagByTagName(tagName);
				if (null == tag1) {// 标签不存在,创建标签
					WmhTagUse tag = new WmhTagUse();
					tag.setId(UUIDUtil.getUUID());
					tag.setTagName(tagName);
					tag.setLastUseTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
					this.wmhTagUseMapper.insertSelective(tag);// 创建标签
					// 为当前添加用户添加标签
					WmhUserTag userTag = new WmhUserTag();
					userTag.setId(UUIDUtil.getUUID());
					userTag.setUserId(user.getId());
					userTag.setTagId(tag.getId());
					userTag.setTagName(tag.getTagName());
					this.wmhUserTagMapper.insertSelective(userTag);
				} else {// 标签存在，修改标签使用次数
						// 修改标签使用次数
					tag1.setTagNumber(tag1.getTagNumber() + 1);
					tag1.setLastUseTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
					this.wmhTagUseMapper.updateByPrimaryKeySelective(tag1);
					// 为当前添加用户添加标签
					WmhUserTag userTag = new WmhUserTag();
					userTag.setId(UUIDUtil.getUUID());
					userTag.setUserId(user.getId());
					userTag.setTagId(tag1.getId());
					userTag.setTagName(tag1.getTagName());
					this.wmhUserTagMapper.insertSelective(userTag);
				}
			}
		}
	}

	@Override
	public JSONObject editCheckUserInfo(HttpServletResponse response, WmhUser user) {
		JSONObject jso = new JSONObject();
		if (StringUtils.isEmpty(user.getRealName())) {
			jso.put("code", 1);
			jso.put("msg", "请填写姓名！");
			return jso;
		} else if (StringUtils.isEmpty(user.getPhone())) {
			jso.put("code", 1);
			jso.put("msg", "请填写手机号！");
			return jso;
		} else if (!StringUtils.isEmpty(user.getPhone()) && !StringUtil.isMobile(user.getPhone())) {
			jso.put("code", 1);
			jso.put("msg", "手机号格式不正确！");
			return jso;
		} else if (!StringUtils.isEmpty(user.getPhone()) && StringUtil.isMobile(user.getPhone())
				&& !"".equals(editCheckPhone(user))) {
			jso.put("code", 1);
			jso.put("msg", "新增手机号已存在！");
			return jso;
		} else if (StringUtils.isEmpty(user.getMail())) {
			jso.put("code", 1);
			jso.put("msg", "请填写邮箱！");
			return jso;
		} else if (!StringUtils.isEmpty(user.getMail()) && !StringUtil.checkEmail(user.getMail())) {
			jso.put("code", 1);
			jso.put("msg", "邮箱格式不正确！");
			return jso;
		} /*
			 * else if(StringUtils.isEmpty(user.getQq())){ jso.put("code", 1);
			 * jso.put("msg", "请填写qq号！"); WebUtil.write(response,
			 * jso.toString()); return; }
			 */
		if (!StringUtils.isEmpty(user.getQq())) {
			Pattern p = Pattern.compile("^[1-9][0-9]{5,14}$");
			Matcher m = p.matcher(user.getQq());
			boolean s = m.matches();
			if (!s) {
				jso.put("code", 1);
				jso.put("msg", "qq号格式不正确！");
				return jso;
			}
		}
		jso.put("code", 0);
		jso.put("msg", "校验通过！");
		return jso;
	}

	public String editCheckPhone(WmhUser user) {
		List<WmhUser> u = this.wmhUserMapper.selectUserByPhone(user.getPhone());
		if (CollectionUtils.isEmpty(u)) {
			return "";
		} else {
			String userId = u.get(0).getId();
			if (user.getId().equals(userId)) {
				return "";
			} else {
				return "no";
			}
		}
	}

	@Override
	public Integer getUserCountPageWx(Map<String, Object> map) {

		return wmhUserMapper.getUserCountPageWx(map);
	}

}
