package com.test.leetcode;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenfeiyue on 17/5/23.
 */
public class Question1 {


    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     * <p>
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * <p>
     * Example:
     * Given nums = [2, 7, 11, 15], target = 9,
     * <p>
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     */
    public static void main(String args[]) {
        int[] a = {1, 3, 5, 6, 6};
        int[] result = test(a, 7);
        result = test2(a, 7);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }

        List<String> list = new ArrayList<String>();
        list.add("");
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("");
        String s = "hello" + " world";
        String str2 = "This is";
        String str3 = " a ";
        String str4 = " test";
        String str1 = str2 + str3 + str4;


        for (int x : a) {
            System.out.println(x);
        }

        String s1 = "";
        for (String ss : list2) {
            System.out.println(ss);
            s1 += ss;
        }
        System.out.println(s1);


        test3();
        add();
    }

    public static int[] test(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] == target - a[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static int[] test2(int[] a, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int b = -1;
        for (int i = 0; i < a.length; i++) {
            int complement = target - a[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(a[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    static ReferenceQueue<String> refQueue = new ReferenceQueue<String>();

    static Reference<String> myReference;

    static void test3() {
        new Thread() {

            @Override
            public void run() {
                super.run();
                while (myReference == null) {
                    myReference = (Reference<String>) refQueue.poll();
                    if (myReference != null) {
                        System.out.println("Check Phantom queue:" + myReference.get());
                        break;
                    }
                }

            }
        }.start();
    }


    private static void add() {
        String str = "string";
        //reference will be stored in this queue for cleanup
        PhantomReference<String> phantom = new PhantomReference<String>(str, refQueue);
        phantom.get();
        str += str;
        System.out.println(str);
    }
}
