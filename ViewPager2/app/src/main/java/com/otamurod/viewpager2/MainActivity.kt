package com.otamurod.viewpager2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.otamurod.viewpager2.adapters.ImageAdapter
import com.otamurod.viewpager2.adapters.ImageStateAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var list: ArrayList<String>
    lateinit var imageAdapter: ImageAdapter
    lateinit var imageStateAdapter: ImageStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = ArrayList()

        list.add("https://antonioleiva.com/wp-content/uploads/2018/08/kotlin-guide-cover.png")
        list.add("https://devexperto.com/wp-content/uploads/2017/01/kotlin-guia-cover.png")
        list.add("https://antonioleiva.com/wp-content/uploads/2015/08/kotlin-libro.jpg")

        imageAdapter = ImageAdapter(list)

        imageStateAdapter = ImageStateAdapter(list, this)

//        view_pager2.adapter = imageAdapter
        view_pager2.adapter = imageStateAdapter

        view_pager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Toast.makeText(this@MainActivity, "$position", Toast.LENGTH_SHORT).show()
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                return super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageScrollStateChanged(state: Int) {
                return super.onPageScrollStateChanged(state)
            }
        })

        TabLayoutMediator(
            tab_layout,
            view_pager2,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->

                tab.text = "$position"
            }).attach()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.pager -> {
                true
            }
            R.id.bottom_nav -> {
                val intent = Intent("bottom.navigation")
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}