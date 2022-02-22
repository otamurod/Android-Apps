package com.otamurod.fragments2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

//class MainActivity : AppCompatActivity(), MyInterface {
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.container, firstFragment)
        beginTransaction.commit()

         /*btn.setOnClickListener {

             firstFragment.showData("Otamurod")
 //            setResult("Java Online Bootcamp")
         }*/


    }

    /*override fun setResult(s: String) {
        val firstFragment = supportFragmentManager.findFragmentByTag("first") as FirstFragment
        firstFragment.setResult(s)
    }*/
}