package com.wh.service.rgpp.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.constants.Constants;
import com.wh.dao.rgpp.TbLogMapper;
import com.wh.entity.TbLog;
import com.wh.entity.TbUser;
import com.wh.service.rgpp.LogService;
import com.wh.util.BaseModel;
import com.wh.util.PaginationInterceptor;

@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	private TbLogMapper tbLogMapper;

	@Override
	public List<Object> queryLogByUserIdListPage(TbUser tbUser) throws Exception {
		int pageSize = Constants.pageSize;
        int startNum = pageSize * (tbUser.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(tbUser.getCurrentpage(), tbUser.getRscount());
        List<TbLog> listMaps = tbLogMapper.queryLogByUserIdListPage(tbUser, rowBounds);
        BaseModel baseModel = PaginationInterceptor.endPage();
        List<Object> poList = new ArrayList<Object>();
        poList.add(listMaps);
        poList.add(baseModel.getCurrentpage());
        poList.add(baseModel.getRscount());
        return poList;
	}

	@Override
	public int insertSelectiveLog(TbLog record) {
		return tbLogMapper.insertSelectiveLog(record);
	}

	@Override
	public List<TbLog> queryTbLogByParam(TbLog tbLog) {
		return (List<TbLog>) this.tbLogMapper.queryTbLogByParam(tbLog);
	}
	
	

}
