package com.wh.service.wv;

import com.wh.entity.WvApply;
import com.wh.entity.WvNumber;

import java.util.List;

/**
 * @author lp
 * @Description: ${todo}(这里用一句话描述这个方法的作用)
 * @date 2016/11/8 14:59
 * @return ${return_type}    返回类型
 */
public interface WvService {
    //申请报名
    String add(WvApply wvApply) throws  Exception;
    //查看数据库是否有这个邀请码
    WvApply selectByKey(int key) throws  Exception;
    //根据openId查找
    WvApply selectByOpenId(String openId) throws  Exception;
    //根据推荐码获取用户下的所有被推荐人
    List<WvApply> getPresenteeList(int key2)throws Exception;
    //查找所有的报名的用户
    List<Object> searchAll(WvApply wvApply) throws Exception;
    //根据身份证号进行查找
    List<Object>   searchByCardId(WvApply wvApply) throws Exception;
    //根据学号进行查找
    List<Object>   searchBySchoolNumber(WvApply wvApply)throws  Exception;
    //根据名字进行查找
    List<Object>   searchByName(WvApply wvApply) throws  Exception;
    //根据手机号进行查找
    List<Object>   searchByPhone(WvApply wvApply) throws  Exception;
    //更新报名信息
    String update(WvApply wvApply) throws  Exception;
    
    /**
     * 根据id查询申请人信息
     * @author 王鹏翔
     * @Date 2016年11月16日  下午4:53:57
     * @param id
     * @return
     * @throws Exception
     */
    WvApply selectByPrimaryKey(String id) throws Exception;
    
    /**
     * 修改个人资料
     * @author 王鹏翔
     * @Date 2016年11月16日  下午6:47:28
     * @param apply
     * @return
     * @throws Exception
     */
    String updateCheck(WvApply wvApply) throws Exception;
    //没有注册的时候签到
    void addUser(WvApply wvApply) throws  Exception;

    /**
     * 根据openId更新，没有验证条件
     * @param wvApply
     * @throws Exception
     */
    void updateByOpenId(WvApply wvApply) throws Exception;

    //查看已签到、未报名的用户
    List<Object>  notApplyData(WvApply wvApply) throws Exception;
    //已报名、未缴费；
    List<Object>  notPay(WvApply wvApply) throws Exception;
    //已报名、已缴费；
    List<Object>  payEd(WvApply wvApply) throws Exception;

    //查看已经报名的人数
    WvNumber selectNumber() throws Exception;
    //更改已经报名的人数
    void updateNumber(WvNumber wvNumber) throws  Exception;
    //添加
    void insertNumber(WvNumber wvNumber) throws  Exception;

}
