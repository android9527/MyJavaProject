package com.test.collection;

import java.util.HashMap;

/**
 * Created by chenfeiyue on 16/8/30.
 */
public class HashMapTest {
    public static void main(String args[]) {

        HashMap<Test, String> map = new HashMap();
        Test test1 = new Test();
        test1.age = 18;
        test1.name = "test1";
        Test test2 = new Test();
        test2.age = 19;
        test2.name = "test2";
        Test test3 = new Test();
        test3.age = 19;
        test3.name = "test3";

        map.put(test1, "1");
        map.put(test2, "2");
//        map.put(test3, "2");
        System.out.println(System.currentTimeMillis());

        System.out.println(map.containsKey(test2));
        test2.age = 10;
        System.out.println(map.containsKey(test2));
    }

    static class Test {
        String name = "";
        Integer age;

        @Override
        public int hashCode() {
            return age.hashCode();
        }

        @Override
        public String toString() {
            return "Test{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
