package com.otamurod.zooapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var adapter:AnimalsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load animals
        listOfAnimals.add(Animal("Baboon", "Baboon lives in Jungle", R.drawable.baboon, false))
        listOfAnimals.add(Animal("Bulldog", "Bulldog is feed by people", R.drawable.bulldog, false))
        listOfAnimals.add(Animal("Panda", "Panda lives in China's forests", R.drawable.panda, false))
        listOfAnimals.add(Animal("Swallow", "Swallow lives in Jungle", R.drawable.swallow_bird, false))
        listOfAnimals.add(Animal("White-Tiger", "Tiger lives in Jungle", R.drawable.white_tiger, true))
        listOfAnimals.add(Animal("Zebra", "Zebra lives in Africa's Savanna", R.drawable.zebra, false))

        adapter = AnimalsAdapter(this, listOfAnimals) //initialize
        tvListAnimal.adapter = adapter
    }

    fun delete(index:Int){
        listOfAnimals.removeAt(index) //delete animal
        adapter!!.notifyDataSetChanged()
    }
    fun add(index:Int){
        listOfAnimals.add(index, listOfAnimals[index]) //delete animal
        adapter!!.notifyDataSetChanged()
    }

    inner class AnimalsAdapter:BaseAdapter{

        var listOfAnimals = ArrayList<Animal>()
        var context: Context? = null

        constructor(context: Context, listOfAnimals: ArrayList<Animal>):super(){
            this.context = context
            this.listOfAnimals = listOfAnimals
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }

        override fun getItem(p0: Int): Any {
            return listOfAnimals[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View { //will be called # of times returned from getCount()
            val animal = listOfAnimals[p0]
            if (animal.isKiller == true){
                var inflator =
                    context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_killer_ticket, null)
                myView.textViewName.text = animal.name!!
                myView.textViewDesc.text = animal.des!!
                myView.imageViewName.setImageResource(animal.image!!)
                myView.imageViewName.setOnClickListener(){

//                    delete(p0) //delete item
//                    add(p0) //add item
                    val intent = Intent(context, AnimalInfo::class.java)

                    intent.putExtra("name", animal.name!!)
                    intent.putExtra("des", animal.des!!)
                    intent.putExtra("image", animal.image!!)

//                    startActivity(context!!, intent, Bundle())
                    context!!.startActivity(intent)
                }

                return myView
            }else {
                var inflator =
                    context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_ticket, null)
                myView.textViewName.text = animal.name!!
                myView.textViewDesc.text = animal.des!!
                myView.imageViewName.setImageResource(animal.image!!)
                myView.imageViewName.setOnClickListener(){

//                    delete(p0) //delete item
//                    add(p0) //add item

                    val intent = Intent(context, AnimalInfo::class.java)

                    intent.putExtra("name", animal.name!!)
                    intent.putExtra("des", animal.des!!)
                    intent.putExtra("image", animal.image!!)

//                    startActivity(context!!, intent, Bundle())
                    context!!.startActivity(intent)

                }

                return myView
            }
        }

    }
}