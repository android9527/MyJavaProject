package com.test.serializable;

public class Demo {
    public Object instance = null;

    public static void main(String[] args) {
        Demo demo1 = new Demo();
        Demo demo2 = new Demo();
        demo1.instance = demo2;
        demo2.instance = demo1;
        //现在demo1,demo2设置null,那么demo1,demo2能否被回收呢？
        demo1 = null;
        demo2 = null;
        //手动的调用垃圾回收器回收
        System.gc();
    }
}