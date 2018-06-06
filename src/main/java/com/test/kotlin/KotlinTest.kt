package com.test.kotlin

import java.math.BigDecimal

/**
 * Created by chenfeiyue on 17/6/26.
 * Description:
 */
fun test(a: Int = 1) {
    println(a)
}
fun test(a: Int, b : Double = 0.1, s : String = "str", flag : Boolean) {

}

interface MyInterface {
    val str: String
        get() = "String"
    val prop: Int // 抽象的,需要子类重写

    val propertyWithImplementation: String
        get() = "foo"

    fun bar()
    fun foo() {
        // 可选的方法体
        println("foo")
    }
}
class Child : MyInterface {
    override val prop: Int
        get() = 3

    override fun bar() {
        // 方法体
    }
}
// 给 Int 定义扩展
infix fun Int.shl(x: Int): Int {
    return x
}

// 局部函数
fun add(i: Int) {
    val n = 10
    fun add(m :Int, n : Int) : Int {
        return m + n
    }
    val result = add(i, 5)
}

fun main(args: Array<String>) {
    add(9)
    println("Hello World!")
    test(2)
    // 相当于test(1)
    test()
    // 相当于test(1, 0.1, "str", false)
    test(a = 1, flag = false)

    val bd = "3".bd
    val d = 4.00.bd
    println(bd.add(d))
    println(bd.addAndMul(d))

    val c = Child()
    println(c.str)
    c.foo()
    // 用中缀表示法调用扩展函数
    1 shl 2
// 等同于这样
    1.shl(2)
    test()
}

class People {
    var name: String = ""
    var age : Int = 0
}

/**
 * 对Double属性扩展
 */
private val Double.bd : BigDecimal
    get() = BigDecimal(this.toString())

private val String.bd : BigDecimal
    get() = BigDecimal(this)

/**
 * 对BigDecimal函数扩展,增加相加然后相乘的方法
 */
fun BigDecimal.addAndMul(parm: BigDecimal) : BigDecimal {
    var temp = this + parm
    temp *= parm
    return temp
}
fun test() {
    var sum = 0
    val ints: IntArray = intArrayOf(1, 2, 3)
    ints.filter { it > 0 }.forEach {
        sum += it
    }
    print(sum)
}

