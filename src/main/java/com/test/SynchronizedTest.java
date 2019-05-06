package com.test;

import java.util.ArrayList;

/**
 * Created by chenfeiyue on 2019/2/12.
 * Description:
 */
public class SynchronizedTest {


    static final Object OBJECT = new Object();

    public static void main(String[] args) {

        testWait();
        testNotify();

        String s1 =  1 +"";

        String s2 = String.valueOf(1);
        String s3 = String.valueOf(1);

        System.out.println(s3 == s2);
    }


    static void testWait() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                synchronized (OBJECT) {
                    try {
                        System.out.println("--------- wait--------");
                        OBJECT.wait(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("---------after run --------");
            }
        }.start();
    }

    static void testNotify() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                synchronized (OBJECT) {
                    System.out.println("--------- notify--------");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
