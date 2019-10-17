package com.algorithm;

/**
 * Created by chenfeiyue on 2019/9/16.
 * Description:
 */
public class PowerTest {

    public static void main(String[] args) {

    }

    /**
     * 求数字的n次幂
     * 数字为0时，结果为0
     * <p>
     * 指数为0时，结果为1
     * 指数<0时，结果为n次幂的倒数
     */
    static double power(int base, int exponent) {

        if (base == 0) {
            return 0;
        }
        if (exponent == 0) {
            return 1;
        }

        double result = 1;
        if (exponent > 0) {
            for (int i = 0; i < exponent; i++) {
                result *= base;
            }
        } else {
            int absExp = -exponent;
            for (int i = 0; i < absExp; i++) {
                result *= base;
            }
            result = 1 / result;
        }
        return result;
    }

    /**
     *
     * 另一种解题思路，
     * 剑指offer 面试题16，P112
     * 当输入的指数为32时，那么只要在16次方的基础上，再平方一次
     * 得出公式
     * 当n为偶数时 a(n) = a(n/2) * a(n/2)
     * 当n为奇数时 a(n) = a((n-1)/2) * a((n-1)/2) * a
     */
    static double power2(int base, int exponent) {

        if (base == 0) {
            return 0;
        }
        if (exponent == 0) {
            return 1;
        }

        double result = power2(base, exponent >> 2);
        result *= result;
        return result;
    }

}
