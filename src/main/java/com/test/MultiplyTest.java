package com.test;

public class MultiplyTest {

	/**
	 * @param args
	 * @Author chenfeiyue 
	 * @Date 2015-4-27 下午3:33:47
	 * @Version @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(j + "*" + i + "=" + i * j + "\t");
			}
			System.out.println();
		}
	}

}
