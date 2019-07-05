package encoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

public class StringMessageEncoder extends MessageToByteEncoder<String> {
    private static final String TAG = "StringMessageEncoder";
    public StringMessageEncoder() {
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, String s, ByteBuf byteBuf) throws Exception {
        try {
            ByteBuf buffer = Unpooled.copiedBuffer(s, CharsetUtil.UTF_8);
            channelHandlerContext.writeAndFlush(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
