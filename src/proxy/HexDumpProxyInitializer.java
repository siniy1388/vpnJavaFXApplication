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
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
  import io.netty.handler.logging.LogLevel;
  import io.netty.handler.logging.LoggingHandler;
  
  public class HexDumpProxyInitializer extends ChannelInitializer<SocketChannel> {
  
      private final String remoteHost;
      private final int remotePort;
  
      public HexDumpProxyInitializer(String remoteHost, int remotePort) {
          this.remoteHost = remoteHost;
          this.remotePort = remotePort;
      }
  
      @Override
      public void initChannel(SocketChannel ch) {
          ch.pipeline().addLast(
                  new LoggingHandler(LogLevel.INFO),
                  new HexDumpProxyFrontendHandler(remoteHost, remotePort));
      }
  }
