package com.example.pc.kotlindemoclass

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btn_click.setOnClickListener {
            Log.i(TAG, "VIEW==" + it.id)
            var intent :Intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        }
    }
}
