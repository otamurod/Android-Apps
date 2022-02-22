package com.otamurod.milliytaomlar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.milliytaomlar.db.MyDbHelper
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {

    lateinit var myDbHelper: MyDbHelper
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val id = intent.getIntExtra("id", 0)

        myDbHelper = MyDbHelper(this)
        val foodById = myDbHelper.getFoodById(id)

        name.text = foodById.name
        products.text = foodById.products
        steps.text = foodById.steps

        var counter = 0
        delete_btn.setOnClickListener {
            if (counter == 0) {
                Toast.makeText(this, "O'chirish uchun\nTugmani yana bosing", Toast.LENGTH_SHORT)
                    .show()
            }
            if (counter > 0) {
                myDbHelper.deleteFood(foodById)
                finish()
            }
            counter++

        }

        update_btn.setOnClickListener {
            val intent = Intent(this, FifthActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
            finish()
        }

    }
}