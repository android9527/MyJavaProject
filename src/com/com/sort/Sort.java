package com.com.sort;

public class Sort {

    static Integer a[] = {
            49, 38, 65, 97, 78, 34, 12, 64, 5
    };

    public static void insert_Sort() {

        for (int i = 1; i < a.length; i++) {
            int j;
            int temp = a[i];
            for (j = i - 1; j >= 0 && temp < a[j]; j--) {
                a[j + 1] = a[j];                       //将大于temp的值整体后移一个单位
            }
            a[j + 1] = temp;

            System.out.println("第" + i + "次排序");
            for (int x = 0; x < a.length; x++)
                System.out.print(a[x] + " ");
            System.out.println();
        }


        System.out.println();
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
    }


    public static void main(String args[]) {
        printArray(a);
//        insert_Sort();

//        selectSort(a);
//        selectSort2(a);
        quickSort(a, 0, a.length -1 );

        printArray(a);
    }


    /**
     * 找出最小元素存放在数组头
     */
    public static void selectSort(Integer[] numbers) {

        /**
         *
         * 第1次排序
         5 38 65 97 78 34 12 64 49
         第2次排序
         5 12 65 97 78 34 38 64 49
         第3次排序
         5 12 34 97 78 65 38 64 49
         第4次排序
         5 12 34 38 78 65 97 64 49
         第5次排序
         5 12 34 38 49 65 97 64 78
         第6次排序
         5 12 34 38 49 64 97 65 78
         第7次排序
         5 12 34 38 49 64 65 97 78
         第8次排序
         5 12 34 38 49 64 65 78 97
         第9次排序
         5 12 34 38 49 64 65 78 97
         *
         */

        int size = numbers.length, temp;
        for (int i = 0; i < size; i++) {
            int k = i;
            for (int j = size - 1; j >i; j--)  {
                if (numbers[j] < numbers[k])  k = j;
            }
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;

            System.out.println("第" + (i + 1) + "次排序");
            for (int x = 0; x < a.length; x++)
                System.out.print(a[x] + " ");
            System.out.println();
        }
    }

    /**
     * 冒泡排序
     *
     * @param a
     * @Date 2015-4-27 下午4:27:25
     */
    private static void bubbleSort(Integer[] a) {

        /**
         *
         * 第1趟排序
         38  49  65  78  34  12  64  5  97
         第2趟排序
         38  49  65  34  12  64  5  78  97
         第3趟排序
         38  49  34  12  64  5  65  78  97
         第4趟排序
         38  34  12  49  5  64  65  78  97
         第5趟排序
         34  12  38  5  49  64  65  78  97
         第6趟排序
         12  34  5  38  49  64  65  78  97
         第7趟排序
         12  5  34  38  49  64  65  78  97
         第8趟排序
         5  12  34  38  49  64  65  78  97
         *
         */
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }

            System.out.println("第" + (i + 1) + "趟排序");
            printArray(a);
        }
    }

    private static <T> void printArray(T[] a) {
        for (Object o : a) {
            System.out.print(o + "  ");
        }
        System.out.println();
    }


    private static void selectSort2(Integer[] data){
        for (int i = 0; i < data.length; i++)
        {
            int index = i;
            for (int j = data.length - 1; j > i; j--)
            {
                if (data[j] < data[index])
                {
                    index = j;
                }
            }

            swap(data, i, index);
            System.out.println("第" + (i + 1) + "趟排序");
            printArray(a);
        }
    }

    private static void swap(Integer[] data, int a, int b)
    {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }


    /**
     * 快速排序
     * @param a
     * @param start
     * @param end
     */
    public static void quickSort(Integer a[], int start, int end) {
        int i, j;
        i = start;
        j = end;
        if ((a == null) || (a.length == 0))
            return;

        while (i < j) {//查找基准点下标
            while (i < j && a[i] <= a[j])
                // 以数组start下标的数据为key，右侧扫描
                j--;
            if (i < j) { // 右侧扫描，找出第一个比key小的，交换位置
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
            while (i < j && a[i] < a[j])
                // 左侧扫描（此时a[j]中存储着key值）
                i++;
            if (i < j) { // 找出第一个比key大的，交换位置
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        if (i - start > 1) { // 递归调用，把key前面的完成排序
            quickSort(a, 0, i - 1);
        }
        if (end - j > 1) {
            quickSort(a, j + 1, end); // 递归调用，把key后面的完成排序
        }
    }


    //归并排序
    public static void mergeSort(int[] numbers, int left, int right) {
        int t = 1;// 每组元素个数
        int size = right - left + 1;
        while (t < size) {
            int s = t;// 本次循环每组元素个数
            t = 2 * s;
            int i = left;
            while (i + (t - 1) < size) {
                merge(numbers, i, i + (s - 1), i + (t - 1));
                i += t;
            }
            if (i + (s - 1) < right)
                merge(numbers, i, i + (s - 1), right);
        }
    }
    //归并算法实现
    private static void merge(int[] data, int p, int q, int r) {
        int[] B = new int[data.length];
        int s = p;
        int t = q + 1;
        int k = p;
        while (s <= q && t <= r) {
            if (data[s] <= data[t]) {
                B[k] = data[s];
                s++;
            } else {
                B[k] = data[t];
                t++;
            }
            k++;
        }
        if (s == q + 1)
            B[k++] = data[t++];
        else
            B[k++] = data[s++];
        for (int i = p; i <= r; i++)
            data[i] = B[i];
    }

}