package com.test;

public class Student1 extends Student {

    static {
        System.out.println("static  ======= son  ");
    }


    public Student1(String name, int age) {
        super(name, age);

        System.out.println("Student1(String name, int age)");
    }

    public Student1() {
        System.out.println("hahh      ========");
    }

    @Override
    public String toString() {
        super.toString();
        return "Student1";
    }

    @Override
    public String toString111() {
        System.out.println(11111111);
        return "Student [name=" + name + ", age=" + age + "]";
    }

}
