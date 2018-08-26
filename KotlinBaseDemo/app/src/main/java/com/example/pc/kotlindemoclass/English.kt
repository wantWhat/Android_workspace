package com.example.pc.kotlindemoclass

import android.util.Log

class English(var str : String) : Lanauage() {
    override var name: String = ""
    private lateinit var spleakListener: SpleakListener

    fun setSpleakListener(listener: SpleakListener) {
        this.spleakListener = listener
    }
    override fun init() {
        name = str
        println("$name init")
        Log.i(TAG, "name==" + name)
    }

    fun startSpeak(str : String) {
        spleakListener.onSpleak("$name speak $str")
    }
    class America() {
        fun init() {
            Log.i(TAG, "嵌套类")//TAG 为定义的常量
        }
    }

    inner class England() {
        fun init() {
            Log.i(TAG, "内部类" + "name==" + name)//TAG 为外部类的TAG, name 属性访问不到
        }
    }

    interface SpleakListener {
        fun onSpleak(name : String)//函数中参数不能带修饰符var 或者
    }
}
