package com.otamurod.passportapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.passportapp.databinding.ActivityMainBinding
import com.otamurod.passportapp.fragments.SplashFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setIcon(R.drawable.ic_arrow_back)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val splashFragment = SplashFragment()
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.container, splashFragment)
        beginTransaction.commit()

    }
}