package com.zy.many.server.netty.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            //接收从服务端发送的数据
            ByteBuf buf = (ByteBuf)msg;
            byte[] data = new byte[buf.readableBytes()];
            buf.readBytes(data);
            String request = new String(data, "utf-8");
            System.out.println("Client get: " + request);
            
            String respond = "收到";
            
    		ctx.write(Unpooled.copiedBuffer(respond.getBytes()));//发送给服务端数据
            
    		ctx.flush();

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
        System.out.println("连上服务端");
    }
}