package com.otamurod.flagquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.setMargins
import com.otamurod.flagquiz.models.FlagData
import kotlinx.android.synthetic.main.activity_quiz.*
import java.lang.StringBuilder

class QuizActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var list: ArrayList<FlagData>
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        loadFlags() //load flags
        setData(count)
    }

    private fun setData(count: Int) {
        image_view.setImageResource(list[count].image!!)
        val randomCountryList = randomCountry(list[count].country!!)
        for (i in 0 until 8){

            var button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)
            button.text = randomCountryList[i]

            button.setTextColor(resources.getColor(R.color.white))

            val marginLayoutParams = button.layoutParams as ViewGroup.MarginLayoutParams
            marginLayoutParams.setMargins(2)

            button.setBackgroundColor(resources.getColor(R.color.blue))
            button.setOnClickListener(this)
            linear_layout2.addView(button)

        }

        for (i in 8 until 16){

            var button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)
            button.text = randomCountryList[i]
            button.setTextColor(resources.getColor(R.color.white))

            val marginLayoutParams = button.layoutParams as ViewGroup.MarginLayoutParams
            marginLayoutParams.setMargins(2)

            button.setBackgroundColor(resources.getColor(R.color.blue))
            button.setOnClickListener(this)
            linear_layout3.addView(button)

        }
    }

    private fun randomCountry(string: String):List<String>{
        var stringList = ArrayList<String>()
        var a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val length = string.length

        for (i in 0 until length){
            stringList.add(string[i].toString())
        }

        var b = 16 - length
        var j = 0
        var c = 0
//        val string2 = a.substring(0, b)
        while (c < b){
//            stringList.add(string2[i].toString())
            var char = a.substring(j, j+1).toUpperCase()
            val string = stringList.toString().toUpperCase()
            if ( !(string.contains(char))){
                stringList.add(char)
                j++
                c++
            }else{
                j++
            }
        }
        stringList.shuffle()
        return stringList
    }

    private fun loadFlags() {
        list = ArrayList()
        list.add(FlagData(R.drawable.flag_of_armenia, "Armenia"))
        list.add(FlagData(R.drawable.flag_of_azerbaijan, "Azerbaijan"))
        list.add(FlagData(R.drawable.flag_of_belarus, "Belarus"))
        list.add(FlagData(R.drawable.flag_of_kazakhstan, "Kazakhstan"))
        list.add(FlagData(R.drawable.flag_of_kyrgyzstan, "Kyrgyzstan"))
        list.add(FlagData(R.drawable.flag_of_moldova, "Moldova"))
        list.add(FlagData(R.drawable.flag_of_russia, "Russia"))
        list.add(FlagData(R.drawable.flag_of_tajikistan, "Tajikistan"))
        list.add(FlagData(R.drawable.flag_of_turkmenistan, "Turkmenistan"))
        list.add(FlagData(R.drawable.flag_of_ukraine, "Ukraine"))
        list.add(FlagData(R.drawable.flag_of_uzbekistan, "Uzbekistan"))
        list.shuffle()
    }
    private var childCount: Int? = null
    override fun onClick(p0: View?) {
        val button = p0 as Button
        val pressedButton = Button(this)
        pressedButton.text = button.text
        pressedButton.setTextColor(resources.getColor(R.color.white))
        pressedButton.setBackgroundColor(resources.getColor(R.color.green))


        pressedButton.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)
        val margin = pressedButton.layoutParams as? ViewGroup.MarginLayoutParams
        margin?.setMargins(2)
        button.visibility = View.INVISIBLE
        linear_layout1.addView(pressedButton)

        childCount = linear_layout1.childCount
        if (childCount == list[count].country!!.length){
            val stringBuilder = StringBuilder()

            for (i in 0 until childCount!!){
                val buttonAt = linear_layout1.getChildAt(i) as Button
                stringBuilder.append(buttonAt.text.toString())
            }

            if (stringBuilder.toString().equals(list[count].country!!, ignoreCase = true)){
                count++
                linear_layout1.removeAllViews()
                linear_layout2.removeAllViews()
                linear_layout3.removeAllViews()
                if (count < list.size){
                    setData(count)
                }else{
//                    Toast.makeText(this, "Play One More", Toast.LENGTH_SHORT).show()
//                    linear_layout1.removeAllViews()
//                    linear_layout2.removeAllViews()
//                    linear_layout3.removeAllViews()
//                    count = 0
//                    setData(count)
//                    onRestart()
                    finish()
                }

                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
            else{
                linear_layout1.removeAllViews()
                linear_layout2.removeAllViews()
                linear_layout3.removeAllViews()
                setData(count)

                Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT).show()            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.quiz ->{
                onRestart()
            }
            R.id.learn ->{
                finish()
                val intent = Intent("learn")
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}