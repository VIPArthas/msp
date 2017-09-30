package com.wh.dao.wmh;

import java.util.List;
import java.util.Map;

import com.wh.base.BaseMapper;
import com.wh.base.PageBounds;
import com.wh.entity.WmhFaultRelate;
import com.wh.exception.DAOException;


public interface WmhFaultRelateMapper extends BaseMapper<WmhFaultRelate, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(WmhFaultRelate record);

    int insertSelective(WmhFaultRelate record);

    WmhFaultRelate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WmhFaultRelate record);

    int updateByPrimaryKey(WmhFaultRelate record);

	List<Map<String, Object>> findfaultUserList(Map<String, Object> map, PageBounds pageBounds)throws DAOException;
}