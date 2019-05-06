package com.test.traditionthread;

import java.util.concurrent.atomic.AtomicReference;

public class UnreentrantLock {

    private AtomicReference<Thread> owner = new AtomicReference<Thread>();

    public void lock() {
        Thread current = Thread.currentThread();
        //这句是很经典的“自旋”语法，AtomicInteger中也有
        for (; ; ) {
            if (!owner.compareAndSet(null, current)) {
                return;
            }
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        owner.compareAndSet(current, null);
    }

    public static void main(String[] args) {

        UnreentrantLock lock = new UnreentrantLock();
        lock.test();

    }

    void test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock();
                lock();
                System.out.println("end");
            }
        }).start();
    }
}
//---------------------
//        作者：Lovnx
//        来源：CSDN
//        原文：https://blog.csdn.net/rickiyeat/article/details/78314451
//        版权声明：本文为博主原创文章，转载请附上博文链接！
