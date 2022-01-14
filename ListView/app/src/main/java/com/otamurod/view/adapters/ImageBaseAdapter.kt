package com.otamurod.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.otamurod.view.R
import com.otamurod.view.models.ImageData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class ImageBaseAdapter(var list: List<ImageData>):BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(p0: Int): ImageData = list[p0]

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var itemView:View
        if (p1 == null){
            itemView = LayoutInflater.from(p2?.context).inflate(R.layout.list_item, p1, false)
        }else{
            itemView = p1
        }

        Picasso.get().load(list[p0].imgUrl).into(itemView.img_view)
        itemView.tv.text = list[p0].text

        return itemView
    }
}