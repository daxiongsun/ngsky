package com.ngsky.im.task;

import com.ngsky.im.connection.Connection;
import com.ngsky.im.constant.Constant;
import com.ngsky.im.protocol.MessageHeader;
import com.ngsky.im.protocol.MessagePro;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 消息分发, 监听通道，配置成池的概念，同一通道1分钟未请求关闭该通道
 * @Author daxiong
 * @Date 8/4/2018 7:36 PM
 **/
public class MessageDispatcherTask implements ITask {

    private final static Logger log = LoggerFactory.getLogger(MessageDispatcherTask.class);

    @Override
    public void run() {
        dispatcher();
    }

    private void dispatcher() {
        log.info("--------------------dispatcher message------------------------");
        while (true) {
            try {
                MessagePro messagePro = Constant.MESSAGE_QUEUE.take();   // 阻塞接受
                byte sign = messagePro.getSign();
                log.info("message sign is {}" + sign);
                switch (sign) {
                    case MessageHeader.REQUEST: // request： client -> server
                        log.info("server accepted client's message");
                        if (messagePro.getType() == MessageHeader.LOGIN) {  // user login
                            log.info("************************************ login start ************************************");
                            Connection loginConn = new Connection();
                            JSONObject bodyJson = new JSONObject(messagePro.getBody());
                            // 数据库验证...
                            // 验证通过，即可登录
                            loginConn.setCid(String.valueOf(bodyJson.getLong("uid")));
                            loginConn.setName(bodyJson.getString("username"));
                            loginConn.setChannel(messagePro.getChannel());
                            Constant.CONNECTION_MAP.put(String.valueOf(bodyJson.getLong("uid")), loginConn);

                            // 返回响应
                            MessagePro responseMsg = new MessagePro();
                            responseMsg.setSign(MessageHeader.RESPONSE);
                            responseMsg.setType(MessageHeader.LOGIN);
                            responseMsg.setStatus(MessageHeader.SUCCESS);
                            responseMsg.setBody("");
                            log.info("server send msg to client, message: {}", responseMsg);
                            messagePro.getChannel().writeAndFlush(responseMsg);
                            log.info("************************************ login success ************************************");
                        }
                        break;
                    case MessageHeader.RESPONSE: // response: server -> client
                        break;
                    case MessageHeader.NOTICE: // notice: server -> client
                        break;
                }
            } catch (InterruptedException e) {
                log.error("interrupt error, e:{}", e);
                e.printStackTrace();
            }
        }
    }

}
