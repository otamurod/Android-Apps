package com.otamurod.ussd.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.otamurod.ussd.R
import com.otamurod.ussd.models.ImageData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.ticket.view.*

class ImageAdapter(context: Context, var list: List<ImageData>) :
    ArrayAdapter<ImageData>(context, R.layout.ticket, list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var itemView:View

        if (convertView == null){
            itemView = LayoutInflater.from(parent.context).inflate(R.layout.ticket, parent, false)
        }else{
            itemView = convertView
        }
//        Picasso.get().load(list[position].imgUrl).into(itemView.home_img)
        itemView.home_tv.text = list[position].text
        itemView.home_img.setImageResource(list[position].imgUrl)

        return itemView
    }
}