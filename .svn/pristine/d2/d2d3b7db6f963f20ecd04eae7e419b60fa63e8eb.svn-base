package com.wh.dao.wmh;

import com.wh.entity.WmhFaultRepair;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wh.base.PageBounds;

public interface WmhFaultRepairMapper {
    int deleteByPrimaryKey(String id);

    int insert(WmhFaultRepair record);

    int insertSelective(WmhFaultRepair record);

    WmhFaultRepair selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WmhFaultRepair record);

    int updateByPrimaryKey(WmhFaultRepair record);
    

    /**
     * 后台故障报修list
     * @param map
     * @param pageBounds
     * @return
     */
	List<Map<String, Object>> findList(Map<String, Object> map, PageBounds pageBounds);
	/**
	 * 后台故障报修记录数
	 * @return
	 */
	int countList();
	/**
	 * 故障报修获取图片路径
	 * @param malId
	 * @return
	 */
	List<String> getPicPathByLinkId(@Param("malId")String malId);
    
    
    
}