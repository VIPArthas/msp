package com.wh.service.wv.impl;

import com.wh.constants.Constants;
import com.wh.dao.wchat.TbWchatuserMapper;
import com.wh.dao.wv.WvApplyMapper;
import com.wh.dao.wv.WvNumberMapper;
import com.wh.entity.TbWchatuser;
import com.wh.entity.WvApply;
import com.wh.entity.WvNumber;
import com.wh.service.wv.WvService;
import com.wh.util.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author lp
 * @Description: ${todo}(这里用一句话描述这个方法的作用)
 * @date 2016/11/8 15:00
 * @return ${return_type}    返回类型
 */
@Service
public class WvServiceImpl implements WvService{
    @Autowired
    private WvApplyMapper wvApplyMapper;

    @Autowired
    private TbWchatuserMapper tbWchatuserMapper;

    @Autowired
    private WvNumberMapper wvNumberMapper;
    @Override
    public String add(WvApply wvApply) throws Exception {
        if(StringUtils.isEmpty(wvApply.getName())){
            return "请填写您的姓名";
        }
        if(StringUtils.isEmpty(wvApply.getCardId())){
            return "请填写您的身份证号";
        }
        if(!StringUtil.checkCardId(wvApply.getCardId())){
            return "您的身份证号格式错误";
        }
        if(StringUtils.isEmpty(wvApply.getPhone())){
            return "请填写您的手机号码";
        }
        if(!StringUtil.isMobile(wvApply.getPhone())){
            return "手机格式错误";
        }
        if(StringUtils.isEmpty(wvApply.getShcoolName())){
            return "请选择您的学校";
        }
        if(StringUtils.isEmpty(wvApply.getSchoolNumber())){
            return "请选择您学号";
        }
        if(StringUtils.isEmpty(wvApply.getVacationTime())){
            return "请填写您的放假时间";
        }
        if(StringUtils.isEmpty(wvApply.getStartDate())){
            return "请填写您的开学时间";
        }
        if(!StringUtils.isEmpty(wvApply.getKey1())  && wvApply.getKey1() !=2017 && wvApply.getKey1() !=111111 && wvApply.getKey1() !=222222 && wvApply.getKey1() !=333333 && wvApply.getKey1() !=444444 && wvApply.getKey1() !=555555 && wvApply.getKey1() !=666666){
            WvApply a=wvApplyMapper.selectByKey(wvApply.getKey1());
            if(null==a ){
                return "请填写有效的推荐码";
            }
        }
        if(StringUtils.isEmpty(wvApply.getFactory())){
            return "请填写您意向的工厂";
        }
        String nowTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        wvApply.setCreateTime(nowTime);
        //生成一个数据库里没有的邀请码
        //直招的推荐码为6个1至6个6 用于代理的生成的推荐码不能是这几个
        for(int i=0;i<100;i++){
            Integer radomInt = new Random().nextInt(999999-100000)+100000; //随机生成一个6位的邀请码
            if(111111 == radomInt || 222222 == radomInt || 333333 == radomInt || 444444 == radomInt || 555555 == radomInt || 666666 ==radomInt){
                continue;
            }
            WvApply apply=wvApplyMapper.selectByKey(radomInt);
            if(StringUtils.isEmpty(apply)){
                wvApply.setKey2(radomInt);
                if(StringUtils.isEmpty(wvApply.getId())){
                    wvApply.setId(UUIDUtil.getUUID());
                    wvApplyMapper.insertSelective(wvApply);
                }else{
                    wvApplyMapper.updateByPrimaryKeySelective(wvApply);
                }

                break;
            }else{
                continue;
            }
        }
        return "";
    }


    @Override
    public WvApply selectByKey(int key) throws Exception {
        return wvApplyMapper.selectByKey(key);
    }

    @Override
    public WvApply selectByOpenId(String openId) throws Exception {
        return wvApplyMapper.selectByOpenId(openId);
    }

    @Override
    public List<WvApply> getPresenteeList(int key2) throws Exception {
        List<WvApply> presenteeList=wvApplyMapper.getPresenteeList(key2);
        return presenteeList;
    }

    @Override
    public List<Object> searchAll(WvApply wvApply) throws Exception {
        int pageSize = Constants.newPageSize;
        int startNum = pageSize * (wvApply.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(wvApply.getCurrentpage(), wvApply.getRscount());
        List<WvApply> wvApplyList =wvApplyMapper.searchAllListPage(wvApply, rowBounds);
        if(wvApplyList.size()>0){
            for(int i=0;i<wvApplyList.size();i++){
                WvApply apply = wvApplyList.get(i);
                if(!StringUtils.isEmpty(apply) && StringUtils.isEmpty(apply.getCreateTime())){ //没有注册的话，查找他的微信信息
                    //获取该用户的nickName
                    TbWchatuser tbWchatuser = tbWchatuserMapper.findWchatuserByOpenId(apply.getOpenid());
                    if (!StringUtils.isEmpty(apply)) {
                        String name=tbWchatuser.getNickName();
                        apply.setName(name);
                    }
                }
            }
        }
        BaseModel baseModel = PaginationInterceptor.endPage();
        List<Object> list = new ArrayList<Object>();
        list.add(wvApplyList);
        list.add(baseModel.getCurrentpage());
        list.add(baseModel.getRscount());
        return list;

    }

    @Override
    public List<Object> searchByCardId(WvApply wvApply) throws Exception {
        int pageSize = Constants.newPageSize;
        int startNum = pageSize * (wvApply.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(wvApply.getCurrentpage(), wvApply.getRscount());
        List<WvApply> wvApplyList =wvApplyMapper.searchByCardId(wvApply, rowBounds);
        BaseModel baseModel = PaginationInterceptor.endPage();
        List<Object> list = new ArrayList<Object>();
        list.add(wvApplyList);
        list.add(baseModel.getCurrentpage());
        list.add(baseModel.getRscount());
        return list;
    }

    @Override
    public List<Object> searchBySchoolNumber(WvApply wvApply) throws Exception {
        int pageSize = Constants.newPageSize;
        int startNum = pageSize * (wvApply.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(wvApply.getCurrentpage(), wvApply.getRscount());
        List<WvApply> wvApplyList =wvApplyMapper.searchBySchoolNumber(wvApply, rowBounds);
        BaseModel baseModel = PaginationInterceptor.endPage();
        List<Object> list = new ArrayList<Object>();
        list.add(wvApplyList);
        list.add(baseModel.getCurrentpage());
        list.add(baseModel.getRscount());
        return list;
    }

    @Override
    public List<Object> searchByName(WvApply wvApply) throws Exception {
        int pageSize = Constants.newPageSize;
        int startNum = pageSize * (wvApply.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(wvApply.getCurrentpage(), wvApply.getRscount());
        List<WvApply> wvApplyList =wvApplyMapper.searchByName(wvApply, rowBounds);
        BaseModel baseModel = PaginationInterceptor.endPage();
        List<Object> list = new ArrayList<Object>();
        list.add(wvApplyList);
        list.add(baseModel.getCurrentpage());
        list.add(baseModel.getRscount());
        return list;
    }

    @Override
    public List<Object> searchByPhone(WvApply wvApply) throws Exception {
        int pageSize = Constants.newPageSize;
        int startNum = pageSize * (wvApply.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(wvApply.getCurrentpage(), wvApply.getRscount());
        List<WvApply> wvApplyList =wvApplyMapper.searchByPhone(wvApply, rowBounds);
        BaseModel baseModel = PaginationInterceptor.endPage();
        List<Object> list = new ArrayList<Object>();
        list.add(wvApplyList);
        list.add(baseModel.getCurrentpage());
        list.add(baseModel.getRscount());
        return list;
    }
    @Override
    public String update(WvApply wvApply) throws Exception {
        if(StringUtils.isEmpty(wvApply.getName())){
            return "请填写您的姓名";
        }
        if(StringUtils.isEmpty(wvApply.getCardId())){
            return "请填写您的身份证号";
        }
        if(!StringUtil.checkCardId(wvApply.getCardId())){
            return "您的身份证号格式错误";
        }
        if(StringUtils.isEmpty(wvApply.getPhone())){
            return "请填写您的手机号码";
        }
        if(!StringUtil.isMobile(wvApply.getPhone())){
            return "手机格式错误";
        }
        if(StringUtils.isEmpty(wvApply.getShcoolName())){
            return "请填写您的学校";
        }
        if(StringUtils.isEmpty(wvApply.getSchoolNumber())){
            return "请填写您学号";
        }
        if(StringUtils.isEmpty(wvApply.getVacationTime())){
            return "请填写您的放假时间";
        }
        if(StringUtils.isEmpty(wvApply.getStartDate())){
            return "请填写您的开学时间";
        }
        if(!StringUtils.isEmpty(wvApply.getKey1())  && wvApply.getKey1() !=111111 && wvApply.getKey1() !=222222 && wvApply.getKey1() !=333333 && wvApply.getKey1() !=444444 && wvApply.getKey1() !=555555 && wvApply.getKey1() !=666666){
            WvApply a=wvApplyMapper.selectByKey(wvApply.getKey1());
            if(null==a){
                return "请填写有效的推荐码";
            }
        }
        if(StringUtils.isEmpty(wvApply.getFactory())){
            return "请填写您意向的工厂";
        }
        wvApplyMapper.updateByPrimaryKeySelective(wvApply);
        return "";
    }
	@Override
	public WvApply selectByPrimaryKey(String id) throws Exception {
		return this.wvApplyMapper.selectByPrimaryKey(id);
	}
	@Override
	public String updateCheck(WvApply wvApply) throws Exception {
		if(StringUtils.isEmpty(wvApply.getName())){
            return "请填写您的姓名";
        }
        if(StringUtils.isEmpty(wvApply.getCardId())){
            return "请填写您的身份证号";
        }
        if(!StringUtil.checkCardId(wvApply.getCardId())){
            return "您的身份证号格式错误";
        }
        if(StringUtils.isEmpty(wvApply.getPhone())){
            return "请填写您的手机号码";
        }
        if(!StringUtil.isMobile(wvApply.getPhone())){
            return "手机格式错误";
        }
        if(StringUtils.isEmpty(wvApply.getShcoolName())){
            return "请填写您的学校";
        }
        if(StringUtils.isEmpty(wvApply.getSchoolNumber())){
            return "请填写您学号";
        }
        if(StringUtils.isEmpty(wvApply.getVacationTime())){
            return "请填写您的放假时间";
        }
        if(StringUtils.isEmpty(wvApply.getStartDate())){
            return "请填写您的开学时间";
        }
        if(StringUtils.isEmpty(wvApply.getFactory())){
            return "请填写您意向的工厂";
        }
        this.update(wvApply);
        return "";
	}

    @Override
    public void addUser(WvApply wvApply) throws Exception {
        wvApplyMapper.insertSelective(wvApply);
    }

    @Override
    public void updateByOpenId(WvApply wvApply) throws Exception {
        wvApplyMapper.updateByPrimaryKeySelective(wvApply);
    }

    @Override
    public List<Object>  notApplyData(WvApply wvApply) throws Exception {
        int pageSize = Constants.newPageSize;
        int startNum = pageSize * (wvApply.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(wvApply.getCurrentpage(), wvApply.getRscount());
        List<WvApply> wvApplyList = null;
        try {
            wvApplyList = wvApplyMapper.notApplyData(wvApply, rowBounds);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(wvApplyList.size()>0){
            for(int i=0;i<wvApplyList.size();i++){
                WvApply apply = wvApplyList.get(i);
                if(!StringUtils.isEmpty(apply)){
                    //获取该用户的nickName
                    TbWchatuser tbWchatuser = tbWchatuserMapper.findWchatuserByOpenId(apply.getOpenid());
                    if (!StringUtils.isEmpty(apply)) {
                        String name=tbWchatuser.getNickName();
                        apply.setName(name);
                    }
                }
            }
        }
        BaseModel baseModel = PaginationInterceptor.endPage();
        List<Object> list = new ArrayList<Object>();
        list.add(wvApplyList);
        list.add(baseModel.getCurrentpage());
        list.add(baseModel.getRscount());
        return list;
    }

    @Override
    public List<Object> notPay(WvApply wvApply) throws Exception {
        int pageSize = Constants.newPageSize;
        int startNum = pageSize * (wvApply.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(wvApply.getCurrentpage(), wvApply.getRscount());
        List<WvApply> wvApplyList =wvApplyMapper.notPay(wvApply, rowBounds);
        BaseModel baseModel = PaginationInterceptor.endPage();
        List<Object> list = new ArrayList<Object>();
        list.add(wvApplyList);
        list.add(baseModel.getCurrentpage());
        list.add(baseModel.getRscount());
        return list;
    }

    @Override
    public List<Object> payEd(WvApply wvApply) throws Exception {
        int pageSize = Constants.newPageSize;
        int startNum = pageSize * (wvApply.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(wvApply.getCurrentpage(), wvApply.getRscount());
        List<WvApply> wvApplyList =wvApplyMapper.payEd(wvApply, rowBounds);
        BaseModel baseModel = PaginationInterceptor.endPage();
        List<Object> list = new ArrayList<Object>();
        list.add(wvApplyList);
        list.add(baseModel.getCurrentpage());
        list.add(baseModel.getRscount());
        return list;
    }

    @Override
    public WvNumber selectNumber() throws Exception {
        return wvNumberMapper.selectNumber();
    }

    @Override
    public void updateNumber(WvNumber wvNumber) throws Exception {
        wvNumberMapper.updateByPrimaryKeySelective(wvNumber);
    }

    @Override
    public void insertNumber(WvNumber wvNumber) throws Exception {
        wvNumberMapper.insertSelective(wvNumber);
    }
}
