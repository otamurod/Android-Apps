package com.otamurod.playmarket.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.playmarket.R
import com.otamurod.playmarket.models.Program
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_in_play_market.view.*

class InPlayMarketAdapter(var appList:List<Program>):RecyclerView.Adapter<InPlayMarketAdapter.VH>() {

    inner class VH(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(program: Program){
            Picasso.get().load(program.imgUrl).into(itemView.image)
            itemView.app_name.text = program.appName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_in_play_market, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(appList[position])
    }

    override fun getItemCount(): Int = appList.size
}