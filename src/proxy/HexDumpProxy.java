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
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
  
  public final class HexDumpProxy {
  
      static final int LOCAL_PORT = Integer.parseInt(System.getProperty("localPort", "8443"));
      static final String REMOTE_HOST = System.getProperty("remoteHost", "10.10.2.1");
      static final int REMOTE_PORT = Integer.parseInt(System.getProperty("remotePort", "3128"));
  
      public static void main(String[] args) throws Exception {
          System.err.println("Proxying *:" + LOCAL_PORT + " to " + REMOTE_HOST + ':' + REMOTE_PORT + " ...");
  
          // Configure the bootstrap.
          EventLoopGroup bossGroup = new NioEventLoopGroup(1);
          EventLoopGroup workerGroup = new NioEventLoopGroup();
          try {
              ServerBootstrap b = new ServerBootstrap();
              b.group(bossGroup, workerGroup)
               .channel(NioServerSocketChannel.class)
               .handler(new LoggingHandler(LogLevel.INFO))
               .childHandler(new HexDumpProxyInitializer(REMOTE_HOST, REMOTE_PORT))
               .childOption(ChannelOption.AUTO_READ, false)
               .bind(LOCAL_PORT).sync().channel().closeFuture().sync();
         } finally {
              bossGroup.shutdownGracefully();
              workerGroup.shutdownGracefully();
          }
      }
  }
