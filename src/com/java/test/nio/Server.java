package com.java.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {

        Selector selector = null;
        ServerSocketChannel ssc = null;
        try {
            selector = Selector.open(); //实例化selector
            ssc = ServerSocketChannel.open(); //实例化ServerSocketChannel 对象

            ssc.socket().bind(new InetSocketAddress(9527)); //绑定端口为1987

            ssc.configureBlocking(false); //设置为非阻塞模式
            ssc.register(selector, SelectionKey.OP_ACCEPT); //注册关心的事件，对于Server来说主要是accpet了

            while (true) {
                int n = selector.select(); //获取感兴趣的selector数量
                if (n < 1)
                    continue; //如果没有则一直轮训检查
                Iterator<SelectionKey> it = selector.selectedKeys().iterator(); //有新的链接，我们返回一个SelectionKey集合
                while (it.hasNext()) {
                    SelectionKey key = it.next(); //使用迭代器遍历
                    it.remove(); //删除迭代器

                    if (key.isAcceptable()) { //如果是我们注册的OP_ACCEPT事件
                        ServerSocketChannel ssc2 = (ServerSocketChannel) key.channel();
                        SocketChannel channel = ssc2.accept();
                        channel.configureBlocking(false); //同样是非阻塞
                        channel.register(selector, SelectionKey.OP_READ); //本次注册的是read事件，即receive接受

                        System.out.println("CWJ Client :" + channel.socket().getInetAddress().getHostName() + ":" + channel.socket().getPort());
                    } else if (key.isReadable()) { //如果为读事件
                        try {
                            SocketChannel channel = (SocketChannel) key.channel();

                            ByteBuffer buffer = ByteBuffer.allocate(1024); //1KB的缓冲区
                            channel.read(buffer); //读取到缓冲区
                            buffer.flip(); //准备写入
                            System.out.println("android123 receive info:" + decode(buffer));
                            channel.write(ByteBuffer.wrap("it works".getBytes())); //返回给客户端
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (selector != null)
                    selector.close();
                if (ssc != null)
                    ssc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String decode(ByteBuffer buffer) {
        System.out.println(" buffer= " + buffer);
        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;
        try {
            charset = Charset.forName(Charset.defaultCharset().toString());
            decoder = charset.newDecoder();
            charBuffer = decoder.decode(buffer);
            System.out.println(" charBuffer= " + charBuffer);
            System.out.println(charBuffer.toString());
            return charBuffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}