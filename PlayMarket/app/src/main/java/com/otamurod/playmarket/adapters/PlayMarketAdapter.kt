package com.otamurod.playmarket.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.playmarket.R
import com.otamurod.playmarket.models.GeneralData
import kotlinx.android.synthetic.main.item_play_market.view.*

class PlayMarketAdapter(var generalList: List<GeneralData>) :RecyclerView.Adapter<PlayMarketAdapter.VH>() {

    inner class VH(itemView: View):RecyclerView.ViewHolder(itemView){
        fun onBind(generalData: GeneralData){
            itemView.title_tv.text = generalData.title

            val inPlayMarketAdapter = InPlayMarketAdapter(generalData.appList!!)
            itemView.rv_inside.adapter = inPlayMarketAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_play_market, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(generalList[position])
    }

    override fun getItemCount(): Int = generalList.size
}