package com.example.pc.kotlindemoclass.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.pc.kotlindemoclass.Book
import com.example.pc.kotlindemoclass.R

class BookAdapter(ctx: Context, data: ArrayList<Book>) : RecyclerView.Adapter<BookAdapter.MyViewHolder>() {
    private var mContext: Context = ctx
    private var mData: ArrayList<Book> = data
    var mItemListener : ItemClickListener? = null

    fun setItemClickListener(listener : ItemClickListener) {
        mItemListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.book_item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder!!.nameTv.text = mData.get(position).name
        holder.priceTv.text = mData.get(position).price
        holder.itemView!!.setOnClickListener {
            mItemListener!!.onItemClick(position)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTv:TextView = itemView!!.findViewById<TextView>(R.id.book_title)
        var priceTv :TextView = itemView!!.findViewById<TextView>(R.id.price_tv)

    }

    interface ItemClickListener {
        fun onItemClick(position : Int)
    }
}