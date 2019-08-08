package decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class StringMessageDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) {
        try {
            byte[] bytes = new byte[msg.readableBytes()];
            msg.readBytes(bytes);
            out.add(new String(bytes, CharsetUtil.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
