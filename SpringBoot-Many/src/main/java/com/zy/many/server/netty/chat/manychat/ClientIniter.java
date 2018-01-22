package com.zy.many.server.netty.chat.manychat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ClientIniter extends ChannelInitializer<SocketChannel> {

@Override
protected void initChannel(SocketChannel arg0) throws Exception {
    ChannelPipeline pipeline = arg0.pipeline();
    pipeline.addLast("stringD", new StringDecoder());
    pipeline.addLast("stringC", new StringEncoder());
    pipeline.addLast("http", new HttpClientCodec());
    pipeline.addLast("chat", new ChatClientHandler());
}

}

/*作者：levy兄
链接：http://www.jianshu.com/p/216881b0573d
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/