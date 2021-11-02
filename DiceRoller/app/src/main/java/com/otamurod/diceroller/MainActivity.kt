package com.otamurod.diceroller

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast


/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //get button using its ID
        val rollButton: Button = findViewById(R.id.button)
        //create Listener
        rollButton.setOnClickListener {
            /*Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show()
            //OR
//            val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT)
//            toast.show()*/

//            val resultTextView: View = findViewById(R.id.textView)
//            resultTextView.textView.text = "6"

            rollDice() //call function

        }
        rollDice() //call function to show what to do with "Roll" button
    }

    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice() {
        val luckyNumber1 = 6
        // Create new Dice object with 6 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()

        val luckyNumber2 = 6
        // Create another Dice object with 6 sides and roll it
        val dice2 = Dice(6)
        val diceRoll2 = dice2.roll()

        // Update the screen with the dice roll
        /**
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = diceRoll.toString()
        val resultTextView2: TextView = findViewById(R.id.textView2)
        resultTextView2.text = diceRoll2.toString()
         */

        // Find the ImageView in the layout
        val diceImage: ImageView = findViewById(R.id.imageView)
        val diceImage2: ImageView = findViewById(R.id.imageView2)

        // Determine which drawable resource ID to use based on the dice roll
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        // Update the ImageView with the correct drawable resource ID

        diceImage.setImageResource(drawableResource)
        // Update the content description
        diceImage.contentDescription = diceRoll.toString()

        // Determine which drawable resource ID to use based on the dice roll
        val drawableResource2 = when (diceRoll2) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        // Update the ImageView with the correct drawable resource ID
        diceImage2.setImageResource(drawableResource2)
        // Update the content description
        diceImage2.contentDescription = diceRoll2.toString()

        if (diceRoll == luckyNumber1 && diceRoll2 == luckyNumber2){
            Toast.makeText(this, "You Win!", Toast.LENGTH_LONG).show()
        }
    }
}

//class of a Dice
class Dice(private val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}