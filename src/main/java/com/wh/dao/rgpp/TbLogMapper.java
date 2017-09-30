package com.wh.dao.rgpp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.wh.entity.TbLog;
import com.wh.entity.TbUser;

public interface TbLogMapper {
	public int deleteByPrimaryKey(String id);

	public int insert(TbLog record);

	public int insertSelectiveLog(TbLog record);

	public TbLog selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(TbLog record);

	public int updateByPrimaryKey(TbLog record);
    
    /**
     * 根据用户Id查询其操作日志(注：原分页方法修改)
     * @return
     */
    public List<TbLog> queryLogByUserIdListPage(TbUser tbUser, RowBounds rowBounds)  throws Exception;
    
    /**
     * 根据userId和doType查询TbLog
     * @param tbLog
     * @return
     * @throws Exception
     * @author 王鹏翔
     * @date 2016年8月26日
     */
    public List<TbLog> queryTbLogByParam(TbLog tbLog);
}