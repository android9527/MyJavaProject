package com.test.traditionthread;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * Created by chenfeiyue on 2018/4/23.
 * Description:
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class TwoThreadTest {

//    final Lock lock1 = new ReentrantLock();
//    final Lock lock2 = new ReentrantLock();


    final Object object = new Object();

    int state = 1;


    public static void main(String[] args) {

//        String regularTerm = "1月(30天)";
//        String regularTerm = "(30天)";
        String regularTerm = "(365天)";
        String days = regularTerm.substring(1, regularTerm.length() - 2);
        System.out.println(days);
//        BigDecimal bd = new BigDecimal("aaa");
//        TwoThreadTest test = new TwoThreadTest();
//        test.thread1();
//        test.thread2();


//        for (int i = 0; i < 100; i++) {
//            new Thread() {
//                @Override
//                public void run() {
//                    super.run();
//                    test.testSynchronized();
//                }
//            }.start();
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
    }

    static long time = System.currentTimeMillis();

    public static synchronized void testSynchronized() {

        for (int i = 0; i < Short.MAX_VALUE; i++) {
            System.out.print(i);
        }
        System.out.println(Thread.currentThread().getName() + " time = " + (System.currentTimeMillis() - time));
    }


    int i = 0;

    void thread1() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (i < 100) {
                    synchronized (object) {
                        if (state == 1) {
                            System.out.println("--------" + i + "   " + getName());
                            i++;
                            state = 2;
                            object.notify();
                        } else {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }.start();
    }

    void thread2() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (i < 100) {
                    synchronized (object) {
                        if (state == 2) {
                            System.out.println("--------" + i + "   " + getName());
                            i++;
                            state = 1;
                            object.notify();
                        } else {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        }.start();
    }

}
