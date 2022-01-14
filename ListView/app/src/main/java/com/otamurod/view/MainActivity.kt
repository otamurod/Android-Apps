package com.otamurod.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.otamurod.view.adapters.ImageBaseAdapter
import com.otamurod.view.models.ImageData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var list = ArrayList<ImageData>()

/*    var stringList = ArrayList<String>()
    lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var imageAdapter: ImageAdapter*/

    lateinit var imageBaseAdapter: ImageBaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
//        imageAdapter = ImageAdapter(this, list)

        imageBaseAdapter = ImageBaseAdapter(list)
//        list_view.adapter = imageAdapter

        list_view.adapter = imageBaseAdapter

        list_view.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent("second")

            intent.putExtra("image", list[position])
            startActivity(intent)
        }

        list_view.setOnItemLongClickListener { parent, view, position, id ->
            Toast.makeText(this, "${list[position].text} is removed", Toast.LENGTH_SHORT).show()
            list.removeAt(position)
            imageBaseAdapter.notifyDataSetChanged() //reload

            true
        }

        /*initUI()
        loadStringList()
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stringList)

        list_view.adapter = arrayAdapter
        list_view.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent("second")
            intent.putExtra("name", stringList[position])

            startActivity(intent)

        }*/

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.list_menu -> {
                onResume()
                true
            }
            R.id.grid_menu -> {
                finish()
                val intent = Intent("third")
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

   /* private fun loadStringList() {
        for (i in 0 until  100){
            stringList.add("List Item $i")
        }
    }*/

    /* private fun initUI() {
         for (i in 0 until list.size){
             val itemView = LayoutInflater.from(this).inflate(R.layout.item, null, false)
             itemView.tv.text = list[i].text
             Picasso.get().load(list[i].imgUrl).into(itemView.img_view)

             container.addView(itemView)
         }
     }*/

    private fun loadData() {
        list.add(ImageData("https://cdn.icon-icons.com/icons2/2235/PNG/512/android_os_logo_icon_134673.png", "Android"))
        list.add(ImageData("https://upload.wikimedia.org/wikipedia/commons/7/74/Kotlin_Icon.png", "Kotlin"))
        list.add(ImageData("https://img2.freepng.ru/20180404/ebw/kisspng-java-programming-computer-programming-programming-coffee-jar-5ac598db779939.2171835915228991634899.jpg","Java"))
        list.add(ImageData("https://icons-for-free.com/iconfiles/png/512/brand+brands+ios+logo+logos+icon-1320184727559829845.png", "iOS"))
        list.add(ImageData("https://ih0.redbubble.net/image.415946483.7473/flat,1000x1000,075,f.u1.jpg", "Swift"))
        list.add(ImageData("https://logowik.com/content/uploads/images/flutter5786.jpg", "Flutter"))
        list.add(ImageData("https://www.kindpng.com/picc/m/176-1766682_dart-programming-language-hd-png-download.png", "Dart"))
        list.add(ImageData("https://www.vhv.rs/dpng/d/456-4562295_library-of-javascript-icon-graphic-freeuse-png-files.png", "Javascript"))
        list.add(ImageData("http://assets.stickpng.com/images/5848152fcef1014c0b5e4967.png", "Python"))
        list.add(ImageData("https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Ruby_logo.svg/1024px-Ruby_logo.svg.png", "Ruby"))
    }
}