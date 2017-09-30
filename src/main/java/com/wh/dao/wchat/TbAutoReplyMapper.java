package com.wh.dao.wchat;

import com.wh.entity.TbAutoReply;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface TbAutoReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAutoReply record);

    int insertSelective(TbAutoReply record);

    TbAutoReply selectByPrimaryKey(Integer id);

    TbAutoReply selectByCode(String code);

    TbAutoReply selectByWelcomeInfo(Integer welcomInfo);

    int updateByPrimaryKeySelective(TbAutoReply record);

    int updateByPrimaryKey(TbAutoReply record);

    List<Map<Object, Object>> queryByTypeListPage(TbAutoReply tbAutoReply,RowBounds rowBounds);

}