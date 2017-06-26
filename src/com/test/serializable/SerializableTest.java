package com.test.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        write();
//        read();

//        writeTwice();

    }

    private static void write() {
        People p = new People();
        p.setName("chen");
//        p.setAge(24);
        p.setSex("man");

        try {
            File file = new File("my.out");
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(file));// 输出流保存的文件名为
            // my.out
            // ；ObjectOutputStream能把Object输出成Byte流
            oos.writeObject(p);
            oos.flush(); // 缓冲流
            oos.close(); // 关闭流
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("write finish");
    }

    private static void read() {
        ObjectInputStream oin = null;// 局部变量必须要初始化
        try {
            File file = new File("my.out");
            oin = new ObjectInputStream(
                    new FileInputStream(file));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        People p1;
        try {
            p1 = (People) oin.readObject();// 由Object对象向下转型为MyTest对象
            System.out.println(p1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void writeTwice() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("result.obj"));
            People p = new People();
            p.setName("chen");
//            p.setAge(24);
            p.setSex("man");
            //试图将对象两次写入文件
            out.writeObject(p);
            out.flush();
            System.out.println("length------>" + new File("result.obj").length());

            p.setAge(20);
            out.writeObject(p);
            out.close();
            System.out.println("length------>" + new File("result.obj").length());

            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                    "result.obj"));
            //从文件依次读出两个文件
            People p1 = (People) oin.readObject();
            People p2 = (People) oin.readObject();
            oin.close();

            //判断两个引用是否指向同一个对象
            System.out.println(p1 == p2);
            System.out.println(p1);
            System.out.println(p2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
