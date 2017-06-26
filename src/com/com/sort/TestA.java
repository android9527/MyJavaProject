package com.com.sort;

/**
 * Created by chenfeiyue on 16/12/12.
 */
class TestA {

    int b[] = {a, 1};

    public static int a = 1;

    static {
        System.out.println("static TestA");
    }

    {
        System.out.println("{ TestA }");
    }

    public static void a() {
        System.out.println("a()");
    }

    public TestA() {
        System.out.println("TestA()");
    }
}

class TestB extends TestA {
    static {
        System.out.println("static  TestB");
    }

    {
        System.out.println("{ TestB }");
    }

    public static void a() {
        System.out.println("b()");
    }

    public TestB(){
        System.out.println("TestB()");
    }

    public static void main(String ar[]) {
//        TestA.a();
//        TestA a = new TestA();
        TestA b = new TestB();


    }
}
