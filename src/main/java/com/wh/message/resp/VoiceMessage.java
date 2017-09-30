package com.wh.message.resp;

/**
 * 回复：语音消息
 * Created by danding on 2015/8/14.
 */
public class VoiceMessage extends BaseMessage {
    private Voice Voice;

    public com.wh.message.resp.Voice getVoice() {
        return Voice;
    }

    public void setVoice(com.wh.message.resp.Voice voice) {
        Voice = voice;
    }
}
