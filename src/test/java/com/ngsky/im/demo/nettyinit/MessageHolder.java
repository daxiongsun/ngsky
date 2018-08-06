package com.ngsky.im.demo.nettyinit;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;
import com.ngsky.im.demo.TextQueue;

/**
 * @Description TODO
 * @Author daxiong
 * @Date 8/4/2018 12:24 PM
 **/
public class MessageHolder implements Runnable {
    @Override
    public void run() {
        while(true){
            Channel channel = TextQueue.TEXT_CHANNEL_MAP.get("001");
            System.out.println("MessagePro:" + channel);
            if (channel != null) {
                System.out.println("=================================================================================");
                try {
                    Thread.sleep(1000);
                    channel.writeAndFlush(Unpooled.copiedBuffer("Hello！", CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
