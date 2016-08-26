package com.test.serializable;

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
		read();
	}

	private static void write() {
		People p = new People();
		p.setName("chen");
		p.setAge(24);

		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("/Users/qfpay/Documents/my.out"));// 输出流保存的文件名为
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
			oin = new ObjectInputStream(
					new FileInputStream("/Users/qfpay/Documents/my.out"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		People people = null;
		try {
			people = (People) oin.readObject();// 由Object对象向下转型为MyTest对象
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(people);
	}

}
