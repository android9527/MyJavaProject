package com.test.traditionthread;

/**
 * Created by chenfeiyue on 16/9/5.
 */
public class WaitTest {

    private static final Object object = new Object();
    private static int count;


    public static void main(String args[]) {
//        testWait();
//        testWait();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
////
////        testSleep();
//
//        synchronized (object) {
//            object.notifyAll();
//        }


        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    testSub();
                }
            }.start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        for (int i = 0; i < 5; i++)
//            new Thread() {
//                @Override
//                public void run() {
//                    super.run();
                    testAdd();
        testAdd();

        testAdd();
//                }
//            }.start();

    }

    static void testSleep() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                synchronized (object) {
                    try {
                        System.out.println("-----------start sleep");
                        Thread.sleep(10000);
                        System.out.println("-----------end sleep");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    static void testWait() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                synchronized (object) {
                    try {
                        System.out.println("-----------start wait");
                        object.wait();
                        System.out.println("-----------end wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }


    static void testAdd() {
        synchronized (object) {
            System.out.println("--- " + Thread.currentThread().getId());
            add();
        }
    }

    static void testSub() {
        synchronized (object) {
            if (count <= 0) {
                try {
                    System.out.println("--- " + Thread.currentThread().getId() + "-----------start wait");
                    object.wait();
                    sub();
                    System.out.println("--- " + Thread.currentThread().getId() + "-----------end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                sub();
            }
        }
    }


    private static void add() {
        count++;
        System.out.println("count = " + count);
        object.notify();
    }

    private static void sub() {
        count--;
        System.out.println("count = " + count);
    }
}
