package com.otamurod.draganddrop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.draganddrop.R
import com.otamurod.draganddrop.models.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter : androidx.recyclerview.widget.ListAdapter<User, UserAdapter.MyViewHolder>(MyDiffUtil()) {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(user: User){
            itemView.tv1.text = user.username
            itemView.tv2.text = user.password
        }
    }
    class MyDiffUtil: DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.equals(newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.onBind(user)
    }


}

