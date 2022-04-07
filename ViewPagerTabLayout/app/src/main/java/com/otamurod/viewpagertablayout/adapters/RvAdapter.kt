package com.otamurod.viewpagertablayout.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.viewpagertablayout.R
import com.otamurod.viewpagertablayout.adapters.RvAdapter.VH
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_item.view.*

class RvAdapter(var imageList: ArrayList<String>, val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(string: String, clickListener: OnItemClickListener) {
            Picasso.get().load(string).into(itemView.img)
            itemView.setOnClickListener {
                clickListener.onItemClicked(string)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(imageList[position], itemClickListener)
    }

    override fun getItemCount(): Int = imageList.size

}

interface OnItemClickListener {
    fun onItemClicked(string: String)
}
