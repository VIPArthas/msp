package com.wh.work;

import com.wh.service.msp.MspPlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 企业号accessToken 定时维护
 * 
 * @author Administrator
 *
 */
@Component("QiyePlatformJob")
public class QiyePlatformJob {
	private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
	private MspPlatformService platformService;

    public void run() {
    	log.info("微信企业号AccessToken维护线程检测中...");
		log.info("定时任务开始......");
		long begin = System.currentTimeMillis();
		try {
			platformService.updateAccessToken();

		} catch (Exception e) {
			log.error("{}", e);
		}
		long end = System.currentTimeMillis();
		log.info("定时任务结束，共耗时：[" + (end - begin)  + "]豪秒");

    }


}
