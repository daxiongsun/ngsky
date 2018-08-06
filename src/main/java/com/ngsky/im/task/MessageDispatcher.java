package com.ngsky.im.task;

import com.ngsky.im.connection.Connection;
import com.ngsky.im.constant.Constant;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 消息分发, 监听通道，配置成池的概念，同一通道1分钟未请求关闭该通道
 * @Author daxiong
 * @Date 8/4/2018 7:36 PM
 **/
public class MessageDispatcher implements Runnable {

    private final static Logger log = LoggerFactory.getLogger(MessageDispatcher.class);

    private String cid;
    private String body;

    public MessageDispatcher(String cid, String body) {
        this.cid = cid;
        this.body = body;
    }

    private void dispatcher(String cid, String body) {
        log.info("--------------------dispatcher message------------------------");
        while (true) {
            Connection connection = Constant.CONNECTION_MAP.get(cid);
            if (connection != null) {
                Channel channel = connection.getChannel();
                boolean isOk = sendMsg(channel, body);
                if (!isOk) {
                    log.info("Messages send failure!");
                    // 重试发送
                }
                log.info("Messages send successful!");
                break;
            }
        }
    }

    private boolean sendMsg(Channel channel, String body) {
        if (channel != null) {
            channel.writeAndFlush(body);
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        dispatcher(cid, body);
    }
}
