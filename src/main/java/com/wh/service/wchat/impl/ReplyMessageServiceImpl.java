package com.wh.service.wchat.impl;

import com.wh.constants.Constants;
import com.wh.dao.wchat.TbAutoReplyMapper;
import com.wh.entity.TbAutoReply;
import com.wh.message.resp.Article;
import com.wh.message.resp.NewsMessage;
import com.wh.message.resp.TextMessage;
import com.wh.service.wchat.ReplyMessageService;
import com.wh.util.BaseModel;
import com.wh.util.MessageUtil;
import com.wh.util.PaginationInterceptor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by danding on 2016/8/5.
 */
@Service
public class ReplyMessageServiceImpl implements ReplyMessageService {
    @Autowired
    private TbAutoReplyMapper tbAutoReplyMapper;

    @Override
    public void insert(TbAutoReply tbAutoReply) {
        this.tbAutoReplyMapper.insertSelective(tbAutoReply);
    }

    @Override
    public void update(TbAutoReply tbAutoReply) {
        this.tbAutoReplyMapper.updateByPrimaryKeySelective(tbAutoReply);
    }

    @Override
    public String getAutoReplyMessageByCode(String code, String fromUserName, String toUserName) {
        TbAutoReply tbAutoReply = this.tbAutoReplyMapper.selectByCode(code);
        int type = Integer.parseInt(tbAutoReply.getType());
        switch (type){
            case 0://图文消息
                List<Article> articleList = new ArrayList<Article>();
                Article article = new Article();
                article.setTitle(tbAutoReply.getTitle());
                article.setUrl(tbAutoReply.getUrl());
                article.setPicUrl(tbAutoReply.getPicpath());
                article.setDescription(tbAutoReply.getDescription());
                articleList.add(article);
                NewsMessage newsMessage = new NewsMessage();
                newsMessage.setToUserName(fromUserName);
                newsMessage.setFromUserName(toUserName);
                newsMessage.setCreateTime(System.currentTimeMillis());
                newsMessage.setMsgType(MessageUtil.RESQ_MESSAGE_TYPE_NEWS);
                newsMessage.setArticleCount(articleList.size());
                newsMessage.setArticles(articleList);
                return  MessageUtil.newsMessageToXml(newsMessage);
            case 1://文本消息
                TextMessage textMessage = new TextMessage();
                textMessage.setContent(tbAutoReply.getDescription());
                textMessage.setFromUserName(toUserName);
                textMessage.setToUserName(fromUserName);
                textMessage.setMsgType(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
                textMessage.setCreateTime(System.currentTimeMillis());
                return MessageUtil.textMessageToXml(textMessage);
        }

        TextMessage textMessage = new TextMessage();
        textMessage.setContent("噢[衰]，小岗目前还不认识这条命令也！[大哭]");
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setMsgType(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        return MessageUtil.textMessageToXml(textMessage);

    }

    @Override
    public String getWelcomeInfo(String fromUserName, String toUserName) {
        TbAutoReply welcome = this.tbAutoReplyMapper.selectByWelcomeInfo(1);
        if(welcome==null){
            TextMessage textMessage = new TextMessage();
            textMessage.setContent("终于等到你...");
            textMessage.setFromUserName(toUserName);
            textMessage.setToUserName(fromUserName);
            textMessage.setMsgType(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
            textMessage.setCreateTime(System.currentTimeMillis());
            return MessageUtil.textMessageToXml(textMessage);
        }else{
            int type = Integer.parseInt(welcome.getType());
            switch (type){
                case 0://图文消息
                    List<Article> articleList = new ArrayList<Article>();
                    Article article = new Article();
                    article.setTitle(welcome.getTitle());
                    article.setUrl(welcome.getUrl());
                    article.setPicUrl(welcome.getPicpath());
                    article.setDescription(welcome.getDescription());
                    articleList.add(article);
                    NewsMessage newsMessage = new NewsMessage();
                    newsMessage.setToUserName(fromUserName);
                    newsMessage.setFromUserName(toUserName);
                    newsMessage.setCreateTime(System.currentTimeMillis());
                    newsMessage.setMsgType(MessageUtil.RESQ_MESSAGE_TYPE_NEWS);
                    newsMessage.setArticleCount(articleList.size());
                    newsMessage.setArticles(articleList);
                    return  MessageUtil.newsMessageToXml(newsMessage);
                case 1://文本消息
                    TextMessage textMessage = new TextMessage();
                    textMessage.setContent(welcome.getDescription());
                    textMessage.setFromUserName(toUserName);
                    textMessage.setToUserName(fromUserName);
                    textMessage.setMsgType(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
                    textMessage.setCreateTime(System.currentTimeMillis());
                    return MessageUtil.textMessageToXml(textMessage);
            }

            TextMessage textMessage = new TextMessage();
            textMessage.setContent("噢[衰]，小岗目前还不认识这条命令也！[大哭]");
            textMessage.setCreateTime(System.currentTimeMillis());
            textMessage.setMsgType(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
            textMessage.setFromUserName(toUserName);
            textMessage.setToUserName(fromUserName);
            return MessageUtil.textMessageToXml(textMessage);
        }
    }

    @Override
    public List<Object> queryPageList(TbAutoReply tbAutoReply) {
        int pageSize = Constants.pageSize;
        int startNum = pageSize * (tbAutoReply.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(tbAutoReply.getCurrentpage(), tbAutoReply.getRscount());
        List<Map<Object, Object>> listMaps = this.tbAutoReplyMapper.queryByTypeListPage(tbAutoReply, rowBounds);
        BaseModel baseModel = PaginationInterceptor.endPage();
        List<Object> result = new ArrayList<Object>();
        result.add(listMaps);
        result.add(baseModel.getCurrentpage());
        result.add(baseModel.getRscount());
        return result;
    }

    @Override
    public void setWelcomeInfo(int id) {
        TbAutoReply welcomeLast = this.tbAutoReplyMapper.selectByWelcomeInfo(1);
        welcomeLast.setIswelcome(0);
        this.tbAutoReplyMapper.updateByPrimaryKeySelective(welcomeLast);

        TbAutoReply tbAutoReply = new TbAutoReply();
        tbAutoReply.setId(id);
        tbAutoReply.setIswelcome(1);
        this.tbAutoReplyMapper.updateByPrimaryKeySelective(tbAutoReply);
    }

    @Override
    public TbAutoReply queryById(Integer id) {
        return this.tbAutoReplyMapper.selectByPrimaryKey(id);
    }
}
