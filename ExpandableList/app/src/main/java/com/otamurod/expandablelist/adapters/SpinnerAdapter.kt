package com.otamurod.expandablelist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.otamurod.expandablelist.R
import com.otamurod.expandablelist.models.User
import kotlinx.android.synthetic.main.spinner_item.view.*

class SpinnerAdapter(var list: List<User>)
    :BaseAdapter() {


    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): User {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var itemView:View
        if (p1 == null){
            itemView = LayoutInflater.from(p2?.context).inflate(R.layout.spinner_item, p2, false)
        }else{
            itemView = p1
        }

        itemView.image.setImageResource(list[p0].img)
        itemView.spinner_tv.text = list[p0].name

        return itemView
    }
}