import core.ServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import utils.Log;

public class NettyService extends SimpleChannelInboundHandler<String> {
    private ChannelFuture serverChannelFuture;
    private NioEventLoopGroup parentGroup;
    private NioEventLoopGroup childGroup;
    private ChannelHandlerContext ctx;

    private static final String TAG = "NettyService";

    public NettyService() {
        this.parentGroup = new NioEventLoopGroup();
        this.childGroup = new NioEventLoopGroup();
    }

    public void sendMsg(String msg) {
        if (ctx != null) {
            Log.d(TAG, "send --->" + msg);
            ctx.writeAndFlush(msg);
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

    public void start() {
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(parentGroup,childGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitializer(this));
            serverChannelFuture = serverBootstrap.bind(12222).sync();
            serverChannelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (ctx != null) {
            this.ctx.close();
        }
        if (serverChannelFuture != null) {
            serverChannelFuture.cancel(true);
            serverChannelFuture = null;
        }
    }

}
