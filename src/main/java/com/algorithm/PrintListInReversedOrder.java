package com.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by chenfeiyue on 2019/5/6.
 * Description:
 */
public class PrintListInReversedOrder {
    final static Object object = new Object();

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Node head = createLinkedList(list);
//        printListRevers(head);
        printListReversed(head);

        testSleep();
    }

    private static void testSleep() {
        Thread sleepThread = new Thread() {
            @Override
            public void run() {
                super.run();
                synchronized (object) {
                    try {
                        System.out.println("start sleep");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("end sleep");
                }
            }
        };
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                synchronized (object) {

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sleepThread.interrupt();
                    System.out.println("--thread run-------------" + System.currentTimeMillis());
                }
            }
        };
        thread.start();

        sleepThread.start();

        System.out.println("---------------");
    }

    /**
     * 数组创建链表
     *
     * @param list
     * @return
     */
    private static Node createLinkedList(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        Node firstNode = new Node(list.get(0));

        Node headOfSublist = createLinkedList(list.subList(1, list.size()));
        firstNode.setNext(headOfSublist);
        return firstNode;
    }

    /**
     * stack 的方式实现链表逆向打印
     *
     * @param head head
     */
    private static void printListRevers(Node head) {
        if (head == null) {
            return;
        }

        Node node = head;
        Stack<Integer> stack = new Stack<>();
        // 把链表数据加入栈
        while (node != null) {
            stack.push(node.getData());
            node = node.getNext();
        }

        while (!stack.isEmpty()) {
            Integer integer = stack.pop();
            System.out.println(integer);
        }
    }


    /**
     * 递归方式实现链表逆向打印
     *
     * @param head head
     */
    private static void printListReversed(Node head) {
        if (head == null) {
            return;
        }
        if (head.getNext() != null) {
            printListReversed(head.getNext());
        }
        System.out.println(head.getData());
    }

    public static class Node {
        Integer data;
        Node next;

        public Node(Integer data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}