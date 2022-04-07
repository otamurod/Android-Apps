package com.otamurod.basicuicomponents

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_second)

        val button = Button(this)
        button.text = getString(R.string.second_activity)

        container.addView(button)


        /**
        val linearLayout = LinearLayout(this)
        val linearLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        val textView = TextView(this)
        textView.text = getString(R.string.dynamic_text)
        textView.setTextColor(resources.getColor(R.color.white))
        textView.textSize = 26F

        linearLayout.addView(textView)

        linearLayout.setBackgroundColor(Color.GREEN)
        linearLayout.gravity = Gravity.CENTER
        linearLayout.layoutParams = linearLayoutParams
        setContentView(linearLayout, linearLayoutParams)
         */

    }
}