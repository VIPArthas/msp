package com.wh.dao.xlwapp;

import com.wh.dto.system.UserSearchDto;
import com.wh.entity.UserCash;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface UserCashMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserCash record);

    int insertSelective(UserCash record);

    UserCash selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserCash record);

    int updateByPrimaryKey(UserCash record);

    List<Map<String, Object>> selectByActiveListPage(UserSearchDto userSearchDto, RowBounds rowBounds);
    
    UserCash selectByUserId(String userId);
    
}