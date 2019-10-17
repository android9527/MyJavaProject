package com.algorithm;

/**
 * Created by chenfeiyue on 2019/7/15.
 * Description:
 */
public class Fibonacci {
    private static long fibonacci(long n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static long fibonacci2(long n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int fibOne = 1;
        int fibTwo = 1;
        int fibN = 1;
        for (int i = 2; i < n; i++) {
            fibN = fibOne + fibTwo;
            fibTwo = fibOne;
            fibOne = fibN;
        }
        return fibN;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(10));
        System.out.println(fibonacci2(10));
        System.out.println(frog(10));
        System.out.println(frog2(10));
    }

    /**
     * 青蛙跳台阶问题，一次可以跳1层台阶，也可以跳2层台阶
     * <p>
     * f(n) = f(n-1) + f(n-2)
     */
    static int frog(int n) {
        if (n <= 0) {
            return 1;
        }
        // 1层台阶只有1种
        if (n == 1) {
            return 1;
        }
        // 2层台阶只有2种
        if (n == 2) {
            return 2;
        }
        return frog(n - 1) + frog(n - 2);
    }

    /**
     * 青蛙一次可以跳n层台阶
     * f(n) = f(n-1) + f(n-2) + ... + f(n-m) + ... + f(2) + f(1);
     * f(n-1) = f(n-2) + f(n-3) + ... + f(2) + f(1)
     * f(n) = 2 * f(n-1)
     *
     * @param n 台阶数量
     */
    static int frog2(int n) {

        if (n <= 1) {
            return 1;
        }
        return 2 * frog2(n - 1);
    }
}
