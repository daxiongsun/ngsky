package com.ngsky.im.service;

import com.ngsky.im.connection.Connection;
import com.ngsky.im.constant.Constant;
import com.ngsky.im.util.UUIDUtil;
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
        // 一个用户和服务器之间建立一个连接，回话中，通过服务器推送消息给和该用户相关用户
        Connection connection = new Connection();
        String cid = UUIDUtil.generateUUID();    // 可将 cid 缓存到redis中,以便记录对话，群组关系
        log.info("add connection, cid is {}", cid);
        String name = "";
        connection.setCid(cid);
        connection.setName(name);
        connection.setChannel(ctx.channel());
        Constant.CONNECTION_MAP.put(cid, connection);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("-------------------- Server channelRead -------------------");






    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("-------------------- Server channelReadComplete -------------------");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    }
}
