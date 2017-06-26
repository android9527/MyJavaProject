package com.test.traditionthread;

/**
 * Created by chenfeiyue on 16/11/1.
 */
public class SyncTest {

    public static void main(String[] args) {
        testSync();
    }

    private synchronized static void testSync() {
        System.out.println("----------");
        test2();
    }


    private synchronized static void test2() {
        System.out.println("test2");
    }
}
