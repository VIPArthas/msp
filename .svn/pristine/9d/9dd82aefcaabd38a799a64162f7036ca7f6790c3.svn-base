package com.wh.dao.wmh;

import com.wh.base.BaseMapper;
import com.wh.entity.WmhMessage;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface WmhMessageMapper extends BaseMapper<WmhMessage, Integer>{
    int deleteByPrimaryKey(String id);

    int insert(WmhMessage record);

    int insertSelective(WmhMessage record);

    WmhMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WmhMessage record);

    int updateByPrimaryKey(WmhMessage record);
    List<WmhMessage> searchAllMobileListPage(WmhMessage wmhMessage,RowBounds rowBounds);
    
    Map<String,Object> selectMessagesCount();
}