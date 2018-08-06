package com.ngsky.im.demo.nettyinit;

import com.ngsky.im.demo.TextQueue;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description TODO
 * @Author daxiong
 * @Date 8/2/2018 10:50 PM
 **/
public class TextServerHandler<I> extends ChannelInboundHandlerAdapter {

    private final static Logger log = LoggerFactory.getLogger(TextServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        log.info("-------------------- Server channelActive -------------------");
        TextQueue.TEXT_CHANNEL_MAP.put("001", ctx.channel());

        log.info("服务端注册连接..." + TextQueue.TEXT_CHANNEL_MAP.get("001"));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        log.info("-------------------- Server channelRead -------------------");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        super.channelReadComplete(ctx);
        log.info("-------------------- Server channelReadComplete -------------------");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
    }
}
