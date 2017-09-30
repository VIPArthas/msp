package com.wh.service.wchat;

import com.wh.entity.TbAutoReply;

import java.util.List;

/**
 * 微信消息回复
 * Created by danding on 2016/8/5.
 */
public interface ReplyMessageService {

    void insert(TbAutoReply tbAutoReply);

    void update(TbAutoReply tbAutoReply);

    TbAutoReply queryById(Integer id);

    /**
     * 根据关键词返回指定消息
     * @param code 关键词
     * @param fromUserName
     * @param toUserName
     * @return
     */
    String getAutoReplyMessageByCode(String code,String fromUserName,String toUserName);

    /**
     * 获得微信欢迎信息
     * @param fromUserName
     * @param toUserName
     * @return
     */
    String getWelcomeInfo(String fromUserName,String toUserName);

    /**
     * 返回指定类型的消息
     * @param tbAutoReply
     * @return
     */
     List<Object> queryPageList(TbAutoReply tbAutoReply);

    /**
     * 设置指定id消息为欢迎信息
     * @param id
     */
    void setWelcomeInfo(int id);


}
