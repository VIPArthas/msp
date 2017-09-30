package com.wh.service.xlwapp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wh.entity.*;

/**
 * Created by Administrator on 2016-4-11.
 */
public interface UserLoginService {


    /**
     * 插入UserLogin数据, 如果数据库有数据就更新，没有就插入
     * @param userLogin
     */
	int updateUseLoginByUserId(UserLogin userLogin) throws Exception;


	/**
     * 后台统计页面：查询用户活跃度
     * 当前系统全部用户总数量，6个月活跃用户数量，30天活跃用户数量，24小时活跃用户数量
     * 当前系统实名用户总数量，6个月活跃实名用户数量，30天活跃实名用户数量，24小时活跃实名用户数量
     * @return
     * @author 徐优优
     * @date 2016年4月22日
     */
    public List<Map<String, Object>> queryUserActiveDegree() throws Exception;
    
    /**
     * 30日内月活用户每日数量变化曲线图
     * @return
     * @author 徐优优
     * @date 2016年4月22日
     */
    public Map<String, Object> queryUserActive30DLine() throws Exception;
    
    /**
     * 72小时在线用户每小时数量变化曲线图
     * @return
     * @author 徐优优
     * @date 2016年4月22日
     */
    public Map<String, Object> queryUserActive72HLine() throws Exception;

    /**
     * 两用户最近登陆的时间差
     * @param otherUserId
     * @param myUserId
     * @return
     */
	int getHourDiff(String otherUserId, String myUserId);
}
