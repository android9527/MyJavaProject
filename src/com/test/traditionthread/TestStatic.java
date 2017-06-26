package com.test.traditionthread;

/**
 * Created by chenfeiyue on 16/9/26.
 */
public class TestStatic {

    static boolean start = true;
    static int i = 0;

    public static void main(String[] args) {
        testStatic();
    }

    private static void testStatic() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                while (start) {
                    i++;
                    System.out.println(i);
                }
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        start = false;
        Thread.yield();
        System.out.println(i);
    }
}
