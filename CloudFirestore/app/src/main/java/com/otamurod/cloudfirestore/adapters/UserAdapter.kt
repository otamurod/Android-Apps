package com.otamurod.cloudfirestore.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.otamurod.cloudfirestore.R
import com.otamurod.cloudfirestore.databinding.ItemUserBinding
import com.otamurod.cloudfirestore.models.User

class UserAdapter(val context: Context, var list: List<User>) :
    RecyclerView.Adapter<UserAdapter.VH>() {

    inner class VH(private val itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {

        fun onBind(user: User) {
            itemUserBinding.nameTv.text = user.name
            itemUserBinding.ageTv.text = user.age.toString()
            Glide.with(context).load(user.imgUrl).placeholder(R.drawable.ic_launcher_foreground)
                .into(itemUserBinding.image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.VH {
        return VH(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}