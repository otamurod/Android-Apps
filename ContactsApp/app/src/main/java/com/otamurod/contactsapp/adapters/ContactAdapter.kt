package com.otamurod.contactsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.contactsapp.databinding.ItemContactBinding
import com.otamurod.contactsapp.models.Contact

class ContactAdapter(var list: List<Contact>, var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ContactAdapter.VH>() {

    inner class VH(var itemContactBinding: ItemContactBinding) :
        RecyclerView.ViewHolder(itemContactBinding.root) {

        fun onBind(contact: Contact, position: Int) {
            itemContactBinding.name.text = contact.name
            itemContactBinding.phone.text = contact.phoneNumber
            itemContactBinding.img.setOnClickListener{
                onItemClickListener.onItemClick(contact, position, itemContactBinding.img)
            }
            itemContactBinding.root.setOnClickListener {
                onItemClickListener.onItemContactClick(contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
//        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)) //for extensions

        return VH(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )  //for view binding
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener{

        fun onItemClick(contact: Contact, position: Int, imageView: ImageView)
        fun onItemContactClick(contact: Contact)
    }

}