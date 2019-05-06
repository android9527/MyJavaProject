package com.leetcode;

/**
 * Created by chenfeiyue on 2018/8/10.
 * Description:
 */
public class Singleton {

    private Singleton() {
        System.out.println("--------");
    }

    public static Singleton getInstance() {
        return SingletonHolder.singleton;
    }

    private static final class SingletonHolder {
        private static final Singleton singleton = new Singleton();
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
