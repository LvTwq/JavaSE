package com.example.netty;

import cn.hutool.core.date.DateUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author 吕茂陈
 * @date 2022-10-13 16:59
 */
@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

	/**
	 * 连接到服务器时触发
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		while (true) {
			ByteBuf req = Unpooled.copiedBuffer(DateUtil.now(), CharsetUtil.UTF_8);
			ctx.writeAndFlush(req)
					.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
			TimeUnit.SECONDS.sleep(1);
		}
	}

	/**
	 * 消息到来时触发
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		log.info("Current Time: {}", buf.toString(CharsetUtil.UTF_8));
	}

	/**
	 * 发生异常时触发
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
