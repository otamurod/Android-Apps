package com.otamurod.viewpagertablayout

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer
import com.otamurod.viewpagertablayout.adapters.ImageAdapter
import com.otamurod.viewpagertablayout.adapters.UserPagerAdapter
import com.otamurod.viewpagertablayout.models.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var imageList: ArrayList<String>
    lateinit var imageAdapter: ImageAdapter
    lateinit var userList: ArrayList<User>
    lateinit var userPagerAdapter: UserPagerAdapter
    lateinit var titleList: ArrayList<String>
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userList = ArrayList()
//        for (i in 1..10){
        userList.add(User("My Profile", "https://avatars.githubusercontent.com/u/45229689?v=4"))
        userList.add(User("Otamurod Safarov", "https://otamurod.github.io/img/my_picture.jpg"))
        userList.add(User("Safarov Otamurod", "https://scontent.ftas2-1.fna.fbcdn.net/v/t39.30808-6/245959763_399609598287614_8216926223451720530_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=K2AJ34pnM-wAX9bEkIg&tn=sbK0x2nXTbj5hVny&_nc_ht=scontent.ftas2-1.fna&oh=00_AT8DynxpghYK7XcT5yKtbKCLx3Dd5qyexUARTwcRd_UyBg&oe=61F8F0CF"))
//        }

        titleList = ArrayList()
//        for (i in 1..10) {
        titleList.add("Home")
        titleList.add("About Us")
        titleList.add("Contact Us")
//        }
        userPagerAdapter = UserPagerAdapter(userList, titleList, supportFragmentManager)

//        view_pager.setPageTransformer(true, DepthPageTransformer())
        view_pager.setPageTransformer(true, RotateUpTransformer())
        view_pager.adapter = userPagerAdapter
        tab_layout.setupWithViewPager(view_pager)

        handler = Handler()
        handler.postDelayed(runnable, 1500)

//        loadImages()
//        imageAdapter = ImageAdapter(imageList)
//        view_pager.adapter = imageAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.main -> {
                true
            }
            R.id.category -> {
                val intent = Intent("category")
                startActivity(intent)
                finish()
                true
            }
            R.id.indicator -> {
                val intent = Intent("indicator")
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    var runnable = object : Runnable {
        override fun run() {
            view_pager.currentItem += 1
            handler.postDelayed(this, 1500)
        }

    }

    private fun loadImages() {
        imageList = ArrayList()
        imageList.add("https://images.unsplash.com/photo-1504890001746-a9a68eda46e2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTZ8fGhpZ2glMjB0ZWNofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60")
        imageList.add("https://images.unsplash.com/photo-1603788570946-f19e2e9f47b6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjN8fGhpZ2glMjB0ZWNofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60")
        imageList.add("https://images.unsplash.com/photo-1504384308090-c894fdcc538d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzF8fGhpZ2glMjB0ZWNofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60")
        imageList.add("https://images.unsplash.com/photo-1530893609608-32a9af3aa95c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDV8fGhpZ2glMjB0ZWNofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60")
        imageList.add("https://images.unsplash.com/photo-1496283284170-5d0a178f1180?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDd8fGhpZ2glMjB0ZWNofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60")

    }
}