package com.otamurod.simplepdfreader

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.otamurod.simplepdfreader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}