package encoder;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

public class JsonObjectEncoder extends MessageToByteEncoder<JSONObject> {
    private static final String TAG = "JsonObjectDecoder";

    @Override
    protected void encode(ChannelHandlerContext ctx, JSONObject msg, ByteBuf out) {
        ByteBuf buffer = Unpooled.copiedBuffer(msg.toJSONString(), CharsetUtil.UTF_8);
        out.writeBytes(buffer);
        ctx.writeAndFlush(out);
    }
}
