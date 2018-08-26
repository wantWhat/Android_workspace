package com.example.pc.kotlindemoclass

abstract class Lanauage {
    val TAG = this.javaClass.simpleName
    fun test(){}
    abstract var name : String
    abstract fun init()
}