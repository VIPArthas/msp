package com.wh.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wh.message.custom.*;
import com.wh.message.resp.*;
import com.wh.message.resp.Article;
import com.wh.message.resp.ImageMessage;
import com.wh.message.resp.MusicMessage;
import com.wh.message.resp.NewsMessage;
import com.wh.message.resp.TextMessage;
import com.wh.message.resp.VideoMessage;
import com.wh.message.resp.VoiceMessage;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by danding on 2015/8/14.
 */
public class MessageUtil {
    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    /**
     * 请求消息类型：图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    /**
     * 请求消息类型：语音
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    /**
     * 请求消息类型：视频
     */
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    /**
     * 请求消息类型：小视频
     */
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 返回消息类型：文本
     */
    public static final String RESQ_MESSAGE_TYPE_TEXT = "text";
    /**
     * 返回消息类型：语音
     */
    public static final String RESQ_MESSAGE_TYPE_VOICE = "voice";
    /**
     * 返回消息类型：视频
     */
    public static final String RESQ_MESSAGE_TYPE_VIDEO = "video";
    /**
     * 返回消息类型：音乐
     */
    public static final String RESQ_MESSAGE_TYPE_MUSIC = "music";
    /**
     * 返回消息类型：图文
     */
    public static final String RESQ_MESSAGE_TYPE_NEWS = "news";
    /**
     * 事件类型：订阅
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    /**
     * 事件类型：取消订阅
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    /**
     * 事件类型：推送位置
     */
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    /**
     * 事件类型：自定义菜单点击事件
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";
    /**
     * 事件类型：点击菜单跳转链接
     */
    public static final String EVENT_TYPE_VIEW = "VIEW";


    /**
     * 解析微信发来的请求xml
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String,String> parseXml(String request)throws Exception{
        //将解析结果存储到HashMap中
        Map<String,String> map = new HashMap<String, String>();
        StringReader sr = new StringReader(request);
        InputSource is = new InputSource(sr);
        SAXReader sReader = new SAXReader();
        try{
            Document document = sReader.read(is);
            Element root = document.getRootElement();
            //得到根元素下的所有子节点
            List<Element> elementList = root.elements();
            //遍历节点
            for (Element element:elementList){
                map.put(element.getName(),element.getText());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return map;
    }
    /**
     * 解析微信发来的请求xml
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String ,String> parseXml(HttpServletRequest request) throws Exception{
        //将解析结果存储到HashMap中
        Map<String,String> map = new HashMap<String, String>();
        //从request中获得输入流
        InputStream inputStream = request.getInputStream();
        //读取输入流
        SAXReader reader = new SAXReader();
        reader.setEncoding("UTF-8");
        Document document = reader.read(inputStream);
        //得到根元素
        Element root = document.getRootElement();
        //得到根元素下的所有子节点
        List<Element> elementList = root.elements();
        //遍历节点
        for (Element element:elementList){
            map.put(element.getName(),element.getText());
        }
        //释放资源
        inputStream.close();
        inputStream = null;
        return  map;
    }

    /**
     * 扩展xstream，使其支持CDATA块
     */
    private static XStream xStream = new XStream(new XppDriver(){
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out){
                //对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @Override
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if(cdata){
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * 文本消息对象转换成xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        xStream.alias("xml",textMessage.getClass());
        return  xStream.toXML(textMessage);
    }

    /**
     * 图片消息对象转换成xml
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage){
        xStream.alias("xml",imageMessage.getClass());
        return  xStream.toXML(imageMessage);
    }

    /**
     * 音乐消息对象转换成xml
     * @param musicMessage
     * @return
     */
    public static String musicMessageToXml(MusicMessage musicMessage){
        xStream.alias("xml",musicMessage.getClass());
        return  xStream.toXML(musicMessage);
    }

    /**
     * 视频消息对象转换成xml
     * @param videoMessage
     * @return
     */
    public static String videoMessageToXml(VideoMessage videoMessage){
        xStream.alias("xml",videoMessage.getClass());
        return  xStream.toXML(videoMessage);
    }

    /**
     * 语音消息对象转换成xml
     * @param voiceMessage
     * @return
     */
    public static String voiceMessageToXml(VoiceMessage voiceMessage){
        xStream.alias("xml",voiceMessage.getClass());
        return  xStream.toXML(voiceMessage);
    }

    /**
     * 图文消息对象转换成xml
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage){
        xStream.alias("xml",newsMessage.getClass());
        xStream.alias("item",Article.class);
        return  xStream.toXML(newsMessage);
    }

    /**
     * RESP下的图文消息转换为Custom下的图文消息
     * @param newsMessage
     * @return
     */
    public static com.wh.message.custom.NewsMessage RespToCustom_NewsMessage(NewsMessage newsMessage){
        com.wh.message.custom.NewsMessage message = new  com.wh.message.custom.NewsMessage();
        message.setMsgtype(newsMessage.getMsgType());
        message.setTouser(newsMessage.getToUserName());
        List<com.wh.message.custom.Article> articleList = new ArrayList<com.wh.message.custom.Article>();
        News news = new News();
        for(Article article1:newsMessage.getArticles()){
            com.wh.message.custom.Article article = new com.wh.message.custom.Article();
            article.setPicurl(article1.getPicUrl());
            article.setDescription(article1.getDescription());
            article.setTitle(article1.getTitle());
            article.setUrl(article1.getUrl());
            articleList.add(article);
        }
        news.setArticles(articleList);
        message.setNews(news);
        return message;
    }
}
