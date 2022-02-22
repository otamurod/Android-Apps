package com.otamurod.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()

        add_btn.setOnClickListener {

            val supportFragmentManager = supportFragmentManager
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.add(R.id.container, firstFragment)
            beginTransaction.addToBackStack(firstFragment.toString())
            beginTransaction.commit()

        }

        remove_btn.setOnClickListener {

            val supportFragmentManager = supportFragmentManager
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.remove(firstFragment)
            supportFragmentManager.popBackStack()
            beginTransaction.commit()

        }

        replace_btn.setOnClickListener {

            val supportFragmentManager = supportFragmentManager
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.container, secondFragment)
            beginTransaction.addToBackStack(secondFragment.toString())
            beginTransaction.commit()

        }

        hide_btn.setOnClickListener {

            val supportFragmentManager = supportFragmentManager
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.hide(firstFragment)
            beginTransaction.commit()

        }

        show_btn.setOnClickListener {

            val supportFragmentManager = supportFragmentManager
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.show(firstFragment)
            beginTransaction.commit()

        }

    }
}