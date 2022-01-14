package com.otamurod.draganddrop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.draganddrop.R
import com.otamurod.draganddrop.models.User
import com.otamurod.draganddrop.utils.ItemTouchHelperAdapter
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.*
import kotlin.collections.ArrayList

class UserAdapter2(var context: Context, var userList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter2.Vh>(), ItemTouchHelperAdapter{

    inner class Vh(itemView: View):RecyclerView.ViewHolder(itemView){
        fun onBind(user:User){
            itemView.tv1.text = user.username
            itemView.tv2.text = user.password

            itemView.animation = AnimationUtils.loadAnimation(context, R.anim.anim1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val user = userList[position]
        holder.onBind(user)
    }

    override fun getItemCount(): Int = userList.size

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition){
            for (i in fromPosition until toPosition){
                Collections.swap(userList, i, i+1)
            }
        }else{
            for (i in fromPosition downTo toPosition+1){
                Collections.swap(userList, i, i-1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        userList.removeAt(position)
        notifyItemRemoved(position)
    }
}