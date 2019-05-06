package com.test.test;

import java.util.ArrayList;
import java.util.Map;

public class Child extends Parent {
//    static {
//        System.out.println("Child's static code block");
//    }
//
//    static Foo FOO = new Foo("Child's static parameter");
//
//    Foo foo = new Foo("Child's parameter");
//
//    {
//        System.out.println("Child's code block");
//    }

    public Child() {
        super();
        System.out.println("Child.Child()");
        init();
    }

    public Child(String name) {
        super(name);
        this.name = name;
        System.out.println("Child.Child() name " + name);
        init();
    }

    public Child(String name, int age) {
        super(name, age);
        this.name = name;
        System.out.println("Child.Child() name age");
        init();
    }

    void init(){
        System.out.println("init");
        System.out.println(this);
    }

    ArrayList<String> list = new ArrayList<>();
    void test() {
        list.add("1");
        list.add("2");
        ArrayList<String> temp = list;
        list = new ArrayList<>();
        System.out.println(temp.size());
        System.out.println(list.size());
    }


    public static void main(String[] args) {
        Child child = new Child("zhangsan", 10);
        child.test();
    }
}