package com.ngsky.im.demo.nettyinit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;

/**
 * @Description TODO
 * @Author daxiong
 * @Date 8/2/2018 11:35 PM
 **/
public class TextServerInitializer extends ChannelInitializer<SocketChannel> {
    SslContext sslCtx;

    public TextServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        if(sslCtx != null) pipeline.addLast(sslCtx.newHandler(socketChannel.alloc()));
        // 处理业务逻辑
        pipeline.addLast(new TextServerHandler());
    }
}
