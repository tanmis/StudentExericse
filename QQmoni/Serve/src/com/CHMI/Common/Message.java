package com.CHMI.Common;

import java.io.Serializable;

/**
 * @author 茶米酱
 * @version 1.0
 *用户信息存储
 */
public class Message implements Serializable {
    private String sender;//发送者
    private String receiver;//接收者
    private String content;//内容
    private String times;//发送时间
    private String contenttype;//内容类型[定义在接口]



    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }
}
