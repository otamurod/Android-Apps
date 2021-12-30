package com.otamurod.intents

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.otamurod.intents.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.close.setOnClickListener {
            finish()
        }

        binding.go.setOnClickListener {
            finish()
            val intent = Intent("third")
            startActivity(intent)
        }

    }
}