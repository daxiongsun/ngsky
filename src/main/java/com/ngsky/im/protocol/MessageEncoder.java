package com.ngsky.im.protocol;

import com.ngsky.im.exception.ParamErrorException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

/**
 * @Description 消息编码器(应用数据编码为网络数据)
 *
 *  __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __
 * |           |           |           |           |              |                          |
 *       2           1           1           1            4               Uncertainty
 * |__ __ __ __|__ __ __ __|__ __ __ __|__ __ __ __|__ __ __ __ __|__ __ __ __ __ __ __ __ __|
 * |           |           |           |           |              |                          |
 *     Magic        Sign        Type       Status     Body Length         Body Content
 * |__ __ __ __|__ __ __ __|__ __ __ __|__ __ __ __|__ __ __ __ __|__ __ __ __ __ __ __ __ __|
 *
 * 协议头9个字节定长
 *     Magic      // 数据包的验证位，short类型
 *     Sign       // 消息标志，请求／响应／通知，byte类型
 *     Type       // 消息类型，登录／发送消息等，byte类型
 *     Status     // 响应状态，成功／失败，byte类型
 *     BodyLength // 协议体长度，int类型
 *
 * @Author daxiong
 * @Date 8/6/2018 10:05 PM
 **/
public class MessageEncoder extends MessageToByteEncoder<MessagePro> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MessagePro msg, ByteBuf byteBuf) throws Exception {
        if(msg == null)
            throw new ParamErrorException();
        String body = msg.getBody();
        if(body == null)
            throw new ParamErrorException("body is null");

        // 对body进行编码
        byte[] bodies = body.getBytes(CharsetUtil.UTF_8);
        byteBuf.writeShort(MessageHeader.MAGIC);
        byteBuf.writeByte(msg.getSign());
        byteBuf.writeByte(msg.getType());
        byteBuf.writeByte(msg.getStatus());
        byteBuf.writeBytes(bodies);
    }
}
