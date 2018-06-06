package com.test.test;

import java.util.Map;

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
    public Child(String name) {
        super(name);
        this.name = name;
        System.out.println("Child.Child() name");
    }

    Map<String,String> map;
    public void setMap(Map<String,String> map) {
        this.map = map;
    }

    public Map<String, String> getMap() {
        return map;
    }
}