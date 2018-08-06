package com.ngsky.im.demo.nettyinit;

import com.ngsky.im.demo.config.TextServerConfig;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;

/**
 * @Description TODO
 * @Author daxiong
 * @Date 8/4/2018 9:51 AM
 **/
public class TextClientInitializer extends ChannelInitializer<SocketChannel> {

    private SslContext sslCtx;

    public TextClientInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        if (sslCtx != null)
            pipeline.addLast(sslCtx.newHandler(sc.alloc(), TextServerConfig.TEXT_SERVER_ADDR, TextServerConfig.TEXT_SERVER_PORT));
        pipeline.addLast(new TextClientHandler());
    }
}
