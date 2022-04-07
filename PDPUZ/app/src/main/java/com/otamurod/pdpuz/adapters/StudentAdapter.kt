package com.otamurod.pdpuz.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otamurod.pdpuz.databinding.TicketStudentBinding
import com.otamurod.pdpuz.entities.Student

class StudentAdapter(var list: ArrayList<Student>, var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<StudentAdapter.VH>() {

    inner class VH(var studentBinding: TicketStudentBinding) :
        RecyclerView.ViewHolder(studentBinding.root) {

        fun onBind(student: Student, position: Int) {

            studentBinding.studentName.text = "${student.firstName} ${student.lastName}"
            studentBinding.studentMiddleName.text = student.middleName
            studentBinding.date.text = student.date

            studentBinding.editIcon.setOnClickListener {
                onItemClickListener.onEditClick(student, position)
            }

            studentBinding.deleteIcon.setOnClickListener {
                onItemClickListener.onDeleteClick(student, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(TicketStudentBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onEditClick(student: Student, position: Int)
        fun onDeleteClick(student: Student, position: Int)
    }

}