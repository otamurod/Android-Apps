package com.otamurod.ussd.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.otamurod.ussd.R
import com.otamurod.ussd.models.Packet
import kotlinx.android.synthetic.main.ticket1.view.*

class ListAdapter(context: Context, var list: List<Packet>) :
    ArrayAdapter<Packet>(context, R.layout.ticket1, list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var itemView:View

        if (convertView == null){
            itemView = LayoutInflater.from(parent.context).inflate(R.layout.ticket1, parent, false)
        }else{
            itemView = convertView
        }

        itemView.name.text = list[position].name
        itemView.code.text = list[position].code
        itemView.info_short.text = list[position].info

        return itemView
    }
}