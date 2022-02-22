package com.otamurod.mathgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var random: Random
    var result: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        random = Random()
        var correct:Int = 0
        var wrong:Int = 0

        setElements()

        next_btn.setOnClickListener {

            val input = answer.text.toString()
            val ans = "%.1f".format(result)

            if (input != ""){
                if ( input == ans ){
                    answer.setText("")
                    correct++
                    correct_ans.text = "Correct: $correct"
                    Toast.makeText(this, "Correct!!!", Toast.LENGTH_SHORT).show()
                }else{
                    answer.setText("")
                    wrong++
                    wrong_ans.text = "Wrong: $wrong"
                    Toast.makeText(this, "Wrong!!!:: Correct ANSWER -> $ans", Toast.LENGTH_SHORT).show()
                }
                setElements()
            }
        }

    }

    private fun setElements() {
        val num1 = random.nextInt(100).toFloat()
        number1.text = num1.toString()

        val num2 = random.nextInt(100).toFloat()
        number2.text = num2.toString()
        result = null

        val option = random.nextInt(4)

        when(option){
            0 -> {
                operation.text = "+"
                result = (num1 + num2)
            }
            1 -> {
                operation.text = "-"
                result = (num1 - num2)
            }
            2 -> {
                operation.text = "*"
                result = (num1 * num2)
            }
            3 -> {
                operation.text = "/"
                result = (num1 / num2)
            }
        }

    }

}