package com.otamurod.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClick(view: View) {

        val buttonClicked = view as Button

        var cellId = 0
        //give buttons id to identify when clicked
        when(buttonClicked.id){
            R.id.button1 -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            R.id.button9 -> cellId = 9
        }
        //Log which button is clicked
        Log.d("buttonClick", buttonClicked.id.toString())
        Log.d("buttonClick - cellID", cellId.toString())

        playGame(cellId, buttonClicked)

    }
    fun reset(view: View) {
        restartGame()
    }

    var activePlayer = 1
    //define players
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()


    //play game function
    fun playGame(cellId:Int, buttonClicked:Button){

        if (activePlayer == 1){

            buttonClicked.text = "X"
            buttonClicked.setBackgroundColor(buttonClicked.context.resources.getColor(R.color.blue))
            player1.add(cellId)
            activePlayer = 2

            //play with a divice
            autoPlay()

        }else{

            buttonClicked.text = "O"
            buttonClicked.setBackgroundColor(buttonClicked.context.resources.getColor(R.color.lightGreen))
            player2.add(cellId)
            activePlayer = 1

        }
        //disable buttons when player are gaming
        buttonClicked.isEnabled = false

        //check winner
        checkWinner()
    }

    fun checkWinner(){

        var winner = -1

        //row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }

        //row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }

        //row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }

        //column 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }

        //column 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }

        //column 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }

        //display winner
        if (winner == 1){
            player1WonCounts += 1
            Toast.makeText(this, "Player 1 won the game", Toast.LENGTH_LONG).show()
            restartGame()
        }else if (winner == 2){
            player2WonCounts += 1
            Toast.makeText(this, "Player 2 won the game", Toast.LENGTH_LONG).show()
            restartGame()
        }

    }

    //play with a device
    fun autoPlay(){

        var emptyCells = ArrayList<Int>()

        for (cellId in 1..9){
            //check whether cell is taken or not
            if (!(player1.contains(cellId)) || !(player1.contains(cellId))){

                emptyCells.add(cellId) //take cell if it is free

            }
        }

        if (emptyCells.size == 0){
            restartGame()
        }

        val randomCell = Random()
        val randomIndex = randomCell.nextInt(emptyCells.size)
        val cellId = emptyCells[randomIndex]

        var buttonSelected: Button?
        buttonSelected = when(cellId){
            1 -> button1
            2 -> button2
            3 -> button3
            4 -> button4
            5 -> button5
            6 -> button6
            7 -> button7
            8 -> button8
            9 -> button9
            else -> {button1}
        }

        playGame(cellId, buttonSelected)
    }

    var player1WonCounts = 0
    var player2WonCounts = 0

    //replay the game
    fun restartGame(){
        activePlayer = 1
        player1.clear()
        player2.clear()

        for (cell in 1..9){

            var buttonSelected: Button?
            buttonSelected = when(cell){
                1 -> button1
                2 -> button2
                3 -> button3
                4 -> button4
                5 -> button5
                6 -> button6
                7 -> button7
                8 -> button8
                9 -> button9
                else -> {button1}
            }
            buttonSelected.text = ""
            buttonSelected.setBackgroundColor(buttonSelected.context.resources.getColor(R.color.white))
            buttonSelected.isEnabled = true
        }

        Toast.makeText(this, "Score:\nPlayer 1: $player1WonCounts\nPlayer 2: $player2WonCounts", Toast.LENGTH_LONG).show()
    }


}