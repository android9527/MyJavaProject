package com.test.kotlin


/**
 * Created by chenfeiyue on 17/7/28.
 * Description:
 */
class Hello {
    private val name by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        println("I am lazy")
    }

    init {
        println("init block")
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val hello = Hello()
//            hello.init()
            hello.name
            hello.name
            hello.name

            /**
             * 调用MutablListOf类的扩展方法swap( )
             */
            val mutableList = mutableListOf("1", 2, 3)
            println(mutableList)
            //通过类对象来，使用自定义的扩展函数
            mutableList.swap(0, 2)

            fun a(left: Int, right: Int) = left * right
            val aLambda = a(2,3)
            val bLambda = {left: Int, right: Int -> left * right}

            println(aLambda)
            bLambda(2, 4).let (::println)
            Array(10) {it}.map { it * 2 }/*.joinToString()*/.let ( ::println )
        }
    }
    fun a(left: Int, right: Int) = left * right
}
fun a(left: Int, right: Int) = left * right