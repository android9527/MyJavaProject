package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by chenfeiyue on 2018/12/3.
 * Description:
 */
//public class Singleton {
//    private static Singleton mInstance;
//    /**
//     * 构造函数私有化
//     */
//    private Singleton() {
//
//    }
//    public static synchronized Singleton getInstance() {
//        if (mInstance == null) {
//            mInstance = new Singleton();
//        }
//        return mInstance;
//    }
//}

//public class Singleton {
//    private static Singleton mInstance;
//    /**
//     * 构造函数私有化
//     */
//    private Singleton() {
//    }
//    public static Singleton getInstance() {
//        if (mInstance == null) {
//            synchronized (Singleton.class) {
//                if (mInstance == null) {
//                    mInstance = new Singleton();
//                }
//            }
//        }
//        return mInstance;
//    }
//}

public class Singleton {
    private Singleton() {
    }

    public static Singleton getInstance() {
        return SingletonHolder.mInstance;
    }

    private static class SingletonHolder {
        private static final Singleton mInstance = new Singleton();
    }
}


enum SingletonEnum {
    INSTANCE;
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(i, i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{i, map.get(complement)};
            }
        }

        return null;
    }

    /**
     * 时间复杂度：O(n)， 我们只遍历了包含有 n 个元素的列表一次。在表中进行的每次查找只花费 O(1) 的时间。
     * <p>
     * 空间复杂度：O(n)， 所需的额外空间取决于哈希表中存储的元素数量，该表最多需要存储 n 个元素。
     *
     * @param nums   nums
     * @param target target
     * @return int[]
     */
    public int[] twoSum1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    class ListNode {
        int data;
        ListNode next;
    }


//    /**
//     * 链表相加再反转
//     * @param l1
//     * @param l2
//     * @return
//     */
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//
//    }

    /**
     * 归并排序，合并两个有序数组
     *
     * @param nums1 nums1
     * @param m     m
     * @param nums2 nums2
     * @param n     n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m];
        for (int t = 0; t < m; t++) {
            temp[t] = nums1[t];
        }

        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (temp[i] < nums2[j]) {
                nums1[k++] = temp[i++];
            } else {
                nums1[k++] = nums2[j++];
            }
        }

        // 补充左边
        while (i < m) {
            nums1[k++] = temp[i++];
        }

        // 补充右边
        while (j < n) {
            nums1[k++] = nums2[j++];
        }
    }

    /**
     * 两个数组的交集
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int aNums1 : nums1) {
            for (int aNums2 : nums2) {
                if (aNums1 == aNums2) {
                    list.add(aNums2);
                }
            }
        }

        int size = list.size();
        int[] result = new int[size];
        int i = 0;
        for (Integer aList : list) {
            result[i++] = aList;
        }
        return result;
    }


    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 5};
        int[] b = new int[]{1, 2, 4, 5};
        intersect(a, b);
    }
}

class LRUCache {


    LinkedHashMap<Integer, Integer> mMap;

    public LRUCache(int capacity) {
        mMap = new LinkedHashMap<>(0, 0.75f, true);
    }

    public int get(int key) {
        return mMap.get(key);
    }

    public void put(int key, int value) {
        mMap.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */