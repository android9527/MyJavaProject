package com.test;

public class Student {
    String name;
    int age;

    static {
        System.out.println("static  ======= father  ");
    }

    public Student() {
        System.out.println("hehe    Student");
    }


    public Student(String name, int age) {
        this.name = name;
        this.age = age;

        System.out.println("super   Student(String name, int age)");
    }

    protected String toString111() {
        System.out.println(11111111);
        return "Student [name=" + name + ", age=" + age + "]";
    }

    @Override
    public String toString() {
        System.out.println(11111111);
        return "Student [name=" + name + ", age=" + age + "]";
    }

    public static String tostring() {
        System.out.println("static     tostring");
        return "Student [name=";
    }
}
