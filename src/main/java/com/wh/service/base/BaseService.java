package com.wh.service.base;

import java.util.List;
import java.util.Map;
import com.wh.base.PageBounds;
import com.wh.exception.ServiceException;
import com.wh.util.BaseModel;

/**
 * 业务基类
 * 好处有2点      1直接调用增删改查方法,不必每一个service 都重写一遍  
 * 2 直接调用 ,缩小了方法字符长度
 * @author Leo
 *
 * @param <T>
 * @param <PK>
 */
public abstract interface BaseService<T extends BaseModel, PK extends java.io.Serializable> {
	
	/**
	 * 按主键进行删除操作
	 * @param modelPK 主键
	 * @return
	 */
	boolean delete(PK modelPK) throws ServiceException;

	/**
	 * 添加对象
	 * @param record
	 * @return
	 */
    boolean save(T model) throws ServiceException;

    /**
     * 根据主键获取数据对象
     * @param modelPK 主键
     * @return
     */
    T load(PK modelPK) throws ServiceException;
    
    /**
     * 更新
     * @param model
     * @return
     */
    boolean update(T model) throws ServiceException;

	/**
	 * 查询列表
	 * @param map
	 * @param pages
	 * @return
	 */
	List<T> findList(Map<String,Object> map,PageBounds pages) throws ServiceException;
	
	/**
	 * 查询条件下总的结果数量
	 * @param map
	 * @return
	 */
	int countList(Map<String,Object> map) throws ServiceException;
}
