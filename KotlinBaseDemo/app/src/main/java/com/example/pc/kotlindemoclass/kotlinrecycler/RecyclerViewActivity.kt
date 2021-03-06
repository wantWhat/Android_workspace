package com.example.pc.kotlindemoclass.kotlinrecycler

import android.content.Intent
import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.pc.kotlindemoclass.Book
import com.example.pc.kotlindemoclass.service.Main3Activity
import com.example.pc.kotlindemoclass.R
import kotlinx.android.synthetic.main.activity_main2.*

class RecyclerViewActivity : AppCompatActivity(), BookAdapter.ItemClickListener {
    override fun onItemClick(position: Int) {
        Log.i(com.example.pc.kotlindemoclass.TAG,"CLICK POSITION==" + position)
    }

    var mRecycler: RecyclerView? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btn_click.setOnClickListener {
            Log.i(com.example.pc.kotlindemoclass.TAG, "VIEW==" + it.id)
            var intent: Intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        }
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        var list: ArrayList<Book> = ArrayList<Book>()
        for (i in 0 until 10) {
            Log.i(com.example.pc.kotlindemoclass.TAG, "I==$i")
            var book: Book = Book()
            book.name = "亮剑" + i
            book.price = "$10" + i
            list.add(book)
        }

        recycler_view.addItemDecoration(ItemDec(20))
        var bookAdapter : BookAdapter = BookAdapter(this, list)
        bookAdapter.setItemClickListener(this)
        recycler_view.adapter = bookAdapter

    }
    class ItemDec(var itemSpace : Int) : RecyclerView.ItemDecoration() {
        var space : Int = itemSpace;
        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect!!.left = itemSpace;
            outRect!!.right = itemSpace;
            outRect.top = itemSpace;
            outRect.bottom = itemSpace;
        }
    }
}
