package com.otamurod.sharedpreferences.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.otamurod.sharedpreferences.R
import com.otamurod.sharedpreferences.models.User
import com.otamurod.sharedpreferences.utils.MySharedPreference
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        MySharedPreference.init(this)
        val userStr = MySharedPreference.user

        gson = Gson()
        val user = gson.fromJson(userStr, User::class.java)

        welcome.text = "Dear ${user.username}, Welcome to Uzbekistan!"
    }
}