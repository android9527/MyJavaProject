package com.test.test;

/**
 * Created by chenfeiyue on 2019/1/24.
 * Description:
 */
public class Singleton {
    private static Singleton instance = null;
    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    //非原子操作
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
