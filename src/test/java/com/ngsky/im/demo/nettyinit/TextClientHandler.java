package com.ngsky.im.demo.nettyinit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description TODO
 * @Author daxiong
 * @Date 8/4/2018 9:59 AM
 **/
public class TextClientHandler<I> extends ChannelInboundHandlerAdapter {

    private final static Logger log = LoggerFactory.getLogger(TextClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        log.info("-------------------- Client channelActive -------------------");
        ctx.writeAndFlush(Unpooled.copiedBuffer("你好，我是客户端", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        log.info("-------------------- Client channelRead -------------------");
        ByteBuf in = (ByteBuf) msg;
        System.out.println("客户端接受到数据：" + in.toString(CharsetUtil.UTF_8));
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        super.channelReadComplete(ctx);
        log.info("-------------------- Client channelReadComplete -------------------");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
