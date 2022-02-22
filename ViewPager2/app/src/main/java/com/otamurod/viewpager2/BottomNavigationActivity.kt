package com.otamurod.viewpager2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        bottom_navigation.setOnNavigationItemSelectedListener {
            val itemId = it.itemId
            when (itemId) {
                R.id.first -> {
                    container.setBackgroundColor(Color.BLUE)
                }
                R.id.second -> {
                    container.setBackgroundColor(Color.CYAN)
                }
                R.id.third -> {
                    container.setBackgroundColor(Color.RED)
                }
                R.id.four -> {
                    container.setBackgroundColor(Color.GREEN)
                }
            }

            true
        }

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
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            R.id.bottom_nav -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}