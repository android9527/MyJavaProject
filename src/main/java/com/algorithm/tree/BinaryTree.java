package com.algorithm.tree;

import java.math.BigDecimal;

public class BinaryTree {
    /** 
     * @author yaobo
     * 二叉树的先序中序后序排序
     *
     *                 6
     *           3                 9
     *      1          5      7
     *         2     4          8
     */

    //注意必须逆序建立，先建立子节点，再逆序往上建立，因为非叶子结点会使用到下面的节点，而初始化是按顺序初始化的，不逆序建立会报错
    public Node init() {
        Node J = new Node(8, null, null);  
        Node H = new Node(4, null, null);  
        Node G = new Node(2, null, null);  
        Node F = new Node(7, null, J);  
        Node E = new Node(5, H, null);  
        Node D = new Node(1, null, G);  
        Node C = new Node(9, F, null);  
        Node B = new Node(3, D, E);  
        Node A = new Node(6, B, C);  
        return A;   //返回根节点  
    }
    
    public void printNode(Node node){  
        System.out.print(node.getData());  
    }  
    public void theFirstTraversal(Node root) {  //先序遍历  
        printNode(root);  
        if (root.getLeftNode() != null) {  //使用递归进行遍历左孩子  
            theFirstTraversal(root.getLeftNode());  
        }  
        if (root.getRightNode() != null) {  //递归遍历右孩子  
            theFirstTraversal(root.getRightNode());  
        }  
    }  
    public void theInOrderTraversal(Node root) {  //中序遍历  
        if (root.getLeftNode() != null) {  
            theInOrderTraversal(root.getLeftNode());  
        }  
        printNode(root);  
        if (root.getRightNode() != null) {  
            theInOrderTraversal(root.getRightNode());  
        }  
    }
    
    
    public void thePostOrderTraversal(Node root) {  //后序遍历  
        if (root.getLeftNode() != null) {  
            thePostOrderTraversal(root.getLeftNode());  
        }  
        if(root.getRightNode() != null) {  
            thePostOrderTraversal(root.getRightNode());  
        }  
        printNode(root);  
    }

    public int getDepth(Node node){
        if(node == null){
            return 0;
        }

        int left = getDepth(node.getLeftNode());
        int right = getDepth(node.getRightNode());
        return left > right ? left + 1 : right + 1;
    }
      
    public static void main(String[] args) {
        float d1 = 1.10f;
        double d2 = 1.1;

        long i = Float.floatToIntBits(d1);
        long i2 = Double.doubleToLongBits(d2);
        System.out.println(i == i2);
        BigDecimal decimal1 = new BigDecimal(d1);
        BigDecimal decimal2 = new BigDecimal(d2);
        System.out.println(decimal1.compareTo(decimal2) == 0);

        BinaryTree tree = new BinaryTree();  
        Node root = tree.init();  
        System.out.println("先序遍历");  
        tree.theFirstTraversal(root);  
        System.out.println("");  
        System.out.println("中序遍历");  
        tree.theInOrderTraversal(root);  
        System.out.println("");  
        System.out.println("后序遍历");  
        tree.thePostOrderTraversal(root);  
        System.out.println("二叉树深度");
        System.out.println(tree.getDepth(root));
    }

    static void createTree() {

    }
}