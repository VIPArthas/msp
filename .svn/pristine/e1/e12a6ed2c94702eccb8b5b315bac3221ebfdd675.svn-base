package com.wh.dao.xlwapp;

import com.wh.dto.system.UserSearchDto;
import com.wh.entity.UserPay;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface UserPayMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserPay record);

    int insertSelective(UserPay record);

    UserPay selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserPay record);

    int updateByPrimaryKey(UserPay record);

    List<Map<String, Object>> selectByActiveListPage(UserSearchDto userSearchDto, RowBounds rowBounds);

    Long selectNowMaxNum();
    
    /**
     * 根据userid查询该用户所有账单明细
     * @author 王鹏翔
     * @Date 2016年11月17日  上午11:14:46
     * @param userPay
     * @param rowBounds
     * @return
     */
    List<UserPay> selectUserPayByUserIdListPage(UserPay userPay);
    
    /**
     * 根据userid查询该用户被冻结明细
     * @author 王鹏翔
     * @Date 2016年11月17日  下午2:33:04
     * @param userPay
     * @return
     */
    List<UserPay> selectFreezeUserPayByUserId(UserPay userPay);
    
    /**
     * 
     * @Title: deleteBzj 
     * @Description: 删除同一个任务下某个人与校联网之间保障金的流动记录
     * @author wd
     * @Date 2016年11月30日  下午3:10:44 
     * @param record
     * @return
     * @return int    返回类型
     */
    int deleteBzj(UserPay record);
    
    /**
     * 根据发送人和接收人id删除相关记录
     * @author 王鹏翔
     * @Date 2016年12月20日  下午1:32:15
     * @param pay
     */
    void deleteUserPayRecord(UserPay pay);
    
    /**
     * 查询支付记录（校联微门户）
     * @author 王鹏翔
     * @Date 2017年3月7日  下午4:57:45
     * @param pay
     * @return
     */
    List<Map<String, Object>> selectWmhPayListPage(UserPay pay, RowBounds rowBounds);
    
    
    /**
     * 根据支付类型查询流水单号
     * @author 王鹏翔
     * @Date 2017年3月15日  下午1:34:25
     * @param type
     * @return
     */
    Long selectMaxWmhPayNum(Integer type);
    /**
     * 根据payType类型获取记录数
     * @param map
     * @return
     */
	Integer getPayCountPageWx(Map<String, Object> map);
}