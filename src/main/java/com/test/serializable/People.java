package com.test.serializable;

import java.io.Serializable;

/**
 * Created by chenfeiyue on 16/1/11.
 */
public class People implements Serializable {
    private static final long serialVersionUID = 2L;
    private String name = "aaa";
    {
        System.out.println(name);
        System.out.println("sssss");
    }
    private int age = 11;
    static {
        System.out.println("static");
    }
    public People(){
        String people = "people";
    }
    private transient String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
//                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
