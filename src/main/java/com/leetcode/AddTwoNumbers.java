package com.leetcode;

/**
 * Created by chenfeiyue on 17/6/1.
 * AddTwoNumbers
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * <p>
 * Subscribe to see which companies asked this question.
 */
public class AddTwoNumbers {

    public static void main(String[] args) {

        int a = Short.MAX_VALUE + 1;
        int b = Short.MIN_VALUE - 1;
        System.out.println((short) a);
        System.out.println((short) b);

        int s = 0xefff;
        System.out.println(s);
        System.out.println(test());

        Integer i = 1;
        Class clz = i.getClass();
    }

    static int test(){
        try{
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return 2;
        }
    }
}
