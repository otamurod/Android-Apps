package com.otamurod.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.recyclerview.R
import com.otamurod.recyclerview.models.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter(
    var contactList: List<Contact>,
    var onMyItemClickListener: OnMyItemClickListener
):RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    inner class MyViewHolder(var itemView:View):RecyclerView.ViewHolder(itemView){

        fun onBind(contact: Contact, position: Int){
            itemView.name.text = contact.name
            itemView.number.text = contact.number

            itemView.setOnClickListener {
                onMyItemClickListener.onMyItemClick(contact, position)
            }

            itemView.setOnLongClickListener{
                onMyItemClickListener.onMyItemLongClick(contact)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contact = contactList[position]
        holder.onBind(contact, position)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }


    interface OnMyItemClickListener{
        fun onMyItemClick(contact: Contact, position: Int)
        fun onMyItemLongClick(contact: Contact)
    }
}