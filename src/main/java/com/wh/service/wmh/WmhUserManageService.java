package com.wh.service.wmh;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import com.wh.entity.WmhUser;
import net.sf.json.JSONObject;

/** 
 * @param 
 * @author 王鹏翔 
 * @date 2017年3月7日 下午3:21:47 
 * @return 
 */
public interface WmhUserManageService {
	
	/**
	 * 后台用户管理列表数据
	 * @author 王鹏翔
	 * @Date 2017年3月7日  下午3:49:12
	 * @param user
	 * @return
	 */
	List<Object> selectWmhUserListPage(WmhUser user) throws Exception;
	
	/**
	 * 微门户全选总人数批量添加、删除标签时获取用户id，对用户标签进行添加
	 * @author 王鹏翔
	 * @Date 2017年3月7日  下午3:49:12
	 * @param user
	 * @return
	 */
	List<Map<String, Object>> selectWmhUserList(WmhUser user)throws Exception;
	
	/**
	 * 根据userId查询学生详细信息
	 * @author 王鹏翔
	 * @Date 2017年3月8日  上午9:23:12
	 * @param userId
	 * @return
	 */
	Map<String, Object> selectUserDetailByUserId(String userId) throws Exception;
	
	/**
	 * 添加用户时，校验数据
	 * @author 王鹏翔
	 * @Date 2017年3月8日  下午2:11:14
	 * @param user
	 * @return
	 */
	JSONObject checkUserInfo(HttpServletResponse response,WmhUser user);
	
	/**
	 * 编辑用户时验证用户信息
	 * @author 王鹏翔
	 * @Date 2017年3月17日  下午4:23:53
	 * @param response
	 * @param user
	 */
	JSONObject editCheckUserInfo(HttpServletResponse response,WmhUser user);
	
	/**
     * 根据userId查询用户详情
     * @author 王鹏翔
     * @Date 2017年3月13日  下午4:18:57
     * @param userId
     * @return
     */
    Map<String, Object> selectWmhUserByUserId(String userId) throws Exception;
    
    /**
     * 验证用户信息
     * @author 王鹏翔
     * @Date 2017年3月14日  下午2:02:28
     * @param user
     * @return
     * @throws Exception
     */
    JSONObject verifyUser(Map<String, Object> user) throws Exception;
    
    /**
     * 导入用户
     * @author 王鹏翔
     * @Date 2017年3月14日  下午2:51:25
     * @param user
     * @throws Exception
     */
    void importUserList(List<Map<String, Object>> userList) throws Exception;

	Integer getUserCountPageWx(Map<String, Object> map);
}
