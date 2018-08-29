package com.example.pc.kotlindemoclass

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.example.pc.kotlindemoclass.adapter.BookAdapter
import kotlinx.android.synthetic.main.activity_main2.*

class RecyclerViewActivity : AppCompatActivity(), BookAdapter.ItemClickListener {
    override fun onItemClick(position: Int) {
        Log.i(TAG,"CLICK POSITION==" + position)
    }

    var mRecycler: RecyclerView? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btn_click.setOnClickListener {
            Log.i(TAG, "VIEW==" + it.id)
            var intent: Intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        }
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        var list: ArrayList<Book> = ArrayList<Book>()
        for (i in 0 until 10) {
            Log.i(TAG, "I==$i")
            var book: Book = Book()
            book.name = "亮剑" + i
            book.price = "$10" + i
            list.add(book)
        }
        var bookAdapter : BookAdapter = BookAdapter(this, list)
        bookAdapter.setItemClickListener(this)
        recycler_view.adapter = bookAdapter

    }
}
