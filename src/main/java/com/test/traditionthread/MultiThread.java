package com.test.traditionthread;

import com.test.serializable.People;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by chenfeiyue on 2018/12/20.
 * Description:
 */
public class MultiThread {

    public static void main(String[] args) {
        test("t_hscroll_3");
    }
    public static final String a = "t_hscroll_3";
    static void test(String s) {
        String b = String.valueOf(s);
        System.out.println(a==b);
        People people1 = new People();
        people1.setName(a);
        People people2= new People();
        people2.setName(s);

        System.out.println(people1.getName() == people2.getName());
    }
}