package com.otamurod.tiptime

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.otamurod.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}*/

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Add click listener to the button
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        // Get the cost of service
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        // If the cost is null or 0, then display 0 tip and exit this function early.
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }

        val tipPercentage =
            when (binding.tipOptions.checkedRadioButtonId) {         // Get the tip percentage
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }
        //Calculate the tip and round it up
        var tip = tipPercentage * cost

        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        // Display the formatted tip value on screen
        displayTip(tip)

    }

    private fun displayTip(tip: Double) {
        // Format the tip
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        //Display the tip
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}