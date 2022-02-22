package com.otamurod.multipletabledb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.multipletabledb.databinding.ItemOrderBinding
import com.otamurod.multipletabledb.models.Orders

class OrderAdapter(var list: ArrayList<Orders>) : RecyclerView.Adapter<OrderAdapter.VH>() {

    inner class VH(var itemOrderBinding: ItemOrderBinding) :
        RecyclerView.ViewHolder(itemOrderBinding.root) {

        fun onBind(orders: Orders) {

            itemOrderBinding.itemOrderTv1.text = orders.customer?.name
            itemOrderBinding.itemOrderTv2.text = orders.employee?.name
            itemOrderBinding.itemOrderTv3.text = orders.orderDate

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemOrderBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}