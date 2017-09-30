package com.wh.dao.msp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wh.base.BaseMapper;
import com.wh.base.PageBounds;
import com.wh.exception.DAOException;
import com.wh.mspentity.ds;

@Repository
public interface dsMapper extends BaseMapper<ds, Integer> {
	int deleteByPrimaryKey(Integer id);

	int insert(ds record);

	int insertSelective(ds record);

	ds selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ds record);

	int updateByPrimaryKey(ds record);

	int deleteAll() throws DAOException;

	List<Map<String, Object>> findList1(Map<String, Object> map, PageBounds pageBounds)throws DAOException;
}