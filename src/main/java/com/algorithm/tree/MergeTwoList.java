package com.algorithm.tree;

import java.util.Arrays;

/**
 * Created by chenfeiyue on 2018/9/2.
 * Description:
 */
public class MergeTwoList {

    public static void main(String[] args) {
        int[] a = {3, 6, 8};
        int[] b = {4, 6, 9, 10};
        merge(a, b);
    }

    private static void merge(int[] a, int[] b) {

        int[] result = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k ++] = a[i ++];
            } else {
                result[k ++] = b[j ++];
            }
        }
        /* 后面连个while循环是用来保证两个数组比较完之后剩下的一个数组里的元素能顺利传入 */
        while (i < a.length) {
            result[k++] = a[i++];
        }
        while (j < b.length) {
            result[k++] = b[j++];
        }

        System.out.println(Arrays.toString(result));
    }
}
