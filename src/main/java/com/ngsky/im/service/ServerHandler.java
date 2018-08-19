package com.ngsky.im.service;

import com.ngsky.im.constant.Constant;
import com.ngsky.im.exception.ParamErrorException;
import com.ngsky.im.protocol.MessagePro;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description TODO
 * @Author daxiong
 * @Date 8/2/2018 10:50 PM
 **/
public class ServerHandler<I> extends ChannelInboundHandlerAdapter {

    private final static Logger log = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("-------------------- Server channelActive -------------------");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("-------------------- Server channelRead -------------------");
        if(msg instanceof MessagePro){
            MessagePro message = (MessagePro) msg;
            message.setChannel(ctx.channel());
            boolean offer = Constant.MESSAGE_QUEUE.offer(message);
            log.info("add task to messageQueue...");
            if(!offer){
                log.error("add task failed! Server is busy!");
                // 通知客服端: 服务 器正忙，稍后再试
                // ...
            }
            log.info("add task to messageQueue successful!");
        } else {
            throw new ParamErrorException("accepted message is not server's!");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("-------------------- Server channelReadComplete -------------------");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    }
}
