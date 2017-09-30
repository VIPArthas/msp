package com.wh.service.wmh;

import java.util.List;
import java.util.Map;

import com.wh.entity.WmhUserTag;

/** 
 * @param 
 * @author 王鹏翔 
 * @date 2017年3月8日 上午10:04:50 
 * @return 
 */
public interface WmhUserTagService {

	/**
	 * 根据标签id和userId查询该用户下是不是有此标签
	 * @author 王鹏翔
	 * @Date 2017年3月8日  下午5:28:39
	 * @param userTag
	 * @return
	 */
	WmhUserTag selectUserTagByCondition(WmhUserTag userTag) throws Exception;
	
	/**
	 * 根据用户标签id删除用户下的该标签
	 * @author 王鹏翔
	 * @Date 2017年3月8日  下午3:40:55
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id) throws Exception;
	
	/**
     * 根据用户id查询用户标签
     * @author 王鹏翔
     * @Date 2017年3月7日  下午3:43:48
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectUserTagList(String userId) throws Exception;
	
	/**
     * 根据userId删除该用户下的所有标签
     * @author 王鹏翔
     * @Date 2017年3月8日  上午9:59:34
     * @param userId
     * @return
     */
    int deleteUserTagByUserId(String userId) throws Exception;
    
    /**
     * 添加用户标签
     * @author 王鹏翔
     * @Date 2017年3月8日  下午12:00:15
     * @param record
     * @return
     */
    int insertSelective(WmhUserTag record)throws Exception;
    /**
     * 根据标签id查询使用该标签的所有人
     */
    List<WmhUserTag> searchTagById(String tagId) throws  Exception;
}
