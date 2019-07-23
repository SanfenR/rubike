import core.ServerInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import utils.Log;

public class NettyClient extends SimpleChannelInboundHandler<String> {

    private static final String TAG = "NettyClient";
    private NioEventLoopGroup eventLoopGroup;
    private ChannelHandlerContext ctx;
    private ChannelFuture channelFuture;

    public NettyClient() {
        this.eventLoopGroup = new NioEventLoopGroup();
    }

    public void connect() {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ServerInitializer(this));
            channelFuture = bootstrap.connect("127.0.0.1", 12222);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (ctx != null) {
            this.ctx.close();
        }
        if (channelFuture != null) {
            channelFuture.cancel(true);
            channelFuture = null;
        }

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) {
        Log.d(TAG, "channelRead0 --->" + s);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Log.d(TAG, "channelActive");
        this.ctx = ctx;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Log.d(TAG, "channelInactive");
        this.ctx = null;
    }

    public void sendMsg(String msg) {
        if (ctx != null) {
            Log.d(TAG, "send --->" + msg);
            ctx.writeAndFlush(msg);
        }
    }
}
