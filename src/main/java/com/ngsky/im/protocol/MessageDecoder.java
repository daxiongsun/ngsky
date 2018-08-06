package com.ngsky.im.protocol;

import com.ngsky.im.exception.ParamErrorException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Description 消息解码器(网络数据->应用数据)
 * @Author daxiong
 * @Date 8/6/2018 11:06 PM
 **/
public class MessageDecoder extends ByteToMessageDecoder {
    private final static Logger log = LoggerFactory.getLogger(MessageDecoder.class);
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        log.info("---------------------------------------decode start--------------------------------------");
        if(byteBuf.readableBytes() < MessageHeader.HEADER_LENGTH){
            log.info("message'length is small, is null correct format!");
            throw new ParamErrorException();
        }
        byteBuf.markReaderIndex();  // 标记读索引
        if(MessageHeader.MAGIC != byteBuf.readShort()){
            log.info("message is not verifyed!");
            throw new ParamErrorException();
        }
        byteBuf.markReaderIndex();

        log.info("begin decode for body");
        // 解码
        byte sign = byteBuf.readByte();
        byte type = byteBuf.readByte();
        byte status = byteBuf.readByte();
        byte bodyLen = byteBuf.readByte();
        ByteBuf bodies = byteBuf.readBytes(bodyLen);
        String body = bodies.toString(CharsetUtil.UTF_8);
        log.info("message decoded sign: {}, type:{}, status:{}, bodyLen:{}, body:{}", sign, type, status, bodyLen, body);

        MessagePro message = new MessagePro();
        message.setSign(sign);
        message.setType(type);
        message.setStatus(status);
        message.setBody(body);

        list.add(message);
        log.info("---------------------------------------decode end--------------------------------------");
    }
}
