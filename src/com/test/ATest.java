package com.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by chenfeiyue on 16/8/24.
 */
public class ATest {
    public static void main(String args[]) {
        System.out.println(3 >> 1);
        System.out.println(-1 >>> 1);

        System.out.println((Math.pow(2, 31) -1) ==Integer.MAX_VALUE);


        ArrayBlockingQueue<String> strings = new ArrayBlockingQueue<String>(2);
        try {
            strings.put("a");
            strings.put("b");
            strings.put("c");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
