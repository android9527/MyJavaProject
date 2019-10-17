package com.algorithm;

import java.util.Stack;

/**
 * Created by chenfeiyue on 2019/7/15.
 * Description:
 */
public class StackQueue {

    private static Stack<String> stack1 = new Stack<>(), stack2 = new Stack<>();

    private static void appendTail(String tail) {
        stack1.push(tail);
    }

    private static String deleteHead() {

        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();
    }

    public static void main(String[] args) {
        appendTail("111");
        appendTail("222");
        appendTail("333");
        appendTail("444");
        System.out.println(deleteHead());
        System.out.println(deleteHead());
        System.out.println(deleteHead());
        appendTail("555");
        appendTail("666");
        System.out.println(deleteHead());
    }
}
