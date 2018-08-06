package com.ngsky.im.connection;

import io.netty.channel.Channel;

/**
 * @Description 连接通道实体
 * @Author daxiong
 * @Date 8/4/2018 4:00 PM
 **/
public class Connection {
    private String cid;  // 连接唯一标识 hash 计算(channel对象)
    private String name; // 连接名称
    private Channel channel;  // 连接通道

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
