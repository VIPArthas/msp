package com.wh.dao.xlwapp;

import com.wh.dto.system.UserSearchDto;
import com.wh.entity.User;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectBySelective(User user);

    List<User> selectPhonePassword(User user);

    Map<String,Object> selectUserRealStudent(String userId);

    User selectUserData(String userId);

    int updateByPhoneSelective(User user);

    List<Map<String, Object>> selectUserRealStudentByActiveListPage(UserSearchDto userSearchDto, RowBounds rowBounds);

    int updateByUserId(User user);

    String selectPhoneById(String userId);

    String selectpwdById(String userId);

    /**
     * 根据openId 查询user用户表信息
     * @param openId
     * @return
     */
    User selectUserByOpenId(String openId);
    
    /**
     * 根据用户id查询相关信息
     * @author 王鹏翔
     * @Date 2016年12月12日  下午2:35:20
     * @param userId
     * @return
     */
    Map<String, Object> selectUserReal(String userId);
    List<User> selectUserList(String tagId);

    /**
	 * 后台用户管理列表数据
	 * @author 王鹏翔
	 * @Date 2017年3月7日  下午3:49:12
	 * @param user
	 * @param rowBounds
	 * @return
	 */
	List<Map<String, Object>> selectWmhUserListPage(User user, RowBounds rowBounds);
	
	
	 /**
	 * 微门户全选总人数批量添加、删除标签时获取用户id，对用户标签进行添加
	 * @author 王鹏翔
	 * @Date 2017年3月7日  下午3:49:12
	 * @param user
	 * @return
	 */
	List<Map<String, Object>> selectWmhUserListPage(User user);
	/**
	 * 根据userId查询用户详细信息
	 * @author 王鹏翔
	 * @Date 2017年3月8日  上午9:23:12
	 * @param userId
	 * @return
	 */
	Map<String, Object> selectUserDetailByUserId(String userId);

    List<User> searchAllUser();
    
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
    List<User> selectUserByPhone(String phone);
}