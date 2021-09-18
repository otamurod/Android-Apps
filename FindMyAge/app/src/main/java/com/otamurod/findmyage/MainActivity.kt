package com.otamurod.findmyage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getAge.setOnClickListener{
            //call function
            getUserAge()
        }
    }

    fun getUserAge(){ //function on button click

        val userDOB = Integer.parseInt(DOB.text.toString()) //read user's year of birth
        val currentYear = Calendar.getInstance().get(Calendar.YEAR) //get current year
        val userAge = currentYear - userDOB //user's age
        //display age
        txtShow.text = "You are at $userAge"
    }

}