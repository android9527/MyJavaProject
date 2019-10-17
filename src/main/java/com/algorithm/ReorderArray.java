package com.algorithm;

import java.util.ArrayList;

/**
 * Created by chenfeiyue on 2019/9/2.
 * Description:
 */
public class ReorderArray {
    public static void main(String[] args) {

        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add(1, "2");
        strings.add(2, "3");
        System.out.println(strings.size());

        int array[] = {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        oddEvenSort(array);
        printArray(array);
    }

    /**
     * 交换一个数组中的奇偶数，奇数在前，偶数在后
     * 奇数偶数排序  时间复杂度O(N)
     */
    public static void oddEvenSort(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            //high-- 方向  扫描第一个奇数要进行交换
            if (isOdd(arr[high]) && low < high) {
                high--;
                //low++ 方向  扫描第一个偶数要进行交换
            } else if ((isEven(arr[low])) && low < high) {
                low++;
            } else {
                //执行交换操作
                swap(arr, low, high);
            }
        }
    }

    //偶数判断器
    public static boolean isOdd(int n) {
        if (n % 2 == 0) {
            return true;
        }
        return false;
    }

    //奇数判断器
    public static boolean isEven(int n) {
        if (n % 2 == 1) {
            return true;
        }
        return false;
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i ++) {
            System.out.print(array[i] + " ");
        }
    }
}
