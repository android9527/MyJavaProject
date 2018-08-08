package com.test.traditionthread;

import com.okhttp.GetExample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by chenfeiyue on 16/11/1.
 */
public class SyncTest {


    static ExecutorService executor = Executors.newFixedThreadPool(5);
    static FutureTask<String> task;

    public static void main(String[] args) {

        File file = new File("text.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 100; i++) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    appendMethodB(file);
                }
            }.start();
        }

//        testSync();

//        OkHttp3util.initClient();
//        task = new FutureTask<>(new HttpRunnable());
//        executor.submit(task);
    }


    static class HttpRunnable implements Callable<String> {
        @Override
        public String call() throws Exception {
            task.cancel(true);
            System.out.println("-------start --------" + Thread.currentThread().getName());
            new GetExample().run("");
            System.out.println("-------end --------" + Thread.currentThread().getName());
            System.out.println("-------end --------> " + task.isCancelled());
            System.out.println("-------end --------> " + task.isDone());
            return null;
        }


    }


    private synchronized static void testSync() {
        System.out.println("----------");
        test2();
    }


    private synchronized static void test2() {
        System.out.println("test2");
    }


    static String content = "本周周报（7.2-7.6）\n" +
            "\n" +
            "1、4.0.5版本发布4.0.6缺陷修复\n" +
            "2、验证手势修改和设置手势密码修改\n" +
            "3、整理项目删除无用导入，修改部分单例Context leak\n" +
            "4、AutoScrollViewPager Handler reference leaks 修改为静态内部类\n" +
            "5、删除部分未使用图片资源及layout、drawable资源等\n" +
            "6、修改ImageUtil为静态工具类\n" +
            "7、修改部分重复调用代码，如获取屏幕宽高等，不用每次都获取，统一使用\n" +
            "8、增加WebView视频全屏支持，返回按键处理\n" +
            "9、tinker插件依赖修改，区分Debug版本和Release版本\n" +
            "\n" +
            "下周工作计划：\n" +
            "1、4.0.6版本上线\n" +
            "2、线上bug跟踪\n" +
            "3、版本迭代开发\n";

    public static void write(File file) {
        System.out.println("---------->" + Thread.currentThread().getName());
        OutputStream outputStream = null;
        RandomAccessFile randomFile = null;
        try {
            outputStream = new FileOutputStream(file);
            byte[] b = content.getBytes("UTF-8");
//            outputStream.write(b);

            // 打开一个随机访问文件流，按读写方式
            randomFile = new RandomAccessFile(file, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.write(b);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * B方法追加文件：使用FileWriter
     */
    public static void appendMethodB(File file) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(file, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
