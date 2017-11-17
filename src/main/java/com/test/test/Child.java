package com.test.test;

public class Child extends Parent {

    static {
        System.out.println("Child's static code block");
    }

    static Foo FOO = new Foo("Child's static parameter");

    Foo foo = new Foo("Child's parameter");

    {
        System.out.println("Child's code block");
    }

    public Child() {
        System.out.println("Child.Child()");
    }
}