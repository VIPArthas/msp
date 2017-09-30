package com.wh.message.resp;

import java.util.List;

/**
 * 回复：图文消息
 * Created by danding on 2015/8/14.
 */
public class NewsMessage extends BaseMessage{
    private int ArticleCount;
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}
