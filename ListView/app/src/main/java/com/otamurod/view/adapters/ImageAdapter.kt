package com.otamurod.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.otamurod.view.R
import com.otamurod.view.models.ImageData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_item.view.*

class ImageAdapter(context: Context, var list: List<ImageData>) :
    ArrayAdapter<ImageData>(context, R.layout.grid_item, list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var itemView:View

        if (convertView == null){
            itemView = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        }else{
            itemView = convertView
        }
        Picasso.get().load(list[position].imgUrl).into(itemView.img_view)
        itemView.tv.text = list[position].text

        return itemView
    }
}