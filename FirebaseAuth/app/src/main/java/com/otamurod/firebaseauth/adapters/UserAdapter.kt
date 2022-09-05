package com.otamurod.firebaseauth.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.firebaseauth.adapters.UserAdapter.VH
import com.otamurod.firebaseauth.databinding.ItemUserBinding
import com.otamurod.firebaseauth.models.User
import com.squareup.picasso.Picasso

class UserAdapter(
    var list: List<User>,
    var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<VH>() {

    inner class VH(var itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {

        fun onBind(user: User) {
            itemUserBinding.nameTv.text = user.displayName
            itemUserBinding.emailTv.text = user.email


            Picasso.get().load(user.photoUrl).into(itemUserBinding.image)

            itemView.setOnClickListener {
                onItemClickListener.onItemClick(user)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {

        return list.size
    }

    interface OnItemClickListener {
        fun onItemClick(user: User)
    }


}