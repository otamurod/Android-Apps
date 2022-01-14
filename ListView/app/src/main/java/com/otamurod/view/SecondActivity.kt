package com.otamurod.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.otamurod.view.models.ImageData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val intent = intent
        val data = intent.getSerializableExtra("image") as ImageData

        Picasso.get().load(data.imgUrl).into(image)
        text_view.text = data.text
    }
}