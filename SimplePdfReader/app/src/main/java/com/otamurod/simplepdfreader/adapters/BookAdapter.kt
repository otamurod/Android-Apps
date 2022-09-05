package com.otamurod.simplepdfreader.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.otamurod.simplepdfreader.databinding.BookBinding
import java.io.File

class BookAdapter(var list: List<File?>? = null) : RecyclerView.Adapter<BookAdapter.VH>() {

    inner class VH(var bookBinding: BookBinding) : RecyclerView.ViewHolder(bookBinding.root) {

        fun onBind(file: File) {
            bookBinding.bookName.text = file.name
            bookBinding.path.text = file.path
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VH {
        return VH(BookBinding.inflate(LayoutInflater.from(p0.context), p0, false))
    }

    override fun onBindViewHolder(p0: VH, p1: Int) {
        p0.onBind(list!![p1]!!)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}