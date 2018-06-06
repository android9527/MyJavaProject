package com.test.test;

public class Parent {
    protected String name;
    static Foo FOO = new Foo("Parent's static parameter");
    
    Foo foo = new Foo("Parent's parameter");
    
    static {
        System.out.println("Parent's static code block");
    }
    
    {
        System.out.println("Parent's code block");
    }
    
    public Parent() {
        System.out.println("Parent.Parent()" + name);
    }

    public Parent(String name) {
        System.out.println("Parent.Parent(name)" + name);
    }
}