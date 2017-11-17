package com.test.volley;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class Main {

    public static void main(String[] args) {

        actualPayment(100.01);
        actualPayment(0.02);

        actualPayment(21.00000001);
        actualPayment(0.10000001);
        actualPayment(1.0211111);
        actualPayment(01.2171111);

    }

    public static String actualPayment(double money){


//        realTransAmount.multiply(invesetAmount).divide(
//                transAmount, 2, BigDecimal.ROUND_UP);

        BigDecimal originalDecimal = BigDecimal.valueOf(money);
        originalDecimal = originalDecimal.divide(BigDecimal.valueOf(1), 2, BigDecimal.ROUND_UP);

//        BigDecimal originalDecimal = BigDecimal.valueOf(money);
////        BigDecimal decimal = originalDecimal.multiply(new BigDecimal(100));
//        BigDecimal[] result = originalDecimal.divideAndRemainder(BigDecimal.valueOf(0.01));
//
//        int compare = result[1].compareTo(BigDecimal.valueOf(0));
//        if (compare > 0) {
//            originalDecimal = originalDecimal.subtract(result[1]);
//            originalDecimal = originalDecimal.add(BigDecimal.valueOf(0.01));
//        }
//
        System.out.println(originalDecimal.toString());
//        System.out.println("getQianFenWei---->" + getQianFenWei(originalDecimal.toString()));
        return originalDecimal.toString();
    }


    /**
     * 保留两位小数，开启千分位
     *
     * @param value
     * @param thousand
     * @return
     */
    public static String formatDoubleDigits(double value, boolean thousand) {

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        /*
         * setMinimumFractionDigits设置成2
         *
         * 如果不这么做，那么当value的值是100.00的时候返回100
         *
         * 而不是100.00
         */
        nf.setMinimumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.HALF_UP);
        /*
         * 如果想输出的格式用逗号隔开，可以设置成true
         */
        nf.setGroupingUsed(thousand);
        return nf.format(value);
    }

    public static String getQianFenWei(String numStr) {
        double num;
        try {
            num = Double.parseDouble(numStr);
        } catch (Exception e) {
            num = 0.0;
        }
        return getQianFenWei(num);
    }
    public static String getQianFenWei(double number) {
        return formatDoubleDigits(number, true);
    }
}
