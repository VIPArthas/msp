package com.wh.message.req;

/**
 * 请求：图片消息
 * Created by danding on 2015/8/14.
 */
public class ImageMessage extends BaseMessage {
    private String PicUrl;
    private String MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }


}
