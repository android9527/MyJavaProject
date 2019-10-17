package com.algorithm;

/**
 * Created by chenfeiyue on 2019/7/16.
 * Description:
 */
public class MergeTwoList {
    public static void main(String[] args) {

        int[] a = {1, 5, 9};
        int[] b = {2, 4, 6};

        LinkNode nodeA = createLink(a);
//        printLink(nodeA);
        LinkNode nodeB = createLink(b);
//        printLink(nodeB);

        LinkNode merge = merge(nodeA, nodeB);
        printLink(merge);
    }

    private static LinkNode createLink(int[] array) {
        LinkNode node = new LinkNode(array[0]);
        LinkNode curr = node;
        for (int i = 1; i < array.length; i++) {
            LinkNode next = new LinkNode(array[i]);
            curr.next = next;
            curr = next;
        }
        return node;
    }

    private static void printLink(LinkNode node) {
        if (node != null) {

            LinkNode node1 = node;
            System.out.println(node.value);
            while (node1.next != null) {
                LinkNode next = node1.next;
                System.out.println(next.value);
                node1 = next;
            }
        }
    }


    // 遍历解法
    // 同时不断遍历两个链表，取出小的追加到新的头节点后，直至两者其中一个为空
    // 再将另一者追加的新链表最后

    private static LinkNode merge(LinkNode nodeA, LinkNode nodeB) {
        LinkNode result = new LinkNode();
        LinkNode curr = result;
        LinkNode node1 = nodeA, node2 = nodeB;
        while (node1 != null && node2 != null) {
            if (node1.value < node2.value) {
                curr.next = node1;
                node1 = node1.next;
            } else {
                curr.next = node2;
                node2 = node2.next;
            }
            curr = curr.next;
        }
        if (node1 == null) {
            curr.next = node2;
        } else {
            curr.next = node1;
        }
        return result.next;
    }
}

class LinkNode {
    int value;
    LinkNode next;

    LinkNode() {
    }

    LinkNode(int value) {
        this.value = value;
    }
}