package com.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapTest {

	/**
	 * @param args
	 * @Author chenfeiyue
	 * @Date 2015-4-28 上午9:23:57
	 * @Version @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s1 = new String("s1");
		String s2 = new String("s1");
		System.out.println(s1.equals(s2));
		HashMap<String, Integer> map = new HashMap<>();
		System.out.println("map.put(s1, 1) = " + map.put(s1, 1));
		System.out.println("map.put(s2, 2) = " + map.put(s2, 2));

		Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> entry = it.next();
			System.out.println("key= " + entry.getKey() + " and value= "
					+ entry.getValue());
		}

		HashMap<Integer, Integer> map2 = new HashMap<>();
		Integer i1 = new Integer(135);
		Integer i2 = new Integer(135);

		System.out.println("map.put(i1, 1) = " + map2.put(i1, 1));
		System.out.println("map.put(i2, 2) = " + map2.put(i2, 2));

		Iterator<Map.Entry<Integer, Integer>> it2 = map2.entrySet().iterator();
		while (it2.hasNext()) {
			Map.Entry<Integer, Integer> entry = it2.next();
			System.out.println("key= " + entry.getKey() + " and value= "
					+ entry.getValue());
		}

		HashMap<Object, Integer> map3 = new HashMap<>();
		Object o1 = new Object();
		Object o2 = /* new Object() */o1;

		System.out.println(o1.equals(o2));
		System.out.println("map.put(o1, 1) = " + map3.put(o1, 1));
		System.out.println("map.put(o2, 2) = " + map3.put(o2, 2));

		Iterator<Map.Entry<Object, Integer>> it3 = map3.entrySet().iterator();
		while (it3.hasNext()) {
			Map.Entry<Object, Integer> entry = it3.next();
			System.out.println("key= " + entry.getKey() + " and value= "
					+ entry.getValue());
		}

	}

}
