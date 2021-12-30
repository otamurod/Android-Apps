package com.otamurod.sharedpreferences.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.otamurod.sharedpreferences.R
import com.otamurod.sharedpreferences.models.User
import com.otamurod.sharedpreferences.utils.MySharedPreference
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MySharedPreference.init(this)
        MySharedPreference.text = "Uzbekistan"

        gson = Gson()

        register.setOnClickListener {

            val intent = Intent("second")
            startActivity(intent)

        }

        sign_in_btn.setOnClickListener {
            val username = edit1.text.toString()
            val password = edit2.text.toString()

            val userStr = MySharedPreference.user
            val user = gson.fromJson(userStr, User::class.java)
            if (userStr != ""){
                if (user.username == username && user.password == password){
                    val intent = Intent("welcome")
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Username/Password is invalid", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

    /*lateinit var sharedPreferences: SharedPreferences //access to cache memory
    lateinit var editor: SharedPreferences.Editor //edit cache memory contents

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("android_app", MODE_PRIVATE)

        editor = sharedPreferences.edit()

        save_btn.setOnClickListener {
            val text = edit.text.toString()
            editor.putString("key1", text)
            editor.commit()
        }

        show_btn.setOnClickListener {

            val intent = Intent("second")
            startActivity(intent)

        }

    }*/
}