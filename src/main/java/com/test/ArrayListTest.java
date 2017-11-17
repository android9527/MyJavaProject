package com.test;

import java.util.ArrayList;

public class ArrayListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ArrayList<People> list = new ArrayList<>();
		
		
		
		for(int i = 0; i <= 20 ; i ++){
			People p = new People();
			p.age = 10;
			p.name = "AAA" + i;
			list.add(p);
		}
		
		for (People o1 : list) {
			System.out.println(o1.toString());

		}
		ArrayList<People> list2 = (ArrayList<People>) list.clone();
		
		for (People o1 : list) {
			System.out.println(o1.toString());
			list2.remove(o1);
		} 
		
		System.out.println("-----------");
		for (People o1 : list) {
			System.out.println(o1.toString());

		}
		
		
		
		
//		People p = new People();
//		p.age = 10;
//		p.name = "AAA";
//		ArrayList<People> list = new ArrayList<>();
//		list.add(p);
//		p.name = "BBB";
//		for (People o1 : list) {
//			System.out.println(o1.toString());
//
//		}
//		ArrayList<People> list2 = (ArrayList<People>) list.clone();
//		p.name = "CCC";
//
//		for (People o1 : list) {
//			System.out.println(o1.toString());
//
//		}
//		for (People o1 : list2) {
//			System.out.println(o1.toString());
//
//		}

		
		

	}

	public static class People {
		String name;
		int age;

		@Override
		public String toString() {
			return "People [name=" + name + ", age=" + age + "]";
		}

	}

}
