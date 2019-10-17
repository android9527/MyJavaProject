package com.algorithm;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by chenfeiyue on 2019/2/18.
 * Description:
 */
public class LinkedTest {

    public static void main(String[] args) {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("111", "111");
        map.put("123", "123");
        map.put("aaa", "aaa");
        map.put("abc", "anc");
        map.put("222", "222");
        map.put("223", "223");

        for (Object o : map.keySet()) {
            //it.next()得到的是key，tm.get(key)得到obj
            System.out.println(map.get(o));
        }

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Node head = createLinkedList(list);
        printNode(head);


        Node node = reverseLinkedList(head);
        printNode(node);
    }


    public static void printNode(Node head) {
        while (head != null) {
            System.out.print(head.getValue() + "   ");
            head = head.getNext();
        }
        System.out.println();
    }

    public static Node createLinkedList(List<Integer> list) {
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
            stack.push(node.getValue());
            node = node.getNext();
        }

        while (!stack.isEmpty()) {
            Integer integer = stack.pop();
            System.out.println(integer);
        }
    }

    /**
     * 递归反转链表
     */
    public static Node reverseLinkedList(Node node) {
        if (node == null || node.getNext() == null) {
            return node;
        }
        Node newHead = reverseLinkedList(node.getNext());
        node.getNext().setNext(node);
        node.setNext(null);
        return newHead;
    }

    public static class Node {
        private final int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node node) {
            this.next = node;
        }
    }
}
