package com.otamurod.milliytaomlar

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.milliytaomlar.db.MyDbHelper
import kotlinx.android.synthetic.main.activity_fifth.*

class FifthActivity : AppCompatActivity() {

    lateinit var myDbHelper: MyDbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)

        val id = intent.getIntExtra("id", 0)

        myDbHelper = MyDbHelper(this)
        val foodById = myDbHelper.getFoodById(id)

        name_et.setText(foodById.name)
        products_edit.setText(foodById.products)
        steps_edit.setText(foodById.steps)

        update.setOnClickListener {

            val name = name_et.text.toString()
            val product = products_edit.text.toString()
            val step = steps_edit.text.toString()

            foodById.name = name
            foodById.products = product
            foodById.steps = step
            myDbHelper.updateFood(foodById)

            Toast.makeText(this, "Ma'lumot yangilandi", Toast.LENGTH_SHORT).show()
            finish()

        }
    }
}