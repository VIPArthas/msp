package com.wh.dao.jzxx;

import java.util.List;
import java.util.Map;

import com.wh.entity.WhAttachements;

public interface WhAttachementsMapper {
    int deleteByPrimaryKey(String id);

    int insert(WhAttachements record);

    int insertSelective(WhAttachements record);

    WhAttachements selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WhAttachements record);

    int updateByPrimaryKeyWithBLOBs(WhAttachements record);

    int updateByPrimaryKey(WhAttachements record);
    

    /**
     * 根据管理id查询附件集合
     * 
     * @return
     */
    public List<WhAttachements> queryAttachsById(String linkId);
    
    /**
     * 
     * @Title: getFilePathByLinkId 
     * @Description: 根据linkId查询filePath
     * @author wd
     * @Date 2017年1月21日  上午10:47:19 
     * @param linkId
     * @return
     * @return List<Map<String,Object>>    返回类型
     */
    List<Map<String, Object>> getFilePathByLinkId(String linkId);
}