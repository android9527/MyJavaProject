package com.test.kotlin

/**
 * Created by chenfeiyue on 17/7/28.
 * Description:
 */
class Hello {
    private val name by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        println("I am lazy")
    }

    fun init(){
        TODO("还没有实现!")
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val hello = Hello()
            hello.init()
            hello.name
        }
    }
}