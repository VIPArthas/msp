package com.wh.service.xlwapp.impl;

import com.wh.dao.xlwapp.SmsVerifyMapper;
import com.wh.entity.SmsVerify;
import com.wh.service.xlwapp.SmsService;
import com.wh.util.DateUtil;
import com.wh.util.UUIDUtil;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 短信发送service
 * 
 * @author 徐优优
 * @date 2016年7月21日
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsVerifyMapper smsVerifyMapper;

    @Override
    public void saveSms(SmsVerify smsVerify) {
        this.smsVerifyMapper.insertSelective(smsVerify);
    }
}
