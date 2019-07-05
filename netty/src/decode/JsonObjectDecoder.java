package decode;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class JsonObjectDecoder extends MessageToMessageDecoder<String> {
    private static final String TAG = "JsonObjectDecoder";

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, String s, List<Object> list) {
        list.add(s);
    }
}
