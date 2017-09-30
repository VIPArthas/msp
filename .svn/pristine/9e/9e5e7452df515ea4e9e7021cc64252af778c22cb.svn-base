package com.wh.service.msp.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.wh.dao.msp.MspPlatformMapper;
import com.wh.mspentity.MspPlatform;
import com.wh.service.base.impl.BaseServiceImpl;
import com.wh.service.msp.MspPlatformService;
import com.wh.util.msp.AccessToken;
import com.wh.util.msp.QiYeUtil;

@Service
public class MspPlatformServiceImpl extends BaseServiceImpl<MspPlatform, Integer> implements MspPlatformService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	MspPlatformMapper dao;

	@Resource
	public void setBaseDAO(MspPlatformMapper mspPlatformMapper) {
		this.dao = mspPlatformMapper;
		this.baseDAO = mspPlatformMapper;
	}

	@Override
	public void updateAccessToken() {
		List<MspPlatform> platformList = dao.queryAll();
		Date time = new Date();
		time.setTime(System.currentTimeMillis() - 6000000);
		for (MspPlatform platform : platformList) {
			// 如果上次获得AccessToken时间，有效期剩余10分钟以内，则重新获得新的AccessToken
			if (platform.getLasttokentime() == null || platform.getLasttokentime().before(time)) {
				log.info("校验微信企业号平台:{},AccessToken已过期,正在重新获取...", platform.getCorpsecret());
				AccessToken accessToken = QiYeUtil.getAccessToken(platform.getCorpid(), platform.getCorpsecret());
				if (accessToken != null) {
					platform.setAccessToken(accessToken.getToken());
					platform.setLasttokentime(new Date());
					dao.updateByPrimaryKey(platform);
					log.info("AccessToken重新获取成功");
				}

			}
		}

	}

}
