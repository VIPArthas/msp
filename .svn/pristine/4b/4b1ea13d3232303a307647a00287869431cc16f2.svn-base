package com.wh.service.xlwapp.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wh.constants.Constants;
import com.wh.dao.xlwapp.UserCashMapper;
import com.wh.dto.system.UserSearchDto;
import com.wh.entity.UserCash;
import com.wh.service.xlwapp.UserCashService;
import com.wh.util.BaseModel;
import com.wh.util.PaginationInterceptor;
import com.wh.util.wxpay.protocol.TransfersProtocol.TransfersResProtocol;

import net.sf.json.JSONObject;

@Service
public class UserCashServiceImpl implements UserCashService {

	@Autowired
	private UserCashMapper userCashMapper;
	@Override
	public int insert(UserCash record) {
		return userCashMapper.insert(record);
	}

	@Override
	public int insertSelective(UserCash record) {
		return userCashMapper.insertSelective(null);
	}

	@Override
	public UserCash selectByPrimaryKey(String id) {
		return userCashMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserCash record) {
		return userCashMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserCash record) {
		return userCashMapper.updateByPrimaryKey(record);
	}

	@Override
	public void addUserCash(UserCash userCash) {
		userCash.setCashNum(new Long(new Date().getTime()).intValue());
		
	}

    @Override
    public List<Object> selectByActiveListPage(UserSearchDto userSearchDto) {
        int pageSize = Constants.pageSize;
        int startNum = pageSize * (userSearchDto.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(userSearchDto.getCurrentpage(), userSearchDto.getRscount());

        List<Map<String, Object>> listMaps = this.userCashMapper.selectByActiveListPage(userSearchDto,rowBounds);

        BaseModel baseModel = PaginationInterceptor.endPage();
        List<Object> poList = new ArrayList<Object>();
        poList.add(listMaps);
        poList.add(baseModel.getCurrentpage());
        poList.add(baseModel.getRscount());
        return poList;
    }

	@Override
	public void saveUserCash(UserCash userCash, TransfersResProtocol transfersResProtocol) {
		// TODO Auto-generated method stub
		String returnCode = transfersResProtocol.getReturn_code();
		String resultCode = transfersResProtocol.getResult_code();
		if((!StringUtils.isEmpty(returnCode)&&returnCode.equals("SUCCESS")) && (!StringUtils.isEmpty(resultCode)&&resultCode.equals("SUCCESS"))){
			userCash.setCashStatus(1);
			userCash.setCashRemark("提现成功");
		}else{
			userCash.setCashStatus(2);
			JSONObject remarkObj = new JSONObject();
			remarkObj.put("return_code", transfersResProtocol.getReturn_code());
			remarkObj.put("return_msg", transfersResProtocol.getReturn_msg());
			remarkObj.put("err_code", transfersResProtocol.getErr_code());
			remarkObj.put("err_code_des", transfersResProtocol.getErr_code_des());
			userCash.setCashRemark(remarkObj.toString());
		}
		userCash.setCashTime(new Date());
		userCash.setCashBank("微信");
		userCashMapper.insert(userCash);
	}

	@Override
	public UserCash selectUserCash(String userId) {
		return userCashMapper.selectByUserId(userId);
	}
}
