package com.wh.dao.wmh;

import com.wh.entity.WmhUser;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface WmhUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(WmhUser record);

    int insertSelective(WmhUser record);

    WmhUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WmhUser record);

    int updateByPrimaryKey(WmhUser record);
    List<WmhUser> searchAllUser();
    List<WmhUser> selectUserList(String tagId);
    List<WmhUser> selectBySelective(WmhUser user);

    List<Map<String, Object>> selectWmhUserListPage(WmhUser user);

    /**
     * 后台用户管理列表数据
     * @author 王鹏翔
     * @Date 2017年3月7日  下午3:49:12
     * @param user
     * @param rowBounds
     * @return
     */
    List<Map<String, Object>> selectWmhUserListPage(WmhUser user, RowBounds rowBounds);
    /**
     * 根据userId查询用户详细信息
     * @author 王鹏翔
     * @Date 2017年3月8日  上午9:23:12
     * @param userId
     * @return
     */
    Map<String, Object> selectUserDetailByUserId(String userId);

    /**
     * 根据userId查询用户详情
     * @author 王鹏翔
     * @Date 2017年3月13日  下午4:18:57
     * @param userId
     * @return
     */
    Map<String, Object> selectWmhUserByUserId(String userId);


    /**
     * 根据手机号查询用户信息
     * @author 王鹏翔
     * @Date 2017年3月14日  下午2:16:57
     * @param phone
     * @return
     */
    List<WmhUser> selectUserByPhone(String phone);
    /**
     * 根据用户名获取用户(可能重名)
     * @param userName   用户名
     * @return
     */
	List<WmhUser> getUserByUserName(String userName);
	/**
	 * 微门户微信端后台用户数量
	 * @param map	根据条件筛选
	 * @return
	 */
	Integer getUserCountPageWx(Map<String, Object> map);
	
	/**
	 * 根据手机号删除wmhuser
	 * @param phone
	 * @return
	 */
	int deleteByPhone(@Param(value="phone")String phone);
}