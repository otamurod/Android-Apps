package com.otamurod.milliytaomlar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.milliytaomlar.R
import com.otamurod.milliytaomlar.models.Food
import kotlinx.android.synthetic.main.food_card.view.*

class FoodAdapter(var list: List<Food>, var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<FoodAdapter.VH>() {

    inner class VH(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun onBind(food: Food, position: Int) {
            itemView.food_name.text = food.name

            itemView.setOnClickListener {
                onItemClickListener.onItemFoodClick(food)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(R.layout.food_card, parent, false)
        ) //for extensions
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {

        fun onItemFoodClick(food: Food)
    }

}