package com.test

import java.math.BigDecimal
import java.text.ParseException
import java.util.Calendar
import java.util.Date

/**
 * User: wangxk
 * Date: 17-5-2
 * Time: 下午6:12
 * 金额计算工具  (参数请在外层判断是否为空)
 */
object AmountUtil {

    /***   等额本金计算 求每一期等额本金
     * @param a     本金总额
     * *
     * @param I     年化利率
     * *
     * @param n     总期数
     * *
     * @return    每一期等额应还本金
     */
    fun getEqualPrcpItst(a: BigDecimal, I: BigDecimal, n: Int): BigDecimal {
        /**月均还款:b＝a×i×（1＋i）^n÷〔（1＋i）^n－1〕 */
        // 月利率为   i=I÷12
        val i = I.divide(BigDecimal(1200), 10, BigDecimal.ROUND_HALF_EVEN)
        //  System.out.println("****月利率为"+i);
        /**月均还款:b＝a×i×（1＋i）^n÷〔（1＋i）^n－1〕 */
        // BigDecimal b= (a.multiply(i).multiply(i.add(new BigDecimal(1)).pow(n))).divide((i.add(new BigDecimal(1)).pow(n).subtract(new BigDecimal(1))),2,BigDecimal.ROUND_HALF_EVEN);
        val x1 = i.add(BigDecimal(1)).pow(n)
        val x2 = i.add(BigDecimal(1)).pow(n).subtract(BigDecimal(1))
        val b1 = a.multiply(i).multiply(x1)
        val b = b1.divide(x2, 2, BigDecimal.ROUND_HALF_EVEN)
        //    System.out.println("****月还款为"+b);
        return b
    }

    /***
     * 等额本金计算 求第m期应还利息
     * @param a     本金总额
     * *
     * @param I     年化利率
     * *
     * @param n     总期数
     * *
     * @param m     获取期数  （获取期数小于总期数 m小于等于n 否则返回0）
     * *
     * @return    第m期应还利息
     */
    fun getEqualItst(a: BigDecimal, I: BigDecimal, n: Int, m: Int): BigDecimal {
        val A = BigDecimal(0)
        //m小于等于n 否则返回0
        if (m > n) {
            return A
        }
        // 月利率为   i=I÷12
        val i = I.divide(BigDecimal(1200), 10, BigDecimal.ROUND_HALF_EVEN)
        /**月均还款:b＝a×i×（1＋i）^n÷〔（1＋i）^n－1〕 */
        val b = AmountUtil.getEqualPrcpItst(a, I, n)
        //  System.out.println("****月还款为"+b);
        /** 第m月还款利息为  bi＝（a×i－b）×（1＋i）^（j－1）＋b   */
        var bi = a.multiply(i).subtract(b).multiply(i.add(BigDecimal(1)).pow(m - 1)).add(b)
        if (n != m) {
            return bi
        } else {
            bi = b.subtract(getEqualPrcp(a, I, n, m))
        }
        return bi

    }


    private fun getEqualItst2(a: BigDecimal, I: BigDecimal, n: Int, m: Int): BigDecimal {
        val A = BigDecimal(0)
        //m小于等于n 否则返回0
        if (m > n) {
            return A
        }
        // 月利率为   i=I÷12
        val i = I.divide(BigDecimal(1200), 10, BigDecimal.ROUND_HALF_EVEN)
        /**月均还款:b＝a×i×（1＋i）^n÷〔（1＋i）^n－1〕 */
        val b = AmountUtil.getEqualPrcpItst(a, I, n)
        //  System.out.println("****月还款为"+b);
        /** 第m月还款利息为  bi＝（a×i－b）×（1＋i）^（j－1）＋b   */
        val bi = a.multiply(i).subtract(b).multiply(i.add(BigDecimal(1)).pow(m - 1)).add(b)
        return bi

    }


    /***
     * 等额本金计算 求第m期应还本金
     * @param a     本金总额
     * *
     * @param I     年化利率
     * *
     * @param n     总期数
     * *
     * @param m     获取期数  （获取期数小于总期数 m小于等于n 否则返回0）
     * *
     * @return    第m期应还本金
     */
    fun getEqualPrcp(a: BigDecimal, I: BigDecimal, n: Int, m: Int): BigDecimal {
        val A = BigDecimal(0)
        if (m > n) {
            return A
        }
        // 月利率为   i=I÷12
        val i = I.divide(BigDecimal(1200), 10, BigDecimal.ROUND_HALF_EVEN)
        //  System.out.println("****月利率为"+i);
        /**月均还款:b＝a×i×（1＋i）^n÷〔（1＋i）^n－1〕 */
        val b = AmountUtil.getEqualPrcpItst(a, I, n)
        // System.out.println("****月还款为"+b);
        /** 第m月还款利息为  bi＝（a×i－b）×（1＋i）^（j－1）＋b   */
        val bi = AmountUtil.getEqualItst2(a, I, n, m)
        /** 第m月还款本金为  ba＝b-bi  */
        var ba: BigDecimal? = null
        if (n != m) {
            ba = b.subtract(bi)
        } else {
            var ball = BigDecimal(0)

            //            for(int i2 = 1;i2 <= n; i2++) {
            //                //算出前n期总利息
            //                ball = ball.add(getEqualItst2(a, I, n, i2));
            //            }
            //本金+总利息-前n-1期月还款额-第n期还款利息=第n期本金
            //            BigDecimal add = a.add(ball);
            //            BigDecimal multiply = b.multiply(new BigDecimal(n - 1));
            //            ba= add.subtract(multiply).subtract(bi);
            //最后一次采用本金-前n期得本金
            for (i2 in 1..n - 1) {
                //算出前n期总利息
                ball = ball.add(getEqualPrcp2(a, I, n, i2))
            }
            ba = a.subtract(ball)
            //（前n-1期还的本金合计）= （n-1 ）* 每次还款额度 - ball（n-1 期利息合计）
            //            for(int i3 = 1;i3 < n; i3++) {
            //                //算出前n期总利息
            //                ball = ball.add(getEqualItst2(a, I, n, i3));
            //            }
            //            BigDecimal multiply = b.multiply(new BigDecimal(n - 1));
            //            BigDecimal bfAll= multiply.subtract(ball) ;
            //            ba= a.subtract(bfAll) ;
            // 本金 -（前n-1期还的本金合计）

        }
        return ba!!.divide(BigDecimal.ONE, 2, BigDecimal.ROUND_HALF_EVEN)
    }

    /***
     * 等额本金计算 求第m期应还本金
     * @param a     本金总额
     * *
     * @param I     年化利率
     * *
     * @param n     总期数
     * *
     * @param m     获取期数  （获取期数小于总期数 m小于等于n 否则返回0）
     * *
     * @return    第m期应还本金
     */
    fun getEqualPrcp2(a: BigDecimal, I: BigDecimal, n: Int, m: Int): BigDecimal {
        val A = BigDecimal(0)
        if (m > n) {
            return A
        }
        // 月利率为   i=I÷12
        val i = I.divide(BigDecimal(1200), 10, BigDecimal.ROUND_HALF_EVEN)
        //  System.out.println("****月利率为"+i);
        /**月均还款:b＝a×i×（1＋i）^n÷〔（1＋i）^n－1〕 */
        val b = AmountUtil.getEqualPrcpItst(a, I, n)
        // System.out.println("****月还款为"+b);
        /** 第m月还款利息为  bi＝（a×i－b）×（1＋i）^（j－1）＋b   */
        val bi = AmountUtil.getEqualItst2(a, I, n, m)
        /** 第m月还款本金为  ba＝b-bi  */
        var ba: BigDecimal? = null
        ba = b.subtract(bi)
        return ba!!.divide(BigDecimal.ONE, 2, BigDecimal.ROUND_HALF_EVEN)
    }

    /**
     * @Description (按月计息，每个月应还本息金额)
     * *
     * @author haoyunfeng
     * *
     * @param a     本金总额
     * *
     * @param I     年化利率
     * *
     * @param n     总期数
     * *
     * @param m     获取期数  （获取期数小于总期数 m小于等于n 否则返回0）
     * *
     * @return    第m期应还本息金额
     * *
     * @date 2017/5/22 16:00
     */
    fun getMonthPrcpItst(a: BigDecimal, I: BigDecimal, n: Int, m: Int): BigDecimal {
        //每月应还金额
        var A = BigDecimal(0)
        if (m > n) {
            return A
        }
        //每月应还金额=应还本金+应还利息
        A = AmountUtil.getMonthPrcp(a, I, n, m).add(AmountUtil.getMonthItst(a, I, n, m))
        return A
    }

    /**
     * @Description (按月计息,求第m期应还本金)
     * *
     * @author haoyunfeng
     * *
     * @param a     本金总额
     * *
     * @param I     年化利率
     * *
     * @param n     总期数
     * *
     * @param m     获取期数  （获取期数小于总期数 m小于等于n 否则返回0）
     * *
     * @return    第m期应还本金
     * *
     * @date 2017/5/22 16:04
     */
    fun getMonthPrcp(a: BigDecimal, I: BigDecimal, n: Int, m: Int): BigDecimal {
        //每月应还本金
        var A = BigDecimal(0)
        if (m > n) {
            return A
        }
        /** 最后一期，本金为借款本金;其他期本金为0  */
        if (n == m) {
            A = a
        }
        return A
    }

    /**
     * @Description (按月计息,求第m期应还利息)
     * *
     * @author haoyunfeng
     * *
     * @param a  本金总额
     * *
     * @param I  年化利率
     * *
     * @param n  总期数
     * *
     * @param m  获取期数  （获取期数小于总期数 m小于等于n 否则返回0）
     * *
     * @return    第m期应还利息
     * *
     * @date 2017/5/22 16:04
     */
    fun getMonthItst(a: BigDecimal, I: BigDecimal, n: Int, m: Int): BigDecimal {
        //每月应还利息
        var A = BigDecimal(0)
        //m小于等于n 否则返回0
        if (m > n) {
            return A
        }
        //每月利息计算
        A = a.multiply(I).divide(BigDecimal(100)).divide(BigDecimal(12), 2, BigDecimal.ROUND_HALF_EVEN)
        return A
    }


    /**
     * @Description (按天计息固定还款日,判断还款日是否在 起息日期的天数 和 结束日期的天数 之间)
     * *
     * @author haoyunfeng
     * *
     * @param startDate 项目起息时间
     * *
     * @param endDate   项目结束时间
     * *
     * @param repayDay  固定还款日
     * *
     * @date 2017/5/23 15:03
     */
    private fun checkFixedDay(startDate: Date, endDate: Date, repayDay: Int): Boolean {
        //默认还款日不在 起息日期的天数 和 结束日期的天数 之间
        var flag = false
        //起息时间
        val startCal = Calendar.getInstance()
        startCal.time = startDate
        val startDay = startCal.get(Calendar.DATE)
        //结束时间
        val endCal = Calendar.getInstance()
        startCal.time = endDate
        val endDay = endCal.get(Calendar.DATE)
        //还款日在 起息日期的天数 和 结束日期的天数 之间
        if (repayDay > startDay && repayDay < endDay) {
            flag = true
        }
        return flag
    }

    fun calculateFixedPaymentBenefit(investMoney: Double, yearRate: Double, periods: Int): Double {
        val iMoneyDecimal = investMoney.toString().bd
        val rateDecimal = BigDecimal(yearRate.toString())
        val equalPrcpItst = getEqualPrcpItst(iMoneyDecimal, rateDecimal, periods)
        var benefitDecimal = BigDecimal(0)
        for (i in 1..periods + 1 - 1) {
            val equalPrcp = getEqualPrcp(iMoneyDecimal, rateDecimal, periods, i)
            var equalItst = equalPrcpItst.subtract(equalPrcp)
            val result = equalItst.compareTo(BigDecimal.ZERO)
            if (result <= 0) {
                equalItst = BigDecimal(0.toString())
            }
            benefitDecimal = benefitDecimal.add(equalItst)
            System.err.println("第" + i + "期总还款：" + equalPrcpItst + " 还款本金：" + equalPrcp + " 还款利息：" + equalItst)
        }
        return benefitDecimal.toDouble()
    }


    /**
     * @Description (一次还本还息，计算本金)
     * *
     * @author haoyunfeng
     * *
     * @param a 本金总额
     * *
     * @date 2017/5/23 19:20
     */
    @Throws(ParseException::class)
    fun getOneTotalPrcp(a: BigDecimal): BigDecimal {
        return a
    }

    @Throws(Exception::class)
    @JvmStatic fun main(adf: Array<String>) {


        val s : Student ?= null
        println(s?.toString())

        val periods = 5
        System.err.println("总利息：" + calculateFixedPaymentBenefit(10.0, 7.3, 10))
        System.err.println("总利息：" + calculateFixedPaymentBenefit(1000.0, 8.1, 3))
        System.err.println("总利息：" + calculateFixedPaymentBenefit(200.0, 8.1, 3))
        System.err.println("总利息：" + calculateFixedPaymentBenefit(100.0, 8.1, 3))

        val iMoneyDecimal = 3800.bd
        val rateDecimal = 8.1.bd
        val equalPrcpItst = getEqualPrcpItst(iMoneyDecimal, rateDecimal, periods)
        //计算等额本息每一期本金
        for (i in 1..periods) {
            val equalPrcp = getEqualPrcp(iMoneyDecimal, rateDecimal, periods, i)
            val equalItst = equalPrcpItst.subtract(equalPrcp)
            System.err.println("总还款：" + equalPrcpItst + "还款本金：" + equalPrcp + "还款利息" + equalItst)
        }

        /***
         * 等额本息test
         * 设贷款额为a  ，月利率为i，年利率为I，还款月数为n，每月还款额为b，还款本息总和为Y
         * 月利率为   i=I÷12
         */
        //        BigDecimal A = new BigDecimal(10000);
        //        System.out.println("****月还款为"+    AmountUtil.getEqualPrcpItst(A, new BigDecimal(13), 6));
        //        for (int i=1;i<=6;i++){
        //            System.out.println("第"+i+"期应还利息"+     AmountUtil.getEqualItst(A, new BigDecimal(15), 6, i));
        //            System.out.println("第"+i+"期应还本金"+    AmountUtil.getEqualPrcp(A, new BigDecimal(15), 6, i))  ;
        //        }

        /** 按月计息test   */
        //        BigDecimal A = new BigDecimal(10200);//本金
        //        BigDecimal I = new BigDecimal(12);   //年化利率
        //        int num = 8;                             //总期数
        //        for(int i=1;i<=num;i++){
        //            System.out.println("第"+i+"期应还利息"+     AmountUtil.getMonthItst(A, I, num, i));
        //            System.out.println("第"+i+"期应还本金"+     AmountUtil.getMonthPrcp(A, I, num, i));
        //            System.out.println("第"+i+"期应还本息"+     AmountUtil.getMonthPrcpItst(A, I, num, i));
        //        }

        /** 按天计息test   */
        //        BigDecimal A = new BigDecimal(10000);//本金
        //        BigDecimal I = new BigDecimal(15);   //年化利率
        //        int num = 8;                             //总期数
        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //        Date startDate =sdf.parse("2017-05-23");
        //        Date endDate =sdf.parse("2017-09-23");
        //        for(int i=1;i<=num;i++){
        //            System.out.println("第"+i+"期应还利息"+     AmountUtil.getDayItst(A,I,startDate,endDate,i));
        //            System.out.println("第"+i+"期应还本金"+     AmountUtil.getDayPrcp(A,startDate,endDate,i));
        //            System.out.println("第"+i+"期应还本息"+     AmountUtil.getDayPrcpItst(A,I,startDate,endDate,i));
        //        }
        /** 固定还款日test   */
        //        BigDecimal A = new BigDecimal(10000);//本金
        //        BigDecimal I = new BigDecimal(15);   //年化利率
        //        int num = 8;                             //总期数
        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //        Date startDate =sdf.parse("2017-05-11");
        //        Date endDate =sdf.parse("2017-10-25");
        //        int repayDay = 27;
        //        for(int i=1;i<=num;i++){
        //            System.out.println("第"+i+"期应还利息"+     AmountUtil.getFixedDayItst(A,I,startDate,endDate,repayDay,i));
        //            System.out.println("第"+i+"期应还本金"+     AmountUtil.getFixedDayPrcp(A,startDate,endDate,repayDay,i));
        //            System.out.println("第"+i+"期应还本息"+     AmountUtil.getFixedDayPrcpItst(A,I,startDate,endDate,repayDay,i));
        //        }
        /** 一次性还本还息test   */
        //        BigDecimal A = new BigDecimal(10000);//本金
        //        BigDecimal I = new BigDecimal(15);   //年化利率
        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //        Date startDate =sdf.parse("2017-05-24");
        //        Date endDate =sdf.parse("2018-08-16");
        //        System.out.println("一次还本还息应还利息"+     AmountUtil.getOneTotalItst(A,I,startDate,endDate));
        //        System.out.println("一次还本还息应还利息"+     AmountUtil.getOneTotalPrcp(A));
        //        System.out.println("一次还本还息应还利息"+     AmountUtil.getOneTotalPrcpItst(A,I,startDate,endDate));

    }


    //    private static void test(String repayType){
    //        if (repayType.equals("10") || repayType.equals("15")) {
    //            // 总利息
    //            BigDecimal sumRepayAmt = new BigDecimal(0);
    //            if (noRepayLoanPhase != null && noRepayLoanPhase.size() > 0) {
    //                for (Map noRepayloanPhase : noRepayLoanPhase) {
    //                    sumRepayAmt = sumRepayAmt.add(transMoney
    //                            .multiply(
    //                                    new BigDecimal(aunualInterestRate)
    //                                            .divide(new BigDecimal(100)))
    //                            .multiply(
    //                                    new BigDecimal(noRepayloanPhase.get(
    //                                            "termDays").toString()))
    //                            .divide(new BigDecimal(365), 2,
    //                                    BigDecimal.ROUND_HALF_EVEN));
    //                }
    //            }
    //            sumRepayAmt = sumRepayAmt.add(transMoney).subtract(buyAmount);
    //            map.put("toBeIncome", sumRepayAmt);
    //            map.put("toBePev",
    //                    sumRepayAmt.multiply(new BigDecimal("36500")).divide(
    //                            new BigDecimal(haveCount).multiply(buyAmount),
    //                            16, BigDecimal.ROUND_HALF_EVEN));
    //            return map;
    //        }
    ///**
    // * 按月计息，到期还本 承接收益=转让金额*年化率/12个月*当前剩余期限(月)+转让本金-认购本金; 承接年化收益率=承接收益 /
    // * 实际认购金额 / 当前剩余期限(天) * 100 * 365
    // */
    //        else if (repayType.equals("5")) {
    //            // 总利息
    //            BigDecimal sumRepayAmt = new BigDecimal(0);
    //            if (noRepayLoanPhase != null && noRepayLoanPhase.size() > 0) {
    //
    //                for (int i = 0; i < noRepayLoanPhase.size(); i++) {
    //                    sumRepayAmt = sumRepayAmt.add(transMoney.multiply(
    //                            new BigDecimal(aunualInterestRate)).divide(
    //                            new BigDecimal(1200), 2,
    //                            BigDecimal.ROUND_HALF_EVEN));
    //                }
    //            }
    //            sumRepayAmt = sumRepayAmt.add(transMoney).subtract(buyAmount);
    //            // BigDecimal beIncome=transMoney
    //            // .multiply(new BigDecimal(aunualInterestRate)).divide(new
    //            // BigDecimal(1200), 2, BigDecimal.ROUND_HALF_EVEN).multiply(new
    //            // BigDecimal(loanInvestor.getLeftTermCount()))
    //            // .add(transMoney).subtract(buyAmount);
    //            map.put("toBeIncome", sumRepayAmt);
    //            map.put("toBePev",
    //                    sumRepayAmt.multiply(new BigDecimal("3650")).divide(
    //                            new BigDecimal(haveCount).multiply(buyAmount),
    //    }

    private val Int.bd : BigDecimal
        get() = BigDecimal(this.toString())
    private val Double.bd : BigDecimal
        get() = BigDecimal(this.toString())
    private val String.bd : BigDecimal
        get() = BigDecimal(this)
}
