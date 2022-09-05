package com.otamurod.firebaseauth.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.firebaseauth.databinding.ItemFromBinding
import com.otamurod.firebaseauth.databinding.ItemToBinding
import com.otamurod.firebaseauth.models.Message

class MessageAdapter(var list: List<Message>, var uId: String) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class FromVH(var itemFromBinding: ItemFromBinding) :
        RecyclerView.ViewHolder(itemFromBinding.root) {

        fun onBind(message: Message) {
            itemFromBinding.messageTv.text = message.text
            itemFromBinding.dateTv.text = message.date
        }
    }

    inner class ToVH(var itemToBinding: ItemToBinding) :
        RecyclerView.ViewHolder(itemToBinding.root) {

        fun onBind(message: Message) {
            itemToBinding.message.text = message.text
            itemToBinding.date.text = message.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return FromVH(
                ItemFromBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return ToVH(
                ItemToBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == 1) {
            val fromVH = holder as FromVH
            fromVH.onBind(list[position])
        } else {
            val toVH = holder as ToVH
            toVH.onBind(list[position])
        }
    }

    override fun getItemViewType(position: Int): Int {

        if (list[position].fromUid == uId) {
            return 1
        } else {
            return 2
        }
    }

    override fun getItemCount() = list.size
}