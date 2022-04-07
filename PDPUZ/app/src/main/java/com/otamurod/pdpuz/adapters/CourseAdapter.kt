package com.otamurod.pdpuz.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.pdpuz.databinding.TicketCourseBinding
import com.otamurod.pdpuz.entities.Course
import java.util.*

class CourseAdapter(var list: List<Course>, var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<CourseAdapter.VH>() {

    inner class VH(var courseBinding: TicketCourseBinding) :
        RecyclerView.ViewHolder(courseBinding.root) {

        fun onBind(course: Course, position: Int) {

            val tokens = StringTokenizer(course.name, " ")
            val first: String = tokens.nextToken()
            val second: String = tokens.nextToken()

            courseBinding.groupNameTv.text = first
            courseBinding.tv.text = second

            courseBinding.ticket.setOnClickListener {
                onItemClickListener.onItemClick(list[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(TicketCourseBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onItemClick(course: Course)
    }

}