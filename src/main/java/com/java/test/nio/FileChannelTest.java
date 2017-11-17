package com.java.test.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by chenfeiyue on 16/8/18.
 */
public class FileChannelTest {
    public static void main(String args[]) {
        readFile();
    }

    private static void readFile() {
        try {
            RandomAccessFile aFile = new RandomAccessFile("111.text", "rw");
            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {
                System.out.println("Read " + bytesRead);
                buf.flip();

//                while (buf.hasRemaining()) {
//                    System.out.print(decode(buf));
//                }
                System.out.print(decode(buf));
                buf.clear();
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String decode(ByteBuffer buffer) {
        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;
        try {
            charset = Charset.forName("UTF-8");
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
