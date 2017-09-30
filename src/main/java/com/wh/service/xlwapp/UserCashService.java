package com.wh.service.xlwapp;

import java.util.List;

import com.wh.dto.system.UserSearchDto;
import com.wh.entity.UserCash;
import com.wh.util.wxpay.protocol.TransfersProtocol.TransfersResProtocol;

/**
 * 用户提现service
 * 
 * @author 徐优优
 * @date 2016年4月29日
 */
public interface UserCashService {
	/**
     * 查询提现申请明细，带分页
     * @param userSearchDto
     * @return
     */
    List<Object> selectByActiveListPage(UserSearchDto userSearchDto);
	/**
	 * 新增一条记录
	 * @param record
	 * @return
	 * @author 徐优优
	 * @date 2016年4月29日
	 */
	public int insert(UserCash record);

	/**
	 * 新增有值的字段
	 * @param record
	 * @return
	 * @author 徐优优
	 * @date 2016年4月29日
	 */
	public int insertSelective(UserCash record);

	/**
	 * 根据主键筛选字段
	 * @param id
	 * @return
	 * @author 徐优优
	 * @date 2016年4月29日
	 */
	public UserCash selectByPrimaryKey(String id);

	/**
	 * 根据主键更新有值的字段
	 * @param record
	 * @return
	 * @author 徐优优
	 * @date 2016年4月29日
	 */
	public int updateByPrimaryKeySelective(UserCash record);

	/**
	 * 根据主键更新当前的记录
	 * @param record
	 * @return
	 * @author 徐优优
	 * @date 2016年4月29日
	 */
	public int updateByPrimaryKey(UserCash record);
	
	/**
	 * 新增一套提现申请
	 * @param userCash
	 * @author 徐优优
	 * @date 2016年4月29日
	 */
	public void addUserCash(UserCash userCash);
	
	
	public void saveUserCash(UserCash userCash, TransfersResProtocol transfersResProtocol);
	
	
	/**
	 * 根据userId查询提现记录
	 */
	public UserCash selectUserCash(String userId);
	
}
