package com.test;

import org.apache.http.util.TextUtils;

/**
 * Created by chenfeiyue on 2019/7/15.
 * Description:
 */
public class ByteTest {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};

//        display(array);
//        dis(array);



        String version = "8.0";
        String version1 = "7.10";
        String version2 = "7.0100";
        String version3 = "8.0.0";
        String version4 = "8.0.10";
        String version5 = "8.0.1.10";
//        System.out.println(compare(version, version1));
//        System.out.println(compare(version, version2));
//        System.out.println(compare(version, version3));
//        System.out.println(compare(version, version4));
//        System.out.println(compare(version, version5));
        System.out.println(compare(version2, version1));
        System.out.println(Integer.parseInt(""));
        System.out.println(compare(version2, version2));
        System.out.println(compare(version2, version3));
        System.out.println(compare(version2, version4));
        System.out.println(compare(version2, version5));
    }

    public static int compare(String version1, String version2) {
        if (TextUtils.isEmpty(version1) || TextUtils.isEmpty(version2))
        {
            return -1;
        }
        String[] v1 = version1.trim().split("\\.");
        String[] v2 = version2.trim().split("\\.");
        int minLength = Math.min(v1.length, v2.length);
        int index = 0;
        int diff = 0;
        while (index < minLength
                && (diff = v1[index].compareTo(v2[index])) == 0)
        {
            ++index;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，再比较位数；
        diff = (diff != 0) ? diff : v1.length - v2.length;
        return diff;
    }


    public static int getVersion(String version)
    {
        if (version == null)
        {
            return 0;
        }
        // iPhone#7.8.3,aPhone#7.8.6
        if (version.contains("aPhone"))
        {
            String[] split = version.split(",");
            for (String ver : split)
            {
                if (ver != null && ver.contains("aPhone"))
                {
                    String[] aph = ver.split("#");
                    if (aph.length >= 2)
                    {
                        String v = aph[1];
                        String[] v1 = v.split("\\.");
                        int result = parseInt(v1[0]) * 1000000 + parseInt(v1[1]) * 10000;
                        if (v1.length == 3)
                        {
                            result = result + parseInt(v1[2]) * 100;
                        } else if (v1.length == 4)
                        {
                            result = result + parseInt(v1[2]) * 100 + parseInt(v1[3]);
                        }
                        return result;
                    }
                }
            }
        }
        return 0;
    }

    public static int parseInt(String s){
        return parseInt(s, 0);
    }
    public static int parseInt(String s, int defaultInt)
    {
        try
        {
            return Integer.parseInt(s);
        }
        catch (Exception ex)
        {
            // LogUtils.error(ex.getStackTrace().toString());
        }
        return defaultInt;
    }

    static void printByte(int[][] a) {
        if (a == null) {
            return;
        }
//        for() {
//
//        }

        System.out.println(a.length);

        int row = a.length;
        int column = a[0].length;
        System.out.println("row = " + row + "  column");
    }

    public static int[][] array = {
            {1, 2, 3, 4, 5},
            {15, 24, 25, 20, 7},
            {14, 23, 22, 21, 8},
            {13, 12, 11, 10, 9},
            {13, 12, 11, 10, 9},
            {13, 12, 11, 10, 9},
    };

    public static void println(Object object) {
        System.out.println(object);
    }

    public static void dis(int[][] array) {
        if (array == null || array.length == 0) {
            return;
        }

        int left = 0;
        int right = array[0].length - 1;
        int top = 0;
        int bottom = array.length - 1;
        while (left <= right && top <= bottom) {
            // 从左到右遍历
            for (int x = left; x <= right; x++) {
                println(array[top][x]);
            }
            top++;

            // 从上到下
            for (int y = top; y <= bottom; y++) {
                println(array[y][right]);
            }
            right--;
            // 从右到左
            for (int x = right; x >= left; x--) {
                println(array[bottom][x]);
            }
            bottom--;

            // 从下到上
            for (int y = bottom; y >= top; y--) {
                println(array[y][left]);
            }
            left++;
        }


    }


    public static void display(int[][] array) {

        int left = 0, right = array[0].length - 1, top = 0, bottom = array.length - 1;
        while (left <= right && top <= bottom) {

            // 1. 从Left到Right遍历
            for (int x = left; x <= right; x++) {
                println(array[top][x]);
            }
            top++;

            // 2. 从Top到Bottom遍历
            for (int y = top; y <= bottom; y++) {
                println(array[y][right]);
            }
            right--;

            // 3. 从Right到Left遍历
            for (int x = right; x >= left; x--) {
                println(array[bottom][x]);
            }
            bottom--;

            // 4. 从Bottom到Top遍历
            for (int y = bottom; y >= top; y--) {
                println(array[y][left]);
            }
            left++;
        }
    }
}
