package com.wh.dao.xlwapp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wh.entity.UserLogin;

public interface UserLoginMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserLogin record);

    int insertSelective(UserLogin record);

    UserLogin selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserLogin record);
    
    int updateByUserIdSelective(UserLogin record);

    int updateByPrimaryKey(UserLogin record);
    
    List<UserLogin> selectByUserId(String userId);

    int updateByUserId(UserLogin userLogin);
    
    /**
     * 后台统计页面：查询用户活跃度
     * 当前系统全部用户总数量，6个月活跃用户数量，30天活跃用户数量，24小时活跃用户数量
     * 当前系统实名用户总数量，6个月活跃实名用户数量，30天活跃实名用户数量，24小时活跃实名用户数量
     * @return
     * @author 徐优优
     * @date 2016年4月22日
     */
    public List<Map<String, Object>> queryUserActiveDegree();
    
    /**
     * 30日内月活用户每日数量变化曲线图
     * @return
     * @author 徐优优
     * @date 2016年4月22日
     */
    public List<Map<String, Object>> queryUserActive30DLine();
    
    /**
     * 72小时在线用户每小时数量变化曲线图
     * @return
     * @author 徐优优
     * @date 2016年4月22日
     */
    public List<Map<String, Object>> queryUserActive72HLine();
    /**
     * 两用户最近登陆的时间差
     * @param otherUserId
     * @param myUserId
     * @return
     */
	int getHourDiff(@Param(value="otherUserId")String otherUserId,@Param(value="myUserId") String myUserId);
}