package com.test;

public class BreakTest {
	public static void main(String[] args) {
		
		
		for(int i = 0; i < 4; i ++){
			
			for(int j = 0; j < 3; j ++){
				
				if(j == 1){
					continue;
				}
				System.out.println("in");
			}
			
			System.out.println("out");
		}
		
	}
}
