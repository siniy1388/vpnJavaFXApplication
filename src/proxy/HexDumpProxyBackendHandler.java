/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;

/**
 *
 * @author oleg
 */

  import io.netty.channel.Channel;
  import io.netty.channel.ChannelFuture;
  import io.netty.channel.ChannelFutureListener;
  import io.netty.channel.ChannelHandlerContext;
  import io.netty.channel.ChannelInboundHandlerAdapter;
  
  public class HexDumpProxyBackendHandler extends ChannelInboundHandlerAdapter {
  
      private final Channel inboundChannel;
  
      public HexDumpProxyBackendHandler(Channel inboundChannel) {
          this.inboundChannel = inboundChannel;
      }
  
      @Override
      public void channelActive(ChannelHandlerContext ctx) {
          ctx.read();
      }
  
      @Override
      public void channelRead(final ChannelHandlerContext ctx, Object msg) {
          inboundChannel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
              @Override
              public void operationComplete(ChannelFuture future) {
                  if (future.isSuccess()) {
                      ctx.channel().read();
                  } else {
                      future.channel().close();
                  }
              }
          });
      }
  
      @Override
      public void channelInactive(ChannelHandlerContext ctx) {
          HexDumpProxyFrontendHandler.closeOnFlush(inboundChannel);
      }
  
      @Override
      public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
          cause.printStackTrace();
          HexDumpProxyFrontendHandler.closeOnFlush(ctx.channel());
      }
  }
