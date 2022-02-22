package com.otamurod.dialogs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.dialogs.R
import kotlinx.android.synthetic.main.user_item.view.*

class SnackbarAdapter(var list: List<String>, var onItemClickListener: OnItemClickListener):RecyclerView.Adapter<SnackbarAdapter.VH>() {

    inner class VH(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(string: String, position: Int){
            itemView.name.text = string

            itemView.setOnClickListener {
                onItemClickListener.onItemClick(string, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener{
        fun onItemClick(string: String, position: Int)
    }
}