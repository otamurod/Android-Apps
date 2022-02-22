package com.otamurod.designs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment1 = Fragment1()
        val fragment2 = Fragment2()

        btn1.setOnClickListener {

            if (counter == 0) {
                val beginTransaction = supportFragmentManager.beginTransaction()
                beginTransaction.add(R.id.container, fragment1)
                beginTransaction.addToBackStack(fragment1.toString())
                beginTransaction.commit()
                counter++
            }else if(counter == 1){
                val beginTransaction = supportFragmentManager.beginTransaction()
                beginTransaction.replace(R.id.container, fragment2)
                beginTransaction.addToBackStack(fragment2.toString())
                beginTransaction.commit()
                counter++
            }

        }

    }

    override fun onBackPressed() {
        counter--
        super.onBackPressed()
    }
}