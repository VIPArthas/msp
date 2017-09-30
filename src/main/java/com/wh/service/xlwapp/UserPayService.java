package com.wh.service.xlwapp;

import com.wh.dto.system.UserSearchDto;
import com.wh.entity.User;
import com.wh.entity.UserPay;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-4-29.
 */
public interface UserPayService {

    /**
     * 查询交易明细列表，带分页
     * @param userSearchDto
     * @return
     */
    List<Object> selectByActiveListPage(UserSearchDto userSearchDto) throws Exception;

    /**
     * 查询当天最大的流水号
     * @return
     * @throws Exception
     * @autor lyk
     * @date 2016年10月10日
     */
    Long selectNowMaxNum() throws Exception;

    /**
     *  更新付费信息
     * @throws Exception
     * @autor lyk
     * @date 2016年10月10日
     */
    void updatePay(UserPay userPay) throws Exception;
    
    
    /**
     * 根据userid查询该用户账单明细
     * @author 王鹏翔
     * @Date 2016年11月17日  上午11:14:46
     * @param userPay
     * @return
     */
    List<UserPay> selectUserPayByUserIdListPage(UserPay userPay)throws Exception;
    
    /**
     * 根据userid查询该用户被冻结明细
     * @author 王鹏翔
     * @Date 2016年11月17日  下午2:33:04
     * @param userPay
     * @return
     */
    List<UserPay> selectFreezeUserPayByUserId(UserPay userPay)throws Exception;
    
    /**
     * 
     * @Title: deleteBzj 
     * @Description: 删除同一个任务下某个人与校联网之间保障金的流动记录
     * @author wd
     * @Date 2016年11月30日  下午3:16:09 
     * @param userId
     * @param receiverId
     * @param payNote
     * @return
     * @return void    返回类型
     */
    void deleteBzj(String userId,String receiverId,String payNote);
    
    /**
     * 根据发送人和接收人id删除相关记录
     * @author 王鹏翔
     * @Date 2016年12月20日  下午1:32:15
     * @param pay
     */
    void deleteUserPayRecord(UserPay pay)throws Exception;
    void  insertSelective(UserPay userPay) throws  Exception;

    /**
     * @Description: ${todo}(支付宝支付后返回数据库处理)
     * @author lp
     * @date 2017年2月28日10:32:59
     * @return ${return_type}    返回类型
     *
     */
     void updateUserPayByZFB(UserPay userPay) throws Exception;
     
     /**
      * 查询支付记录（校联微门户）
      * @author 王鹏翔
      * @Date 2017年3月7日  下午4:57:45
      * @param pay
      * @return
      */
     List<Object> selectWmhPayListPage(UserPay pay) throws Exception;
    /**
     *@Author:liping
     *@Description:根据payId查询改条记录
     *@Date:2017年3月15日14:39:05
     */
    UserPay searchById(String id) throws Exception;
     /**
      * 微门户生成订单号
      * @author 王鹏翔
      * @Date 2017年3月15日  下午1:42:58
      * @param type
      * @return
      * @throws Exception
      */
     Long getPayNumbyPayType(Integer type) throws Exception;;
     
     /**
      * 根据支付类型查询流水单号
      * @author 王鹏翔
      * @Date 2017年3月15日  下午1:34:25
      * @param type
      * @return
      */
     Long selectMaxWmhPayNum(Integer type) throws Exception;
     
     /**
      * 微门户支付记录添加操作
      * @author 王鹏翔
      * @Date 2017年3月16日  下午5:14:40
      * @param userPay
      * @throws Exception
      */
     void  insertWmhUserPay(UserPay userPay) throws  Exception;


     /**
      *@Author:liping
      *@Description: 单独更新userPay表
      *@Date: 2017年3月20日14:44:37
      */
    void update(UserPay userPay) throws Exception;

	Integer getPayCountPageWx(Map<String, Object> map);
	/**
	 *  每次返回20条数据
	 * @param pay
	 * @return
	 */
	List<Object> selectWmhPayListPageWx(UserPay pay);
}
