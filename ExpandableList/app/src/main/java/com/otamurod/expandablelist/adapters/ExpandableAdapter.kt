package com.otamurod.expandablelist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.otamurod.expandablelist.R
import kotlinx.android.synthetic.main.child_item.view.*
import kotlinx.android.synthetic.main.group_item.view.*

class ExpandableAdapter(var titleList: List<String>,
                        var map: HashMap<String, List<String>>):
    BaseExpandableListAdapter() {


    override fun getGroupCount(): Int = titleList.size

    override fun getChildrenCount(groupPosition: Int): Int {
        return map[titleList[groupPosition]]?.size!!
    }

    override fun getGroup(groupPosition: Int): String {
        return titleList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        val list = map[titleList[groupPosition]]
        return list?.get(childPosition)!!
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var groupItemView: View
        if (convertView == null){
            groupItemView = LayoutInflater.from(parent?.context).inflate(R.layout.group_item, parent, false)
        }else{
            groupItemView = convertView
        }

        groupItemView.group_tv.text = titleList[groupPosition]

        return groupItemView
    }
    
    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        var childItemView: View
        if (convertView == null){
            childItemView = LayoutInflater.from(parent?.context).inflate(R.layout.child_item, parent, false)
        }else{
            childItemView = convertView
        }
        val list = map[titleList[groupPosition]]
        val childItem = list?.get(childPosition)
        childItemView.child_tv.text = childItem

        return childItemView
    }
    

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}