package com.wh.service.rgpp.impl;

import com.wh.pojo.MemberInfo;
import com.wh.util.DateUtil;
import com.wh.util.WeiXinPlatformUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.dao.wchat.TbPlatformMapper;
import com.wh.dao.wchat.TbWchatuserMapper;
import com.wh.entity.TbPlatform;
import com.wh.entity.TbWchatuser;
import com.wh.service.rgpp.WchatuserPlatService;
import com.wh.util.UUIDUtil;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WchatuserPlatServiceImpl implements WchatuserPlatService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbWchatuserMapper tbWchatuserMapper;

    @Autowired
    private TbPlatformMapper tbPlatformMapper;

    @Override
    public TbWchatuser findWchatUserByOpenId(String openId) {
        return tbWchatuserMapper.findWchatuserByOpenId(openId);
    }

    @Override
    public String queryUserIdByOpenId(String openId) {
        TbWchatuser tbWchatuser = this.tbWchatuserMapper.findWchatuserByOpenId(openId);
        if (tbWchatuser != null) {
            return tbWchatuser.getUserId();
        }
        return null;
    }

    @Override
    public String queryPlatformIdByOpenId(String openId) {
        TbWchatuser tbWchatuser = this.tbWchatuserMapper.findWchatuserByOpenId(openId);
        if (tbWchatuser != null) {
            return tbWchatuser.getPlatformId();
        }
        return null;
    }

    @Override
    public String queryPlatformTypeByOpenId(String openId) {
        TbWchatuser tbWchatuser = this.tbWchatuserMapper.findWchatuserByOpenId(openId);
        if (tbWchatuser != null) {
            TbPlatform platform = this.tbPlatformMapper.queryById(tbWchatuser.getPlatformId());
            if (platform != null) {
                return platform.getType();
            }
        }
        return null;
    }

    @Override
    public void update(TbWchatuser tbWchatuser) {
        this.tbWchatuserMapper.updateTbWchatuser(tbWchatuser);
    }

    @Override
    public void add(TbWchatuser tbWchatuser, String appAccout) {
        TbPlatform tbPlatform = this.tbPlatformMapper.queryByAccout(appAccout);
        tbWchatuser.setPlatformId(tbPlatform.getId());
        tbWchatuser.setId(UUIDUtil.getUUID());
        this.tbWchatuserMapper.saveTbWchatuser(tbWchatuser);
    }



    @Override
    public void add(TbWchatuser tbWchatuser) {
        this.tbWchatuserMapper.saveTbWchatuser(tbWchatuser);
    }

    @Override
    public void saveOrAddBySns(TbWchatuser tbWchatuser) {
        TbWchatuser tbWchatuser1 = findWchatUserByOpenId(tbWchatuser.getOpenid());

        if (null != tbWchatuser1 && !StringUtils.isEmpty(tbWchatuser1.getId())){
            tbWchatuser.setId(tbWchatuser1.getId());
            this.tbWchatuserMapper.updateTbWchatuserById(tbWchatuser);
        }else{
            this.tbWchatuserMapper.saveTbWchatuser(tbWchatuser);
        }
    }

    @Override
    public List<TbWchatuser> queryByUserId(String user_id) {
        return tbWchatuserMapper.queryByUserId(user_id);
    }

	@Override
	public List<Map<String, Object>> selectWchatUserAddress() {
		return tbWchatuserMapper.selectWchatUserAddress();
	}

    @Override
    public void saveOrUpdate(TbWchatuser tbWchatuser, String appAccout) {
        TbPlatform tbPlatform = this.tbPlatformMapper.queryByAccout(appAccout);
        tbWchatuser.setPlatformId(tbPlatform.getId());

        if(this.tbWchatuserMapper.selectByPrimaryKey(tbWchatuser.getId())==null){
            MemberInfo memberInfo = WeiXinPlatformUtil.getMemberInfo(tbWchatuser.getOpenid(),"1");
            if(memberInfo!=null){
                log.info("第一次更新成功:{}",memberInfo.getNickname());
                tbWchatuser.setHeadImg(memberInfo.getHeadimgurl());
                tbWchatuser.setNickName(memberInfo.getNickname());
                tbWchatuser.setCreatetime(new Date());
            }else{
                log.info("第一次更新失败:{}");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                memberInfo = WeiXinPlatformUtil.getMemberInfo(tbWchatuser.getOpenid(),"1");
                if(memberInfo!=null){
                    log.info("第二次更新成功:{}",memberInfo.getNickname());
                    tbWchatuser.setHeadImg(memberInfo.getHeadimgurl());
                    tbWchatuser.setNickName(memberInfo.getNickname());
                    tbWchatuser.setCreatetime(new Date());
                }else {
                    log.info("第二次更新失败:{}");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    memberInfo = WeiXinPlatformUtil.getMemberInfo(tbWchatuser.getOpenid(),"1");
                    if(memberInfo!=null){
                        log.info("第三次更新成功:{}",memberInfo.getNickname());
                        tbWchatuser.setHeadImg(memberInfo.getHeadimgurl());
                        tbWchatuser.setNickName(memberInfo.getNickname());
                        tbWchatuser.setCreatetime(new Date());
                    }else {
                        log.info("第三次更新失败:{}");
                    }
                }

            }
            if(StringUtils.isEmpty(tbWchatuser.getCreatetime())){
                tbWchatuser.setCreatetime(new Date());
            }
            this.tbWchatuserMapper.saveTbWchatuser(tbWchatuser);
        }else{
            this.tbWchatuserMapper.updateByPrimaryKey(tbWchatuser);
        }

    }


    public void updateMemberInfo(){
        List<TbWchatuser> list = this.tbWchatuserMapper.queryNoMemberInfo();
        for (TbWchatuser tbWchatuser:list){
            MemberInfo memberInfo =  WeiXinPlatformUtil.getMemberInfo(tbWchatuser.getOpenid(), "1");
            if(memberInfo!=null){
                tbWchatuser.setHeadImg(memberInfo.getHeadimgurl());
                tbWchatuser.setNickName(memberInfo.getNickname());
                this.tbWchatuserMapper.updateTbWchatuser(tbWchatuser);
            }
        }
    }

    @Override
    public void updateStatusByOpenId(TbWchatuser tbWchatuser) {
        this.tbWchatuserMapper.updateStatusByOpenId(tbWchatuser);
    }

    @Override
    public TbWchatuser searchByUserId(String userId) {
        TbWchatuser tbWchatuser=tbWchatuserMapper.searchByUserId(userId);
        return tbWchatuser;
    }
    
    @Override
    public void deleteUserByUserId(String userId) {
        tbWchatuserMapper.deleteUserByUserId(userId);
    }

	@Override
	public void deleteUserByOpenId(String openId) {
		
		 tbWchatuserMapper.deleteUserByOpenId(openId);
	}

	@Override
	public void updateOpenIdNull(String openId) {
		tbWchatuserMapper.updateOpenIdNull(openId);
		
	}
}
