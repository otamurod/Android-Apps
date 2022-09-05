package com.otamurod.realmdatabase.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.otamurod.realmdatabase.databinding.ItemContactBinding
import com.otamurod.realmdatabase.models.Contact
import io.realm.RealmResults

class ContactAdapater(var list: RealmResults<Contact>, var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ContactAdapater.VH>() {

    inner class VH(var itemContactBinding: ItemContactBinding) :
        RecyclerView.ViewHolder(itemContactBinding.root) {

        fun onBind(contact: Contact, position: Int) {

            itemContactBinding.tv1.text = contact.name
            itemContactBinding.tv2.text = contact.number

            itemContactBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(contact, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position]!!, position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener{
        fun onItemClick(contact: Contact, position: Int)
    }
}