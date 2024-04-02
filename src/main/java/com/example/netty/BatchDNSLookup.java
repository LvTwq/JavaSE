package com.example.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BatchDNSLookup {

    private static final String[] DOMAINS = {"www.baidu.com", "www.qq.com", "www.163.com"};

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new BatchDNSLookupHandler());

            for (String domain : DOMAINS) {
                CompletableFuture<List<InetAddress>> future = new CompletableFuture<>();
                b.connect(new InetSocketAddress(domain, 80)).addListener((ChannelFuture channelFuture) -> {
                    if (channelFuture.isSuccess()) {
                        channelFuture.channel().writeAndFlush(Unpooled.wrappedBuffer(("GET / HTTP/1.1\r\nHost: " + domain + "\r\n\r\n").getBytes()));
                        BatchDNSLookupHandler handler = channelFuture.channel().pipeline().get(BatchDNSLookupHandler.class);
                        handler.setFuture(future);
                    } else {
                        future.completeExceptionally(channelFuture.cause());
                    }
                });

                List<InetAddress> addresses = future.get();
                for (InetAddress address : addresses) {
                    System.out.println(domain + ": " + address.getHostAddress());
                }
            }
        } finally {
            group.shutdownGracefully();
        }
    }

    private static class BatchDNSLookupHandler extends ChannelInboundHandlerAdapter {
        private CompletableFuture<List<InetAddress>> future;
        private ByteBuf buffer;

        public void setFuture(CompletableFuture<List<InetAddress>> future) {
            this.future = future;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            buffer = Unpooled.buffer();
            super.channelActive(ctx);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof ByteBuf) {
                ByteBuf byteBuf = (ByteBuf) msg;
                buffer.writeBytes(byteBuf);
                byteBuf.release();
            }
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//            ctx.channel().remoteAddress().toString().substring(1).split(":")[0];
//            List<InetAddress> addresses = InetAddress.getAllByName();
//            future.complete(addresses);
            super.channelReadComplete(ctx);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            future.completeExceptionally(cause);
            ctx.close();
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            buffer.release();
            super.channelInactive(ctx);
        }
    }
}
