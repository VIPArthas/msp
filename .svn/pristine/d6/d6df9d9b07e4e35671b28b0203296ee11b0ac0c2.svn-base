package com.wh.service.rgpp;

import java.util.List;

import com.wh.entity.TbLog;
import com.wh.entity.TbUser;

public interface LogService {
	
	
	/**
     * 根据用户Id查询其操作日志(注：原分页方法修改)
     * @return
     */
	 List<Object> queryLogByUserIdListPage(TbUser tbUser) throws Exception;
	
	/**
	 * 添加log
	 * @param record
	 * @return
	 */
	int insertSelectiveLog(TbLog record);

	
	/**
     * 根据userId和doType查询TbLog
     * @param tbLog
     * @return
     * @throws Exception
     * @author 王鹏翔
     * @date 2016年8月26日
     */
	List<TbLog> queryTbLogByParam(TbLog tbLog) throws Exception;
}
