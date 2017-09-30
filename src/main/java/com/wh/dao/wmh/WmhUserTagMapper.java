package com.wh.dao.wmh;

import java.util.List;
import java.util.Map;

import com.wh.entity.WmhUserTag;

public interface WmhUserTagMapper {
	
	
	/**
	 * 根据标签id和userId查询该用户下是不是有此标签
	 * @author 王鹏翔
	 * @Date 2017年3月8日  下午5:28:39
	 * @param userTag
	 * @return
	 */
	WmhUserTag selectUserTagByCondition(WmhUserTag userTag);
	
	/**
	 * 根据用户标签id删除用户下的该标签
	 * @author 王鹏翔
	 * @Date 2017年3月8日  下午3:40:55
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);

    int insert(WmhUserTag record);

    /**
     * 添加用户标签
     * @author 王鹏翔
     * @Date 2017年3月8日  下午1:31:16
     * @param record
     * @return
     */
    int insertSelective(WmhUserTag record);

    WmhUserTag selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WmhUserTag record);

    int updateByPrimaryKey(WmhUserTag record);
    
    /**
     * 根据用户id查询用户标签
     * @author 王鹏翔
     * @Date 2017年3月7日  下午3:43:48
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectUserTagList(String userId);
    
    /**
     * 根据userId删除该用户下的所有标签
     * @author 王鹏翔
     * @Date 2017年3月8日  上午9:59:34
     * @param userId
     * @return
     */
    int deleteUserTagByUserId(String userId);
    
    
    /**
     * 
     * @Title: getTagIdAndUserCount 
     * @Description: 根据标签名称查询标签ID和具有该标签的用户数量
     * @author wd
     * @Date 2017年3月8日  上午9:39:18 
     * @param tagName
     * @return
     * @return Map<String,Object>    返回类型
     */
    Map<String,Object> getTagIdAndUserCount(String tagName);
    
    /**
     * 
     * @Title: getAllUserCount 
     * @Description: 查询所有的用户数量
     * @author wd
     * @Date 2017年3月8日  下午4:40:17 
     * @return
     * @return Map<String,Object>    返回类型
     */
    Map<String,Object> getAllUserCount();
    
    /**
     * 
     * @Title: getUserInfoByPhone 
     * @Description: 根据手机号查询用户ID和真实姓名
     * @author wd
     * @Date 2017年3月8日  上午11:55:46 
     * @param phone
     * @return
     * @return String    返回类型
     */
    Map<String,Object> getUserInfoByPhone(String phone);

    List<WmhUserTag> searchTagById(String tagId);
    
    //一次性分页用户tagList
	List<Map<String, Object>> getTagListByUserIds(List<String> userIdList);
}