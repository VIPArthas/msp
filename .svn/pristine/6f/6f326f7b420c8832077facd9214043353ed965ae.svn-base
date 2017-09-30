package com.wh.service.base.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wh.base.BaseMapper;
import com.wh.base.PageBounds;
import com.wh.exception.ServiceException;
import com.wh.service.base.BaseService;
import com.wh.util.BaseModel;
import com.wh.util.base.GenericsUtils;

public class BaseServiceImpl<T extends BaseModel,PK extends Serializable> implements BaseService<T, PK> {
	public BaseMapper<T, PK> baseDAO;
	public T entity;
	public Class<PK> id;
	
	public BaseServiceImpl(){
		this.entity = instanceAnnotationObject();
		this.id = instanceAnnotationID();
	}
	
	@SuppressWarnings("unchecked")
	public T instanceAnnotationObject() {
		try {
			return (T) GenericsUtils.getSuperClassGenricType(getClass(), 0)
					.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public Class<PK> instanceAnnotationID() {
		try {
			return  GenericsUtils.getSuperClassGenricType(getClass(), 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean delete(PK modelPK) throws ServiceException {
		return baseDAO.deleteByPrimaryKey(modelPK) > 0 ? true : false;
	}

	@Override
	public boolean save(T model) throws ServiceException {
		return baseDAO.insertSelective(model) > 0 ? true : false;
	}

	@Override
	public T load(PK modelPK) throws ServiceException {
		return baseDAO.selectByPrimaryKey(modelPK);
	}

	@Override
	public boolean update(T model) throws ServiceException {
		return baseDAO.updateByPrimaryKeySelective(model) > 0 ? true : false;
	}

	@Override
	public List<T> findList(Map<String, Object> map, PageBounds pages) throws ServiceException {
		return baseDAO.findList(map, pages);
	}

	@Override
	public int countList(Map<String, Object> map) throws ServiceException {
		return baseDAO.countList(map);
	}

}
