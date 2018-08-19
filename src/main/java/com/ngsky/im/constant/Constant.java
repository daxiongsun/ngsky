package com.ngsky.im.constant;

import com.ngsky.im.connection.Connection;
import com.ngsky.im.protocol.MessagePro;
import com.ngsky.im.task.ITask;
import com.ngsky.im.task.MessageDispatcherTask;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description 业务相关常量
 * @Author daxiong
 * @Date 8/4/2018 4:05 PM
 **/
public class Constant {
    public final static Map<String, Connection> CONNECTION_MAP = new LinkedHashMap<>();  // 采用 LinkedHashMap 保证连接顺序
    public final static LinkedBlockingQueue<MessageDispatcherTask> MESSAGE_DISPATCHER_TASKS_QUEUE = new LinkedBlockingQueue<>();    //  消息分发队列
    public final static LinkedBlockingQueue<MessagePro> MESSAGE_QUEUE = new LinkedBlockingQueue<>();    //  消息分发队列
}
