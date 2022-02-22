package com.otamurod.multipletabledb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.otamurod.multipletabledb.databinding.ItemCustomerBinding
import com.otamurod.multipletabledb.models.Customer

class CustomerAdapter(var list: List<Customer>) : BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Customer {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var customerViewHolder: CustomerViewHolder? = null

        if (convertView == null) {

            val binding = ItemCustomerBinding.inflate(
                LayoutInflater.from(parent?.context),
                parent,
                false
            )
            customerViewHolder = CustomerViewHolder(binding)

        }else{
            customerViewHolder = CustomerViewHolder(ItemCustomerBinding.bind(convertView))
        }
        customerViewHolder.itemCustomerBinding.itemCustomerTv.text = list[position].name

        return customerViewHolder.itemView
    }

    inner class CustomerViewHolder {
        val itemView: View
        var itemCustomerBinding: ItemCustomerBinding

        constructor(itemCustomerBinding: ItemCustomerBinding) {
            itemView = itemCustomerBinding.root
            this.itemCustomerBinding = itemCustomerBinding
        }
    }
}