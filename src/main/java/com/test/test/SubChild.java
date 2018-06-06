package com.test.test;


public class SubChild extends Child {

    public SubChild() {
        System.out.println("SubChild.SubChild()");
    }

    public SubChild(String name) {
        super(name);
        this.name = name;
        System.out.println("SubChild.SubChild() name = " + name);
    }
}