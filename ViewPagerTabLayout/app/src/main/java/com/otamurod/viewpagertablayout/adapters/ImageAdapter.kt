package com.otamurod.viewpagertablayout.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.otamurod.viewpagertablayout.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_pager.view.*

class ImageAdapter(var imageList: List<String>) : PagerAdapter() {

    private var inflater: LayoutInflater? = null

    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        inflater = LayoutInflater.from(container.context)
        val itemView = inflater?.inflate(R.layout.item_view_pager, container, false)

        Picasso.get().load(imageList[position]).into(itemView!!.image)

        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}