package com.otamurod.passportapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.passportapp.databinding.ItemCitizenBinding
import com.otamurod.passportapp.entities.Citizen


class CitizenAdapter(var list: ArrayList<Citizen>, var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<CitizenAdapter.VH>(), Filterable {

    private val citizenList = ArrayList<Citizen>(list)

    inner class VH(var itemCitizenBinding: ItemCitizenBinding) :
        RecyclerView.ViewHolder(itemCitizenBinding.root) {

        fun onBind(citizen: Citizen, position: Int) {
            itemCitizenBinding.name.text =
                String.format("%d. %s %s", position + 1, citizen.firstName, citizen.lastName)
            itemCitizenBinding.passportNo.text = String.format("%s", citizen.passportNo)

            itemCitizenBinding.go.setOnClickListener {
                onItemClickListener.onItemClick(citizen.id!!)
            }
            /*itemCitizenBinding.more.setOnClickListener {
                onItemClickListener.onItemDeleteClick(citizen, position)
            }*/

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCitizenBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
//        fun onItemDeleteClick(citizen: Citizen, position: Int)
    }

    override fun getFilter(): Filter {
        return newFilter
    }

    var newFilter: Filter = object : Filter() {
        //runs on background thread
        override fun performFiltering(charsequence: CharSequence): FilterResults {

            val filteredList = ArrayList<Citizen>()

            if (charsequence.toString().isEmpty()) {
                filteredList.addAll(citizenList)
            } else {

                for (citizen in citizenList) {
                    if (citizen.firstName.toLowerCase().contains(
                            charsequence.toString().toLowerCase()
                        ) or citizen.lastName.toLowerCase()
                            .contains(charsequence.toString().toLowerCase())
                    ) {
                        filteredList.add(citizen)
                    }
                }

            }

            val filterResults = FilterResults()
            filterResults.values = filteredList

            return filterResults
        }

        //runs on UI thread
        override fun publishResults(charsequence: CharSequence, filterResults: FilterResults) {
            list.clear()
            val citizensFiltered = filterResults.values as ArrayList<Citizen>

            list.addAll(citizensFiltered)

            notifyDataSetChanged()
        }

    }

}
