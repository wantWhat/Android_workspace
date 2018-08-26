package com.example.pc.kotlindemoclass

import android.util.Log

class Chinese(var str : String) : Lanauage(){
     override var name: String = ""

     override fun init() {
         name = str
         println("$name init")
         Log.i(TAG, "name==" + name)
     }
 }