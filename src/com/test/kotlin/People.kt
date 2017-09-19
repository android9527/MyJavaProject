package com.test.kotlin

import java.math.BigDecimal
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

/**
 * Created by chenfeiyue on 17/7/28.
 * Description:
 */

// 常规用法
class People1(name: String) {
}

// 当主构造函数有注解或者可见性修饰符，需加 constructor 关键字
class People2 public @Inject constructor(name: String) {
}

//若主构造函数中，不进行初始化, 可放在init{}中
class People3(name: String) {
    val name: String

    init {
        println("initialize")
        this.name = name
    }
}

// 如果类有一个主构造函数（无论有无参数），每个次构造函数需要直接或间接委托给主构造函数，用this关键字
class People4 {
//    constructor() {
//        println("constructor")
//    }
    constructor(name: String) {
println(name)
    }
    constructor(name: String, age: Int) {

    }
}


class People5 {
    private var name: String = ""
//        get() : String {
//            return name
//        }
//        set(value) {
//            name = value
//        }
    private var age : Int = 0
//        get() : Int {
//            return age
//        }
//        set(value) {
//            age = value
//        }

}