package com.test.test;

public class Run {
    public static void main(String[] args) {
        new Child();
    }
}

/**
 *最后给出执行步骤：

 1、父类静态变量和静态代码块（先声明的先执行）；

 2、子类静态变量和静态代码块（先声明的先执行）；

 3、父类的变量和代码块（先声明的先执行）；

 4、父类的构造函数；

 5、子类的变量和代码块（先声明的先执行）；

 6、子类的构造函数。
 */