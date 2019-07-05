package core;

import decode.JsonObjectDecoder;
import encoder.JsonObjectEncoder;
import encoder.StringMessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    SimpleChannelInboundHandler handler;

    public ServerInitializer(SimpleChannelInboundHandler<String> handler) {
        this.handler = handler;
    }

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        ch.pipeline().addLast(new JsonObjectEncoder());
        ch.pipeline().addLast(new StringMessageEncoder());
        ch.pipeline().addLast(new JsonObjectDecoder());
        ch.pipeline().addLast(new decode.StringMessageDecoder());
        //自己的处理器
        pipeline.addLast(handler);
    }
}
