package com.otamurod.multipletabledb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.otamurod.multipletabledb.databinding.ItemEmployeeBinding
import com.otamurod.multipletabledb.models.Employee

class EmployeeAdapter(var list: List<Employee>) : BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var employeeViewHolder: EmployeeViewHolder? = null

        if (convertView == null) {

            val binding = ItemEmployeeBinding.inflate(
                LayoutInflater.from(parent?.context),
                parent,
                false
            )
            employeeViewHolder = EmployeeViewHolder(binding)

        }else{
            employeeViewHolder = EmployeeViewHolder(ItemEmployeeBinding.bind(convertView))
        }
        employeeViewHolder.itemEmployeeBinding.itemEmployeeTv.text = list[position].name

        return employeeViewHolder.itemView
    }

    inner class EmployeeViewHolder {
        val itemView: View
        var itemEmployeeBinding: ItemEmployeeBinding

        constructor(itemEmployeeBinding: ItemEmployeeBinding) {
            itemView = itemEmployeeBinding.root
            this.itemEmployeeBinding = itemEmployeeBinding
        }
    }
}