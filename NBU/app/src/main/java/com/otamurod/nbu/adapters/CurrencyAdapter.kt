package com.otamurod.nbu.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.nbu.databinding.ItemCurrencyBinding
import com.otamurod.nbu.models.Currency

class CurrencyAdapter(
    private var list: List<Currency>,
    val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<CurrencyAdapter.VH>() {

    inner class VH(private var currencyBinding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(currencyBinding.root) {

        fun onBInd(currency: Currency, position: Int) {
            currencyBinding.Ccy.text = currency.Ccy

            currencyBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(currency, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        return VH(ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBInd(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onItemClick(currency: Currency, position: Int)
    }
}