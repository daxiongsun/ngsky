package com.ngsky.im.demo.nettyinit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ngsky.im.demo.config.TextServerConfig;

import javax.net.ssl.SSLException;

/**
 * @Description TODO
 * @Author daxiong
 * @Date 8/4/2018 9:40 AM
 **/
public class TextClient {

    private final static boolean SSL = System.getProperty("ssl") != null;
    private final static Logger log = LoggerFactory.getLogger(TextClient.class);

    public static void main(String[] args) throws SSLException {
        // configure ssl
        final SslContext sslCtx;
        if (SSL) {
            sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslCtx = null;
        }
        EventLoopGroup group = new NioEventLoopGroup();;
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new LoggingHandler(LogLevel.INFO))
                .handler(new TextClientInitializer(sslCtx));

        // start client
        try {
            ChannelFuture future = bootstrap.connect(TextServerConfig.TEXT_SERVER_ADDR, TextServerConfig.TEXT_SERVER_PORT).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.info("------------------------------client cannot connect server--------------------------------");
            e.printStackTrace();
        } finally{
            // shutdown
        }
    }
}
