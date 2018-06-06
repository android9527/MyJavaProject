package com.test

/**
 * Created by chenfeiyue on 18/1/8.
 * Description:
 */
fun main(args: Array<String>) {
    val testNums = intArrayOf(10, 10000, 100000, 1000000, 10000000)
    for (item in testNums) {
        println("testnums:" + item)
        val time1 = System.currentTimeMillis()
        for (i in 0..item - 1) {
            val s = String.format("%s %s %s", "aa", "bb", "cc")
        }
        val time2 = System.currentTimeMillis()
        println("String.format waste time:" + (time2 - time1) + "ms")

        val time3 = System.currentTimeMillis()
        //StringBuilder ss=new StringBuilder();
        for (i in 0..item - 1) {
            val ssa = "aa" + "bb" + "cc"
            var ssaa = "aa" + "bb" + "cc"
            ssaa += ssa
        }
        val time4 = System.currentTimeMillis()
        println("StringBulder  waste time:" + (time4 - time3) + "ms")


        val time5 = System.currentTimeMillis()
        //StringBuilder ss=new StringBuilder();
        for (i in 0..item - 1) {
            val ssa = StringBuilder().append("aa").append("cc").append("dd").toString()
        }
        val time6 = System.currentTimeMillis()
        println("StringBulder  waste time:" + (time6 - time5) + "ms")

    }
}