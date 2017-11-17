package com.test.serializable;

public class CloneTest {
	public static void main(String args[]) {
		
		CloneablePeople p1 = new CloneablePeople();
		p1.test = "p1";
		p1.p = new People();
		p1.p.setName("CloneablePeople1");
//		p1.p.setAge(11);
		
		System.err.println(p1);
		CloneablePeople p2 = p1.clone();
		
		p1.test = "p2";
		
//		p1.p = new People();
		p1.p.setName("CloneablePeople2");
//		p1.p.setAge(22);
		System.err.println(p2);
	}
	
	
}




