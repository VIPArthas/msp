package com.wh.service.xlwapp.impl;

import com.wh.constants.Constants;
import com.wh.dao.xlwapp.UserMapper;
import com.wh.dao.xlwapp.UserPayMapper;
import com.wh.dto.system.UserSearchDto;
import com.wh.entity.User;
import com.wh.entity.UserPay;
import com.wh.service.xlwapp.UserPayService;
import com.wh.util.BaseModel;
import com.wh.util.ConfigConstantsUtil;
import com.wh.util.DateUtil;
import com.wh.util.PaginationInterceptor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-4-29.
 */
@Service
public class UserPayServiceImpl implements UserPayService{


    @Autowired
    private UserPayMapper userPayMapper;

    @Autowired
    private UserMapper userMapper;


    /**
     * 查询交易明细列表，带分页
     * @param userSearchDto
     * @return
     */
    public List<Object> selectByActiveListPage(UserSearchDto userSearchDto) {
        int pageSize = Constants.pageSize;
        int startNum = pageSize * (userSearchDto.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(userSearchDto.getCurrentpage(), userSearchDto.getRscount());

        List<Map<String, Object>> listMaps = this.userPayMapper.selectByActiveListPage(userSearchDto,rowBounds);

        BaseModel baseModel = PaginationInterceptor.endPage();
        List<Object> poList = new ArrayList<Object>();
        poList.add(listMaps);
        poList.add(baseModel.getCurrentpage());
        poList.add(baseModel.getRscount());
        return poList;
    }

    @Override
    public Long selectNowMaxNum() {
        return this.userPayMapper.selectNowMaxNum();
    }

    @Override
    public void updatePay(UserPay userPay) {
        User user = new User();
        user.setId(userPay.getUserId());
        user.setSchoolMoney(userPay.getSchoolMoney());

        User userRe = this.userMapper.selectByPrimaryKey(userPay.getReceiverId());
        if (StringUtils.isEmpty(userRe.getSchoolMoney())) {
            userRe.setSchoolMoney(0 + userPay.getPaySchoolMoney());
        }else {
            userRe.setSchoolMoney(userRe.getSchoolMoney() + userPay.getPaySchoolMoney());
        }

        this.userPayMapper.insertSelective(userPay);
        this.userMapper.updateByPrimaryKeySelective(user);
        this.userMapper.updateByPrimaryKeySelective(userRe);

    }

	@Override
	public List<UserPay> selectUserPayByUserIdListPage(UserPay userPay) {
        return this.userPayMapper.selectUserPayByUserIdListPage(userPay);
	}

	@Override
	public List<UserPay> selectFreezeUserPayByUserId(UserPay userPay) {
		return this.userPayMapper.selectFreezeUserPayByUserId(userPay);
	}
	
	@Override
	public void deleteBzj(String userId,String receiverId,String payNote){
		UserPay userPay = new UserPay();
		userPay.setUserId(userId);
		userPay.setReceiverId(receiverId);
		userPay.setPayNote(payNote);
		userPayMapper.deleteBzj(userPay);
	}

	@Override
	public void deleteUserPayRecord(UserPay pay) {
		this.userPayMapper.deleteUserPayRecord(pay);
	}

    @Override
    public void insertSelective(UserPay userPay) {
        Long paySwift = userPayMapper.selectNowMaxNum();
        if (StringUtils.isEmpty(paySwift)) {
            // 8位日期，加上三位流水号
            userPay.setPayNum(DateUtil.swiftDateNum(new Date(), "yyyyMMdd")*100 + 1);
        }else {
            userPay.setPayNum(DateUtil.swiftDateNum(new Date(), "yyyyMMdd") + paySwift + 1);
        }
        userPayMapper.insertSelective(userPay);
    }

    @Override
    public void updateUserPayByZFB(UserPay userPay) throws Exception {
        UserPay up=userPayMapper.selectByPrimaryKey(userPay.getId());
        if(null!=up){
            up.setPayStatus(Constants.PAY_STATUS_SUCESS);
            userPayMapper.updateByPrimaryKeySelective(up);
            String userId=up.getUserId();
            if(!StringUtils.isEmpty(userId)){
                User user=userMapper.selectByPrimaryKey(userId);
                if(null!=user){
                    Double schoolMoney=up.getPaySchoolMoney()+user.getSchoolMoney();
                    user.setSchoolMoney(schoolMoney);
                    System.out.println(">>>>>支付后的校币是" + schoolMoney  +"支付了"+up.getPaySchoolMoney()/100);
                    userMapper.updateByPrimaryKey(user);
                }
            }
        }
    }

	@Override
	public List<Object> selectWmhPayListPage(UserPay pay) throws Exception {
		int pageSize = Constants.wmhPageSize;
        int startNum = pageSize * (pay.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(pay.getCurrentpage(), pay.getRscount());
        List<Map<String, Object>> payRecordList = this.userPayMapper.selectWmhPayListPage(pay,rowBounds);
        BaseModel baseModel = PaginationInterceptor.endPage();
		List<Object> list = new ArrayList<Object>();
		list.add(payRecordList);
		list.add(baseModel.getCurrentpage());
        list.add(baseModel.getRscount());
		return list;
	}
	


    @Override
    public UserPay searchById(String id) throws Exception {
        UserPay userPay=userPayMapper.selectByPrimaryKey(id);
        return userPay;
    }

	@Override
	public Long selectMaxWmhPayNum(Integer type){
		return this.userPayMapper.selectMaxWmhPayNum(type);
	}

	@Override
	public Long getPayNumbyPayType(Integer type){
		Long paynum = null;
		Long num = selectMaxWmhPayNum(type);
		if(StringUtils.isEmpty(num)){
			if(type == 12){//01
				paynum = Long.parseLong((DateUtil.format(new Date(), "yyyyMMdd")+"01"))*10000+1;
			}else if(type == 13){//02
				paynum = Long.parseLong((DateUtil.format(new Date(), "yyyyMMdd")+"02"))*10000+1;
			}else if(type == 14){//03
				paynum = Long.parseLong((DateUtil.format(new Date(), "yyyyMMdd")+"03"))*10000+1;
			}else{//04
				paynum = Long.parseLong((DateUtil.format(new Date(), "yyyyMMdd")+"04"))*10000+1;
			}
		}else{
			paynum= num+1;
		}
		return paynum;
	}

	@Override
	public void insertWmhUserPay(UserPay userPay) {
		this.userPayMapper.insertSelective(userPay);
	}

    @Override
    public void update(UserPay userPay) throws Exception {
        userPayMapper.updateByPrimaryKeySelective(userPay);
    }

	@Override
	public Integer getPayCountPageWx(Map<String, Object> map) {
		
		return userPayMapper.getPayCountPageWx(map);
	}
	/**
	 * 支付每次  20条
	 */
	@Override
	public List<Object> selectWmhPayListPageWx(UserPay pay) {
		int pageSize = 20;
        int startNum = pageSize * (pay.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(pay.getCurrentpage(), pay.getRscount());
        List<Map<String, Object>> payRecordList = this.userPayMapper.selectWmhPayListPage(pay,rowBounds);
        BaseModel baseModel = PaginationInterceptor.endPage();
		List<Object> list = new ArrayList<Object>();
		list.add(payRecordList);
		list.add(baseModel.getCurrentpage());
        list.add(baseModel.getRscount());
		return list;
	}
}
