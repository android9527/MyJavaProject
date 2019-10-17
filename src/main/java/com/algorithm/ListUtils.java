package com.algorithm;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    /**
     * 分割List
     *
     * @param list     待分割的list
     * @param pageSize 每段list的大小
     */
    public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        int size = list.size();
        int requestNum = size % pageSize == 0 ? size / pageSize : size / pageSize + 1;
        List<List<T>> listArray = new ArrayList<List<T>>(requestNum);
        if (requestNum == 1) {
            listArray.add(list);
            return listArray;
        }
        // 大于一页
        for (int i = 0; i < requestNum; i++) {
            // 最后一页
            if (i == requestNum - 1) {
                listArray.add(list.subList(i * pageSize, size));
            } else {
                listArray.add(list.subList(i * pageSize, (i + 1) * pageSize));
            }
        }
        return listArray;
    }

    public static void main(String[] s) {


        List<String> list = new ArrayList<String>(1000000);
        for (int i = 0; i < 1000000; i++) {
            list.add(i + "");
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
/**
 * 方法二：数组长度提取出来
 * <p>
 * 优点：不必每次都计算
 * <p>
 * 缺点：1、m的作用域不够小，违反了最小作用域原则 2、不能在for循环中操作list的大小，比如除去或新加一个元素
 */
//        long start = System.currentTimeMillis();
//        int m = list.size();
//        for (int i = 0; i < m; i++) {
//            System.out.println(list.get(i));
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);



//        System.out.println("6 Eu Tong Sen Street 06-01 Soho at Clarke Quay Central Singapore 059817".length());
//
//        List<String> strlist = new ArrayList<String>();
//        for (int i = 0; i < 120; i++) {
//            strlist.add("aa" + (i + 1));
//        }
//        List<List<String>> list = splitList(strlist, 17);
//
//        int index = 1;
//        for (List<String> strlist2 : list) {
//            System.out.println(index++);
//            System.out.println("----------------------------------");
//            for (String str : strlist2) {
//                System.out.print(str + "\t");
//            }
//            System.out.println("END");
//            System.out.println();
//        }
    }


}