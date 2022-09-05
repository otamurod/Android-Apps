package com.otamurod.networking.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.otamurod.networking.databinding.ItemUserBinding
import com.otamurod.networking.models.User

class UserAdapter(val context: Context, private var list: List<User>) :
    RecyclerView.Adapter<UserAdapter.VH>() {

    inner class VH(private var userBinding: ItemUserBinding) : RecyclerView.ViewHolder(userBinding.root) {

        fun onBInd(user: User) {
            userBinding.login.text = user.login
            Glide.with(context).load(user.avatar_url).into(userBinding.image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        return VH(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBInd(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}