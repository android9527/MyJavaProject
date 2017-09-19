package com.test.kotlin

/**
 * Created by chenfeiyue on 17/7/27.
 * Description:
 */
fun main(args: Array<String>) {

    KotlinTest().test()
}

/**
 *  对函数进扩展：
 *   在函数前面指定一个接受者，这里的接受者是MutabList<E>集合。
 *   为它扩展一个swap函数
 *  扩展函数的this指向接受者对象。
 *
 */
fun  <T> MutableList<T>.swap(x: Int, y: Int) {
    //利用一个中间变量，互换之指定位置的值。
    val temp = this[x]
    this[x] = this[y]
    this[y] = temp
}

/**
 * 使用中辍符号 infix来标记单个参数的扩展函数
 * infix
 * 好处，可以直接使用中辍注解来调用该扩展函数
 */
fun Int.diminished(): Int {
    println(this)
    return this.dec()  //数值自减
}

class KotlinTest {
    fun test() {

        /**
         * 调用MutablListOf类的扩展方法swap( )
         */
        val mutableList = mutableListOf("1", 2, 3)
        println(mutableList)
        //通过类对象来，使用自定义的扩展函数
        mutableList.swap(0, 2)
        println(mutableList)

        /**
         * 调用Int类的扩展方法diminished()
         */
        val normal = 5
        println(normal)
        val instanceInt = 1
        println(normal.diminished())

        //等同于 ，这里使用中缀符号的注解来调用函数   println( 1 diminished 2 )
    }
}