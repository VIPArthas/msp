package com.wh.dao.msp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wh.base.BaseMapper;
import com.wh.exception.DAOException;
import com.wh.mspentity.MspTeacherClass;
@Repository
public interface MspTeacherClassMapper extends BaseMapper<MspTeacherClass, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(MspTeacherClass record);

    int insertSelective(MspTeacherClass record);

    MspTeacherClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MspTeacherClass record);

    int updateByPrimaryKey(MspTeacherClass record);

    /**
     * 根据教师工号确定其带班信息
     * @param memeberId
     * @return
     */
	List<Map<String, Object>> tecClassListByMembId(@Param(value="memberId")String memberId)throws DAOException;
}