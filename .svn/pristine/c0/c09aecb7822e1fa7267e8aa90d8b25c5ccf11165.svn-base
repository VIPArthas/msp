package com.wh.dao.wmh;

import java.util.List;
import java.util.Map;

import com.wh.entity.WmhTagUse;

public interface WmhTagUseMapper {
    int deleteByPrimaryKey(String id);

    int insert(WmhTagUse record);

    /**
     * 添加标签
     * @author 王鹏翔
     * @Date 2017年3月8日  上午10:34:39
     * @param record
     * @return
     */
    int insertSelective(WmhTagUse record);

    WmhTagUse selectByPrimaryKey(String id);

    /**
     * 修改标签
     * @author 王鹏翔
     * @Date 2017年3月8日  下午1:23:29
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(WmhTagUse record);

    int updateByPrimaryKey(WmhTagUse record);
    
    /**
     * 根据标签名称查询标签信息
     * @author 王鹏翔
     * @Date 2017年3月8日  上午11:39:41
     * @param tagName
     * @return
     */
    WmhTagUse selectTagByTagName(String tagName);
    
    /**
     * 
     * @Title: getOftenUseTags 
     * @Description: 获取常用的前4个标签
     * @author wd
     * @Date 2017年3月8日  下午2:00:12 
     * @return
     * @return List<Map<String,Object>>    返回类型
     */
    List<Map<String,Object>> getOftenUseTags();
    
    /**
     * 
     * @Title: getLastUseTags 
     * @Description: 获取最近使用的前10个标签
     * @author wd
     * @Date 2017年3月8日  下午2:00:43 
     * @return
     * @return List<Map<String,Object>>    返回类型
     */
    List<Map<String,Object>> getLastUseTags();
    
    /**
     * 
     * @Title: updateTagNumberById 
     * @Description: 根据标签Id修改标签使用次数+1
     * @author wd
     * @Date 2017年3月8日  下午2:38:31 
     * @param wmhTagUse
     * @return void    返回类型
     */
    int updateTagNumberById(WmhTagUse wmhTagUse);
    
    /**
     * 查询常用标签
     * @author 王鹏翔
     * @Date 2017年3月13日  下午1:54:01
     * @return
     */
    List<WmhTagUse> selectCommonUseTagList();
}