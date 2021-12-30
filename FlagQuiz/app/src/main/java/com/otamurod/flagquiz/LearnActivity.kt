package com.otamurod.flagquiz

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.Menu
import android.widget.BaseAdapter
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_learn.*
import kotlinx.android.synthetic.main.activity_menu.view.*
import kotlinx.android.synthetic.main.flag_ticket.view.*

class LearnActivity : AppCompatActivity() {

    var adapter:FlagAdapter ?= null
    var listOfFlags = ArrayList<Country>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)

        listOfFlags.add(Country("Uzbekistan", R.drawable.flag_of_uzbekistan))
        listOfFlags.add(Country("Armenia", R.drawable.flag_of_armenia))
        listOfFlags.add(Country("Azerbaijan", R.drawable.flag_of_azerbaijan))
        listOfFlags.add(Country("Belarus", R.drawable.flag_of_belarus))
        listOfFlags.add(Country("Georgia", R.drawable.flag_of_georgia))
        listOfFlags.add(Country("Kazakhstan", R.drawable.flag_of_kazakhstan))
        listOfFlags.add(Country("Kyrgyzstan", R.drawable.flag_of_kyrgyzstan))
        listOfFlags.add(Country("Moldova", R.drawable.flag_of_moldova))
        listOfFlags.add(Country("Russia", R.drawable.flag_of_russia))
        listOfFlags.add(Country("Tajikistan", R.drawable.flag_of_tajikistan))
        listOfFlags.add(Country("Turkmenistan", R.drawable.flag_of_turkmenistan))
        listOfFlags.add(Country("Ukraine", R.drawable.flag_of_ukraine))

        adapter = FlagAdapter(this, listOfFlags)

        gridViewListFlags.adapter = adapter

    }

    class FlagAdapter: BaseAdapter {

        var listOfFlags = ArrayList<Country>()
        var context: Context?= null
        constructor(context: Context, listOfFlags:ArrayList<Country>):super(){
            this.context = context
            this.listOfFlags = listOfFlags
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            val country = this.listOfFlags[p0]
            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var flagView = inflater.inflate(R.layout.flag_ticket, null)

            flagView.image_flag.setImageResource(country.flag!!)
            flagView.flag.text = country.name!!

            return flagView
        }

        override fun getCount(): Int {
            return listOfFlags.size
        }

        override fun getItem(p0: Int): Any {
            return listOfFlags[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.quiz ->{
                finish()
                val intent = Intent("quiz")
                startActivity(intent)
            }
            R.id.learn ->{
                onRestart()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}