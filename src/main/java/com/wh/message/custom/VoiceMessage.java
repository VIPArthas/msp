package com.wh.message.custom;

/**
 * Created by danding on 2015/8/19.
 */
public class VoiceMessage extends BaseMessage {
    private Media voice;

    public Media getVoice() {
        return voice;
    }

    public void setVoice(Media voice) {
        this.voice = voice;
    }
}
