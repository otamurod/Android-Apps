package com.otamurod.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    var dotClick = true //dot can be pressed once by default
    var positive = true //variable to control " - " button

    fun buttonClickEvent(view: android.view.View) {

        if (isNewOperation){
            showEntry.setText("")
        }

        isNewOperation = false

        val buttonClicked = view as Button
        var buttonClickValue:String = showEntry.text.toString()

        when(buttonClicked.id){ //get numbers from a user
            button0.id ->{
                buttonClickValue += "0"
            }
            button1.id ->{
                buttonClickValue += "1"
            }
            button2.id ->{
                buttonClickValue += "2"
            }
            button3.id ->{
                buttonClickValue += "3"
            }
            button4.id ->{
                buttonClickValue += "4"
            }
            button5.id ->{
                buttonClickValue += "5"
            }
            button6.id ->{
                buttonClickValue += "6"
            }
            button7.id ->{
                buttonClickValue += "7"
            }
            button8.id ->{
                buttonClickValue += "8"
            }
            button9.id ->{
                buttonClickValue += "9"
            }
            buttonPlusMinus.id ->{
                if (positive){
                    buttonClickValue = "-" + buttonClickValue
                    positive = false //disable '-' button if once clicked
                }else if(buttonClickValue.contains("-")){ //remove ' - ' when clicked if number is negative
                    buttonClickValue = buttonClickValue.substring(1)
                    positive = true
                }
            }
            buttonDot.id ->{
                //TODO: Prevent adding more than 1 dot
                if (dotClick){
                    buttonClickValue += "."
                    dotClick = false
                }
            }
        }

        showEntry.setText(buttonClickValue)

    }

    var operation:String? = null
    var oldNumber = ""
    var isNewOperation = true

    fun buttonOpEvent(view: View){
        val buttonClicked = view as Button

        when(buttonClicked.id) { //set operations
            buttonAdd.id -> {
                operation = "+"
            }
            buttonSub.id -> {
                operation = "-"
            }
            buttonMul.id -> {
                operation = "*"
            }
            buttonDiv.id -> {
                operation = "/"
            }
        }

        oldNumber = showEntry.text.toString()
        showEntry.setText("")
        isNewOperation = true
        dotClick = true //enable '.' button

    }

    fun buttonResultEvent(view: View){

        val newNumber = showEntry.text.toString()
        var result:Double? = null

        if (operation != null){ //check whether an operation selected or not
            when(operation){
                "+" -> {
                    if (oldNumber != null && newNumber != null){
                        result = oldNumber.toDouble() + newNumber.toDouble()
                    }else{
                        result = 0.000
                    }
                }
                "-" -> {
                    if (oldNumber != null && newNumber != null) {
                        result = oldNumber.toDouble() - newNumber.toDouble()
                    }else{
                        result = 0.000
                    }
                }
                "*" -> {
                    if (oldNumber != null && newNumber != null) {
                        result = oldNumber.toDouble() * newNumber.toDouble()
                    }else{
                        result = 0.000
                    }
                }
                "/" -> {
                    if (oldNumber != null && newNumber != null) {
                        result = oldNumber.toDouble() / newNumber.toDouble()
                    }else{
                        result = 0.000
                    }
                }
            }
        }else{
            result = 0.000
        }

        if (result != null){
            showEntry.setText("${result!!.toFloat()}").toString() //display result
            operation = null
        }else{
            showEntry.setText("0").toString()
            operation = null
        }
        isNewOperation = true
        dotClick = true

    }


    fun buttonPercentEvent(view: View){
        val entryInPercent = showEntry.text.toString()
        //prevent from error if a user clicks % button before entering data
        if (entryInPercent != ""){
            val percent:Double = entryInPercent.toDouble() / 100.000
            showEntry.setText(percent.toString())
        }else{
            showEntry.setText("")
        }
        isNewOperation = true

    }

    fun buttonACEvent(view: View){ //Clear all

        showEntry.setText("")
        isNewOperation = true
        dotClick = true

    }

}