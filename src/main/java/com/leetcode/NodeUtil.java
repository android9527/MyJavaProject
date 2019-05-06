package com.leetcode;

/**
 * Created by chenfeiyue on 2018/8/8.
 * Description:
 */
public class NodeUtil {

    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
        linkedList.add(3);
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);

        linkedList.printNode(linkedList.getHead());

//        linkedList.deleteNode(1);
        System.out.println();
//        linkedList.printNode(linkedList.getHead());

        Node node1 = new Node(2);
        Node node2 = new Node(1);

        System.out.println();
        linkedList.deleteNodeByData(3);
//        linkedList.deleteNodeByData(5);
        linkedList.printNode(linkedList.getHead());
    }
}

class LinkedList {

    Node head;

    public Node getHead() {
        return head;
    }

    /**
     * 向已知链表中添加结点
     *
     * @param data data
     */
    public void add(int data) {
        Node addNode = new Node(data);

        if (head == null) {
            head = addNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = addNode;
    }

    /**
     * 打印链表
     */
    public void printNode(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node + "  ");
        Node next = node.next;
        printNode(next);
    }


    //方法：获取单链表的长度
    public int getLength() {
        if (head == null) {
            return 0;
        }
        int length = 0;
        Node current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    /**
     * 链表删除结点:
     * 把要删除结点的前结点指向要删除结点的后结点，即直接跳过待删除结点
     *
     * @param index
     * @return
     */
    public boolean deleteNode(int index) {
        //待删除结点不存在
        if (index < 0 || index > getLength() - 1) {
            return false;
        }
        //删除头结点
        if (index == 0) {
            head = head.next;
            return true;
        }
        Node preNode = head;
        Node curNode = preNode.next;
        int i = 1;
        while (curNode != null) {
            //寻找到待删除结点
            if (i == index) {
                //待删除结点的前结点指向待删除结点的后结点
                preNode.next = curNode.next;
                return true;
            }
            //当先结点和前结点同时向后移
            preNode = preNode.next;
            curNode = curNode.next;
            i++;
        }
        return true;
    }

    /**
     * 删除链表中一个结点
     *
     * @param data node  要删除的结点
     */
    public <T> boolean deleteNodeByData(T data) {

        // 删除头部结点
        if (head == null) {
            return false;
        }

        if (head.data == data) {
            head = head.next;
            return false;
        }

        Node preNode = head;
        Node curNode = preNode.next;
        while (curNode != null) {

            if (curNode.data == data) {
                preNode.next = curNode.next;
                return true;
            }
            preNode = preNode.next;
            curNode = curNode.next;
        }
        return false;
    }
}

class Node<T> {
    Node next;
    T data;

    public Node(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data + "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Node) {
            Node other = (Node) obj;
            return data == other.data;
        }

        return false;
    }
}


