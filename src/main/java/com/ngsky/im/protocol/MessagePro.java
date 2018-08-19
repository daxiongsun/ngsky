package com.ngsky.im.protocol;

import io.netty.channel.Channel;

/**
 * @Description 消息传输协议
 * @Author daxiong
 * @Date 8/5/2018 11:51 PM
 **/
public class MessagePro {
    private byte sign;
    private byte type;   // 类型
    private byte status;  // 状态
    private String body;  // 消息体
    private Channel channel;  // 消息通道

    public byte getSign() {
        return sign;
    }

    public void setSign(byte sign) {
        this.sign = sign;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "MessagePro{" +
                "sign=" + sign +
                ", type=" + type +
                ", status=" + status +
                ", body='" + body + '\'' +
                ", channel=" + channel +
                '}';
    }
}
