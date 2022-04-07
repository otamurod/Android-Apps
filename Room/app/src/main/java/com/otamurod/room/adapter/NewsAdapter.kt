package com.otamurod.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.room.databinding.ItemNewsBinding
import com.otamurod.room.entity.News

class NewsAdapter(var list: List<News>, var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<NewsAdapter.VH>() {

    inner class VH(var itemNewsBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(itemNewsBinding.root) {

        fun onBind(news: News, position: Int) {
            itemNewsBinding.tv1.text = news.title
            itemNewsBinding.tv2.text = news.info

            itemNewsBinding.deleteBtn.setOnClickListener {
                onItemClickListener.onItemDelete(news, position)
            }
            itemNewsBinding.editBtn.setOnClickListener {
                onItemClickListener.onItemEdit(news, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemNewsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onItemDelete(news: News, position: Int)
        fun onItemEdit(news: News, position: Int)
    }
}