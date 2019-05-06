package com.test.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenfeiyue on 17/10/25.
 * Description:
 */
public class Student {
    public Student(String name) {

    }

    public Student() {

    }


    public Student(Student student) {
        this.age = student.age;
        this.name = student.name;
        this.map = student.map;
    }

    private Map<String, String> map;


    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.age = 1;
        student.name = "2";
        Map map = new HashMap();
        map.put("1", "1");
        map.put("2", "2");
        student.map = map;

        Student student2 = new Student(student);



        System.out.println(student.map == student2.map);
        System.out.println(student.map.toString());
        System.out.println(student2.map.toString());
    }
}
