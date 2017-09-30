package com.wh.dao.xlwapp;

import com.wh.dto.system.UserSearchDto;
import com.wh.entity.UserRecharge;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface UserRechargeMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRecharge record);

    int insertSelective(UserRecharge record);

    UserRecharge selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRecharge record);

    int updateByPrimaryKey(UserRecharge record);

    List<Map<String,Object>> selectByActiveListPage(UserSearchDto userSearchDto, RowBounds rowBounds);
}