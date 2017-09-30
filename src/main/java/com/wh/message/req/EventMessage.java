package com.wh.message.req;

/**
 * 事件消息
 * Created by danding on 2015/8/14.
 */
public class EventMessage extends BaseMessage {
    private String Event;

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}
