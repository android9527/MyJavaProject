package com.algorithm;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by chenfeiyue on 2019/2/18.
 * Description:
 */
public class LinkedTest {

    public static void main(String[] args) {


        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

        for (ThreadInfo threadInfo :threadInfos) {
            System.out.println(threadInfo.getThreadId() + "  " + threadInfo.getThreadName());
        }


        float f = 0x7F800001;
        System.out.println(f);


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

    }


    public static void printNode(Node head) {
        while (head != null) {
            System.out.print(head.getValue() + "   ");
            head = head.getNode();
        }
        System.out.println();
    }

    public static Node createLinkedList(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        Node firstNode = new Node(list.get(0));

        Node headOfSublist = createLinkedList(list.subList(1, list.size()));
        firstNode.setNode(headOfSublist);
        return firstNode;
    }

    public static class Node {
        private final int value;
        private Node node;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }
    }
}
