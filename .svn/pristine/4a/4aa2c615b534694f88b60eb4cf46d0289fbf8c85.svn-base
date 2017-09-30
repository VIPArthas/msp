package com.wh.base;

import java.util.List;
import java.util.Map;

import com.wh.exception.DAOException;
import com.wh.util.BaseModel;


/**
 * <strong>BaseMapper</strong><br>
 * <strong>Create on : 2012-2-8<br></strong>
 * <strong>Copyright (C) Ecointel Software Co.,Ltd.<br></strong>
 * @author Jack.Zhang(whyloverjack@163.com)
 * @version <strong>ecointel-epp v1.0.0</strong><br>
 */
public interface BaseMapper<T extends BaseModel, PK extends java.io.Serializable> {

	/**
	 * 写入操作
	 * @param model
	 * @return
	 */
	int insert(T model) throws DAOException;

	/**
	 * 写入操作
	 * @param model
	 * @return
	 */
    int insertSelective(T model)throws DAOException;
	/**
	 * 根据主键进行删除操作
	 * @param modelPK
	 * @return
	 */
	int deleteByPrimaryKey(PK modelPK) throws DAOException;
	
	/**
	 * 根据主键获取目标对象
	 * @param modelPK
	 * @return
	 */
	T selectByPrimaryKey(PK modelPK) throws DAOException;
	
	/**
	 * 更新
	 * @param model
	 * @return
	 */
	int updateByPrimaryKey(T model) throws DAOException;

	/**
	 * 更新
	 * @param model
	 * @return
	 */
	int updateByPrimaryKeySelective(T model) throws DAOException;
	
	/**
	 * 查询列表
	 * @param map
	 * @param pages
	 * @return
	 */
	List<T> findList(Map<String,Object> map,PageBounds pages)throws DAOException;
	
	/**
	 * 查询条件下总的结果数量
	 * @param map
	 * @return
	 */
	int countList(Map<String,Object> map)throws DAOException;
	
}
