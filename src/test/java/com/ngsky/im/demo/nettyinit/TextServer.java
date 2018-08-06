package com.ngsky.im.demo.nettyinit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ngsky.im.demo.config.TextServerConfig;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * @Description 初始化文本聊天服务
 * @Author daxiong
 * @Date 8/2/2018 10:50 PM
 **/
public class TextServer {

    public static final Logger log = LoggerFactory.getLogger(TextServer.class);

    private static final boolean SSL = System.getProperty("ssl") != null;

    // 向外抛出异常，ssl 认证失败
    public static void main(String[] args) throws CertificateException, SSLException {
        log.info("---------------------------ServerStart config---------------------------------");
        //  配置 SSL
        final SslContext sslCtx;
        if (SSL) {
            SelfSignedCertificate ssc = null;
            ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        log.info("Server message holder");
        new Thread(new MessageHolder()).start();

        EventLoopGroup bossGroup = new NioEventLoopGroup(TextServerConfig.BOSS_THREAD_SIZE);
        EventLoopGroup workGroup = new NioEventLoopGroup(TextServerConfig.WORK_THREAD_SIZE);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new TextServerInitializer(sslCtx));

            log.info("start ServerStart...");
            ChannelFuture future = bootstrap.bind(TextServerConfig.TEXT_SERVER_PORT).sync();
            log.info("start ServerStart successful! location: " + (SSL ? "https" : "http") + "://" + "{}:{}/", TextServerConfig.TEXT_SERVER_ADDR, TextServerConfig.TEXT_SERVER_PORT);
            future.channel().closeFuture().sync();
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        } finally {
            // shutdown
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

}
