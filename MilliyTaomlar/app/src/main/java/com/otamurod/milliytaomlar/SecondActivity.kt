package com.otamurod.milliytaomlar

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.milliytaomlar.adapters.FoodAdapter
import com.otamurod.milliytaomlar.db.MyDbHelper
import com.otamurod.milliytaomlar.models.Food
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    lateinit var myDbHelper: MyDbHelper
    lateinit var list: List<Food>
    lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        myDbHelper = MyDbHelper(this)

        list = myDbHelper.getAllFoods()

        foodAdapter = FoodAdapter(list, object : FoodAdapter.OnItemClickListener {

            override fun onItemFoodClick(food: Food) {
                val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
                intent.putExtra("id", food.id)
                startActivity(intent)
                finish()
            }

        })

        food_list.adapter = foodAdapter
    }
}