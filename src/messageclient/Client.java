/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;
import java.util.List;

public class Client {

    private final String host;
    private final int port;
    private EchoClientHandler echo = null;
    private List<String> listServ;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                          .remoteAddress(new InetSocketAddress(host, port))
                          .handler(new ChannelInitializer<SocketChannel>() {

                              @Override
                              public void initChannel(SocketChannel ch) throws Exception {
                                  echo =new EchoClientHandler();
                                  ch.pipeline().addLast(echo);
                              }
                          });

            ChannelFuture f = b.connect().sync();

            f.channel().closeFuture().sync();
            listServ = echo.getListServIp();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public List<String> getListIp(){
        return listServ;
    }

}
