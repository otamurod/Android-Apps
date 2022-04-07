package com.otamurod.pdpuz.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.pdpuz.databinding.TicketMentorBinding
import com.otamurod.pdpuz.entities.Mentor

class MentorAdapter(var list: List<Mentor>, var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MentorAdapter.VH>() {

    inner class VH(var mentorBinding: TicketMentorBinding) :
        RecyclerView.ViewHolder(mentorBinding.root) {

        fun onBind(mentor: Mentor, position: Int) {

            mentorBinding.mentorName.text = "${mentor.lastName} ${mentor.firstName}"
            mentorBinding.mentorMiddleName.text = mentor.middleName

            mentorBinding.editIcon.setOnClickListener {
                onItemClickListener.onEditClick(mentor, position)
            }

            mentorBinding.deleteIcon.setOnClickListener {
                onItemClickListener.onDeleteClick(mentor, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(TicketMentorBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onEditClick(mentor: Mentor, position: Int)
        fun onDeleteClick(mentor: Mentor, position: Int)
    }

}