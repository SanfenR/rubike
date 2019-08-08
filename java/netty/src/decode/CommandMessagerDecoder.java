package decode;

import bean.NetBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class CommandMessagerDecoder extends MessageToMessageDecoder<String> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, String s, List<Object> list) {
        try {
            NetBean domain = JSON.parseObject(s, new TypeReference<NetBean>() {
            });
            list.add(domain);
        } catch (Exception e) {
            e.printStackTrace();
            list.clear();
            list.add(s);
        }
    }
}
