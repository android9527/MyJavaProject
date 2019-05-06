package com.test.test;

public class Parent {
    protected String name;
    protected int age;
//    static Foo FOO = new Foo("Parent's static parameter");
//    {
//        System.out.println("Parent's code block");
//    }
//    Foo foo = new Foo("Parent's parameter");
//
//    static {
//        System.out.println("Parent's static code block");
//    }
    
    public Parent() {
        System.out.println("Parent.Parent()");
        System.out.println(this);
    }

    public Parent(String name) {
        this(name, 0);
        System.out.println("Parent.Parent(name)" + name);
        System.out.println("Parent(String name)----------------");
    }

    public Parent(String name, int age) {
        this();
        System.out.println("Parent.Parent(name, age)" + name);
        System.out.println("Parent(String name)----------------");
    }
}