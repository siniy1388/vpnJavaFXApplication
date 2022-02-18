/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import java.util.ArrayList;
import java.util.List;

@Sharable 
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private static List<String> listIpServ;

    public void channelActive(ChannelHandlerContext ctx) {
               ctx.writeAndFlush(Unpooled.copiedBuffer("servers", CharsetUtil.UTF_8));

    }

    @SuppressWarnings("empty-statement")
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        System.out.println(
                "Client received: " + in.toString(CharsetUtil.UTF_8));
       parseStr(in.toString(CharsetUtil.UTF_8));
       
       ctx.close();


    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    // Список серверов из строки полученной с сервера ЭХО
    // преобразуем в массив LIST
    private void parseStr(String s){
        listIpServ = new ArrayList<String>();
        String ts="";
        for (int i=s.indexOf(":")+1;i<=s.length()-1;i++){
            if ((s.charAt(i) != ',')&&(s.charAt(i) != ';')) {
                ts=ts+s.charAt(i);
            } else {
                if (ts.length()>0){
                    listIpServ.add(ts);
                    ts="";
            }
            }
        }
    }
    // Получаем список серверов
    public List<String> getListServIp (){
        return listIpServ;
    }    
}