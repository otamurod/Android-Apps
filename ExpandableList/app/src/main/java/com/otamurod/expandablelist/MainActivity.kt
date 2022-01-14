package com.otamurod.expandablelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.otamurod.expandablelist.adapters.ExpandableAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ExpandableListView.OnGroupExpandListener




class MainActivity : AppCompatActivity() {

    lateinit var map: HashMap<String, List<String>>
    lateinit var titleList: ArrayList<String>
    lateinit var expandableAdapter: ExpandableAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        expandableAdapter = ExpandableAdapter(titleList, map)
        expandable_list_view.setAdapter(expandableAdapter)
        
       /* expandable_list_view.setOnGroupClickListener { parent, view, groupPosition, id ->
            Toast.makeText(this, "${titleList[groupPosition]}", Toast.LENGTH_SHORT).show()
            true
        }*/


        expandable_list_view.setOnGroupCollapseListener {
            Toast.makeText(this, "Collapse -> ${titleList[it]}", Toast.LENGTH_SHORT).show()
        }

        var lastExpandedPosition = -1
        expandable_list_view.setOnGroupExpandListener {
            Toast.makeText(this, "Expand -> ${titleList[it]}", Toast.LENGTH_SHORT).show()

            if (lastExpandedPosition != -1 && it != lastExpandedPosition) {
                expandable_list_view.collapseGroup(lastExpandedPosition)
            }
            lastExpandedPosition = it
        }
        
        expandable_list_view.setOnChildClickListener { parent, view, groupPosition, childPosition, id ->
            Toast.makeText(this, map[titleList[groupPosition]]?.get(childPosition), Toast.LENGTH_SHORT).show()
            true
        }

    }

    private fun loadData() {
        map = HashMap()
        val spainList = arrayListOf("Real Madrid", "Barcelona", "Atletico")
        val englandList = arrayListOf("Manchester United", "Arsenal", "Chelsea")
        val italyList = arrayListOf("Juventus", "Milan", "Napoli")

        map["England"] = englandList
        map["Italy"] = italyList
        map["Spain"] = spainList

        titleList = ArrayList()

        titleList.add("England")
        titleList.add("Italy")
        titleList.add("Spain")

    }
}