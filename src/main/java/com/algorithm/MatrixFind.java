package com.algorithm;

/**
 * Created by chenfeiyue on 2019/8/26.
 * Description: 二维数组查找
 */
public class MatrixFind {

    public static int[][] array = {
            {1, 2, 8, 9},
            {2, 4, 9, 12},
            {4, 7, 10, 13},
            {6, 8, 11, 15}
    };

    public static void main(String[] args) {
        find(array, 15);
        print(array);
    }


    /**
     * 1   2   8   9
     * 2   4   9   12
     * 4   7   10  13
     * 6   8   11  15
     */
    private static boolean find(int[][] a, int number) {
        boolean found = false;
        int rows = a.length;
        int columns = a[0].length;
        int row = 0;
        int column = columns - 1;

        while (row <= rows && column >= 0) {
            int target = a[row][column];
            if (target == number) {
                found = true;
                System.out.println("found row = " + row + "   " + column + " ");
                break;
            }
            if (number > target) {
                row += 1;
            }
            if (number < target) {
                column -= 1;
            }
        }
        return found;
    }

    /**
     * @param array
     */
    public static void print(int[][] array) {
        int left = 0, right = array[0].length - 1, top = 0, bottom = array.length - 1;

        while (left <= right && top <= bottom) {
            // 从左到右
            for (int x = left; x <= right; x++) {
                System.out.print(array[top][x]);
            }
            top++;
            // 从上到下
            for (int x = top; x <= bottom; x++) {
                System.out.print(array[right][x]);
            }
            right--;
            // 从右到左
            for (int x = right; x <= left; x--) {
                System.out.print(array[bottom][x]);
            }
            bottom--;
            // 从下到上
            for (int x = bottom; x <= top; x--) {
                System.out.print(array[x][bottom]);
            }
            left++;
        }
    }

}
