package com.otamurod.pdpuz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.pdpuz.database.AppDatabase
import com.otamurod.pdpuz.databinding.TicketGroupBinding
import com.otamurod.pdpuz.entities.Group

class GroupAdapter(
    context: Context,
    var list: ArrayList<Group>,
    var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<GroupAdapter.VH>() {

    val database = AppDatabase.getInstance(context)

    inner class VH(var groupBinding: TicketGroupBinding) :
        RecyclerView.ViewHolder(groupBinding.root) {

        fun onBind(group: Group, position: Int) {

            val studentsCount = database.studentDao().getStudentsInGroup(group.groupName!!)

            groupBinding.groupName.text = group.groupName
            groupBinding.numberOfStudents.text = "Talabalar soni: $studentsCount ta"

            groupBinding.viewIcon.setOnClickListener {
                onItemClickListener.onViewClick(group, position)
            }

            groupBinding.editIcon.setOnClickListener {
                onItemClickListener.onEditClick(group, position)
            }

            groupBinding.deleteIcon.setOnClickListener {
                onItemClickListener.onDeleteClick(group, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(TicketGroupBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onViewClick(group: Group, position: Int)
        fun onEditClick(group: Group, position: Int)
        fun onDeleteClick(group: Group, position: Int)
    }

}