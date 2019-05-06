package com.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by chenfeiyue on 2019/5/6.
 * Description:
 */
public class PrintListInReversedOrder {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Node head = createLinkedList(list);
        printListRevers(head);
    }

    /**
     * 数组创建链表
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