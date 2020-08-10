package cn.duzou.netty.client.model;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Nio 的实现
 * @author LiDaShuang
 * @version 1.0.0
 */
public class P6eNioModel extends P6eModel {

    @Override
    protected EventLoopGroup group(Bootstrap bootstrap) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        bootstrap.group(eventLoopGroup);
        return eventLoopGroup;
    }

    @Override
    protected void option(Bootstrap bootstrap) {

    }

    @Override
    public void channel(Bootstrap bootstrap) {
        bootstrap.channel(NioSocketChannel.class);
    }

}
