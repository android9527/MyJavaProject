package com.test;

public class SortTest {

	/**
	 * @param args
	 * @Author chenfeiyue 
	 * @Date 2015-4-27 下午4:14:38
	 * @Version @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] a = { 49, 38, 98, 97, 76, 13, 27, 49 };

//		bubbleSort(a);

//		selectSort(a);

		insertSort(a);
		
		
		
		
		
		System.out.println("查找 " + 98  +"   " +  binarySearch(a, 98));
	}

	/**
	 * 冒泡排序
	 * @param a
	 * @Date 2015-4-27 下午4:27:25
	 */
	private static void bubbleSort(Integer[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					int temp = a[j + 1];
					a[j + 1] = a[j];
					a[j] = temp;
				}
			}

			System.out.println("第" + (i + 1) + "趟排序");
			printArray(a);
		}

	}

	private static <T> void printArray(T[] a) {
		for (Object o : a) {
			System.out.print(o + "  ");
		}
		System.out.println();
	}

	/*代码

	Code highlighting produced by Actipro CodeHighlighter (freeware)http://www.CodeHighlighter.com/-->/**  
	 * 冒泡法排序<br/>  

	 * <li>比较相邻的元素。如果第一个比第二个大，就交换他们两个。</li>  
	 * <li>对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。</li>  
	 * <li>针对所有的元素重复以上的步骤，除了最后一个。</li>  
	 * <li>持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。</li>  

	 *   
	 * @param numbers  
	 *            需要排序的整型数组  
	 */
	public static void bubbleSort2(Integer[] numbers) {
		int temp; // 记录临时中间值   
		int size = numbers.length; // 数组大小   
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - i; j++) {
				if (numbers[i] > numbers[j]) { // 交换两数的位置   
					temp = numbers[i];
					numbers[i] = numbers[j];
					numbers[j] = temp;
				}
			}
			System.out.println("第" + (i + 1) + "趟排序");
			printArray(numbers);
		}
	}

	/**
	 * 直接插入排序

		（1）基本思想：在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排

		好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数

		也是排好顺序的。如此反复循环，直到全部排好顺序。
	 * @Date 2015-4-28 下午3:39:14
	 */
	private static void insertSort(Integer[] a) {
		for (int i = 1; i < a.length; i++) {
			int currentValue = a[i];
			int position = i;
			for (int j = i - 1; j >= 0; j--) {
				if (a[j] > currentValue) {
					a[j + 1] = a[j];
					position --;
				} else {
					break;
				}
			}
			a[position] = currentValue;
			System.out.println("第" + (i) + "趟排序");
			printArray(a);
		}
	}

	/**
	 * 选择排序，就是选择最小的，然后置换，循环再找到最小的，再置换..
	 * @param a
	 * @Date 2015-4-28 下午3:50:03
	 */
	private static void selectSort(Integer[] a) {
		for (int i = 0; i < a.length; i++) {
			int position = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[position] > a[j]) {
					position = j;
				}
			}

			if (position != i) {
				int temp = a[position];
				a[position] = a[i];
				a[i] = temp;
			}

			System.out.println("第" + (i + 1) + "趟排序");
			printArray(a);
		}
	}
	
	
	private static int binarySearch(Integer[] a, int des){
		int left = 0;
		int right = a.length - 1;
		while(left <= right){
			int middle = (left + right) / 2;
			
			if(des == a[middle]){
				return middle;
			}
			
			if(des < a[middle]){
				right = middle - 1;
			}else if (des > a[middle]){
				left = middle + 1;
			}
		}
		
		return -1;
	}

}
