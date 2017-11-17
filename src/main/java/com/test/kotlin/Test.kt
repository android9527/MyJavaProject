package com.test.kotlin

import java.math.BigDecimal
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * Created by chenfeiyue on 17/7/26.
 * Description:
 */
object Test {

}
class C {
    fun foo() { println("member") }
}

fun C.foo() { println("extension") }
fun main(adf: Array<String>) {
    People4("name")
    test()
    C().foo()
    println("OK")

    println(highOrderFunc(::callback))

    highOrderTest { callback("callback") }

    val lock = ReentrantLock()
    val result = lock(lock, ::callback)

    people { say() }


    val a = "a"
    val b = "b"

    people(a) { say(a) }
    checkNull(b) { equals(a, a) }

    checkNull(a, b) { s: String, s1: String -> equals(a, a) }
    fun add(f: (Int) -> Int) = f(10)
    val x = add({ Int -> 2 })
    println(x) //2
}

private fun checkNull(s2: String, equals: () -> Boolean) {
    val result = equals()
    println("checkNull----->$s2   $result")
}

fun equals(s1: String, s2: String): Boolean {
    return s1.equals(s2)
}

fun checkNull(s2: String, s1: String, equals: (s1: String, s2: String) -> Boolean): Boolean {
    val result = equals(s1, s2)
    println("checkNull----->" + result)
    return result
}


fun callback(): String = "Test"

fun callback(str: String) {
    println(str)
}

fun highOrderFunc(body: () -> String): String {

    println("request")

    return body()
//    return fun() : String {
//        return "2222"
//    }
}


fun say() {
    println("say Hello World")
}

fun say(msg: String) {
    println("Hello $msg")
}

/**
 * 在 Kotlin 中无返回为 Unit
 *
 * 此方法接收一个无参数的函数并且无返回
 *
 * 使用参数名加 () 来调用
 */
fun people(hello: () -> Unit) {
    hello()
}

/**
 * 当调用的函数有形参时，
 * 需要在调用的函数声明，并使用声明的形参；
 * 函数参数中的形参无法使用
 */
fun people(arg0: String, hello: (arg1: String) -> Unit) {
    hello(arg0)
    // hello(arg1) 这样调用将报错
}

fun highOrderTest1(body: (str: String) -> String): String {

    // TODO request


    val thread = object : Thread() {

        override fun run() {
            try {
                Thread.sleep(1000)

                val result = body("Thread, Completed")

            } catch (e: Exception) {
                e.printStackTrace()
            }
            super.run()
        }
    }

    thread.start()

    return "OK"
}

fun highOrderTest(body: (str: String) -> Unit) {

    // TODO request

    val thread = object : Thread() {

        override fun run() {
            try {
                Thread.sleep(1000)
                body("Thread, Completed")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            super.run()
        }
    }
    thread.start()
}


fun <T> lock(lock: Lock, body: () -> T): T {
    lock.lock()
    try {
        return body()
    } finally {
        lock.unlock()
    }
}
