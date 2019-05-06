package com.leetcode;


/**
 * Created by chenfeiyue on 2018/8/8.
 * Description: 二叉树操作相关
 */
public class TreeNodeUtil {


    public static void levelTraversal(TreeNode root) {

    }

    //查找节点
    public TreeNode find(TreeNode root, int key) {
        TreeNode current = root;
        while (current != null) {
            //当前值比查找值大，搜索左子树
            if (current.data > key) {
                current = current.leftChild;
                //当前值比查找值小，搜索右子树
            } else if (current.data < key) {
                current = current.rightChild;
            } else {
                return current;
            }
        }
        //遍历完整个树没找到，返回null
        return null;
    }

    //插入节点
    public boolean insert(TreeNode root, int data) {
        TreeNode newNode = new TreeNode(data);
        if (root == null) {//当前树为空树，没有任何节点
            root = newNode;
            return true;
        } else {
            TreeNode current = root;
            TreeNode parentNode = null;
            while (current != null) {
                parentNode = current;
                if (current.data > data) {//当前值比插入值大，搜索左子节点
                    current = current.leftChild;
                    if (current == null) {//左子节点为空，直接将新值插入到该节点
                        parentNode.leftChild = newNode;
                        return true;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {//右子节点为空，直接将新值插入到该节点
                        parentNode.rightChild = newNode;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    class TreeNode {
        //节点数据
        private int data;
        //左子节点的引用
        private TreeNode leftChild;
        //右子节点的引用
        private TreeNode rightChild;

        TreeNode(int data, TreeNode leftChild, TreeNode rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public TreeNode(int data) {
            this.data = data;
        }

        //打印节点内容
        public void display() {
            System.out.println(data);
        }
    }

}
