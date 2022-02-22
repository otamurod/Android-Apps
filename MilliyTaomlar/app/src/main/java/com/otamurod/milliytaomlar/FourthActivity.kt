package com.otamurod.milliytaomlar

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.milliytaomlar.db.MyDbHelper
import com.otamurod.milliytaomlar.models.Food
import kotlinx.android.synthetic.main.activity_fourth.*
import kotlinx.android.synthetic.main.activity_third.*

class FourthActivity : AppCompatActivity() {

    lateinit var myDbHelper: MyDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        myDbHelper = MyDbHelper(this)

        save_btn.setOnClickListener {
            if (!food_name_edit_text.text.isEmpty()) {
                val foodName = food_name_edit_text.text.toString()
                val products = products_edit_text.text.toString()
                val steps = steps_edit_text.text.toString()

                val food = Food(foodName, products, steps)

                myDbHelper.addFood(food)
                finish()
            } else {
                Toast.makeText(this, "Saqlash uchun ma'lumot kiriting", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

}