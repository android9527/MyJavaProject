package com.test.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Run {
    public static void main(String[] args) {
        new Child();

        Student student = new Student();
        Student student1 = new Student();

        System.out.println(student.equals(student1));

        student.setAge(1);
        System.out.println(student.hashCode() + "   " + student1.hashCode());
        System.out.println(student.equals(student1));

        HashMap<Student, String> hashMap = new HashMap<>();
        hashMap.put(student, "1");
        hashMap.put(student1, "2");

        System.out.println(hashMap.get(student));
        Iterator iter = hashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Student, String> entry = (Map.Entry) iter.next();
            Student key = entry.getKey();
            String val = entry.getValue();
            System.out.println("key = " + key + "   " + "value = " + val);
        }
    }
}

/**
 * 最后给出执行步骤：
 * <p>
 * 1、父类静态变量和静态代码块（先声明的先执行）；
 * <p>
 * 2、子类静态变量和静态代码块（先声明的先执行）；
 * <p>
 * 3、父类的变量和代码块（先声明的先执行）；
 * <p>
 * 4、父类的构造函数；
 * <p>
 * 5、子类的变量和代码块（先声明的先执行）；
 * <p>
 * 6、子类的构造函数。
 */