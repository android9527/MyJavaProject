package com.leetcode;


import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by chenfeiyue on 2018/8/8.
 * Description: 二叉树操作相关
 */
public class TreeNodeUtil {

    public static void main(String[] args) {

        TreeNode node = createBinTree();
        preOrderTraverse(node);
        System.out.println();
        inOrderTraverse(node);
        System.out.println();
        postOrderTraverse(node);

        levelTraversal(node);

        depthFirstSearch(node);

        System.out.println();
        System.out.println("length = " + dfs(node));
    }

    /**
     * 递归实现，深度优先遍历二叉树
     */
    public static int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.leftChild) + 1;
        int right = dfs(node.rightChild) + 1;
        int length =  left > right ? left : right;
        return length;
    }

    /**
     * 宽度优先遍历
     *
     * @param root
     */
    public static void levelTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        System.out.println(root.data);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                System.out.println(node.data);
                if (node.leftChild != null) {
                    queue.add(node.leftChild);
                }
                if (node.rightChild != null) {
                    queue.add(node.rightChild);
                }
            }
        }
    }

    //深度优先遍历
    public static void depthFirstSearch(TreeNode nodeHead) {
        if (nodeHead == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(nodeHead);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            System.out.print(pop.data + " ");
            if (pop.rightChild != null) {
                stack.push(pop.rightChild);
            }
            if (pop.leftChild != null) {
                stack.push(pop.leftChild);
            }
        }
    }

    /**
     * 1
     * 2          3
     * 4      5     6  7
     * 8   9
     */
    public static TreeNode createBinTree() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        LinkedList<TreeNode> nodeList = new LinkedList<TreeNode>();
        // 将一个数组的值依次转换为Node节点
        for (int nodeIndex = 0; nodeIndex < array.length; nodeIndex++) {
            nodeList.add(new TreeNode(array[nodeIndex]));
        }
        // 对前lastParentIndex-1个父节点按照父节点与孩子节点的数字关系建立二叉树
        for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {
            // 左孩子
            nodeList.get(parentIndex).leftChild = nodeList
                    .get(parentIndex * 2 + 1);
            // 右孩子
            nodeList.get(parentIndex).rightChild = nodeList
                    .get(parentIndex * 2 + 2);
        }
        // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
        int lastParentIndex = array.length / 2 - 1;
        // 左孩子
        nodeList.get(lastParentIndex).leftChild = nodeList
                .get(lastParentIndex * 2 + 1);
        // 右孩子,如果数组的长度为奇数才建立右孩子
        if (array.length % 2 == 1) {
            nodeList.get(lastParentIndex).rightChild = nodeList
                    .get(lastParentIndex * 2 + 2);
        }

        return nodeList.get(0);
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


    /**
     * 先序遍历
     * <p>
     * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已
     *
     * @param node 遍历的节点
     */
    public static void preOrderTraverse(TreeNode node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrderTraverse(node.leftChild);
        preOrderTraverse(node.rightChild);
    }

    /**
     * 中序遍历
     * <p>
     * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已
     *
     * @param node 遍历的节点
     */
    public static void inOrderTraverse(TreeNode node) {
        if (node == null)
            return;
        inOrderTraverse(node.leftChild);
        System.out.print(node.data + " ");
        inOrderTraverse(node.rightChild);
    }

    /**
     * 后序遍历
     * <p>
     * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已
     *
     * @param node 遍历的节点
     */
    public static void postOrderTraverse(TreeNode node) {
        if (node == null)
            return;
        postOrderTraverse(node.leftChild);
        postOrderTraverse(node.rightChild);
        System.out.print(node.data + " ");
    }

    static class TreeNode {
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
