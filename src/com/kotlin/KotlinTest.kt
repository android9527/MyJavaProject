package com.kotlin

/**
 * Created by chenfeiyue on 17/6/26.
 * Description:
 */
class KotlinTest {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            println("Hello World!")

            val k = KotlinTest()
//            k.test
        }
        val test = if (5 > 3) {
            System.out.println("yes")
        } else {
            System.out.println("no")
        }
    }


}