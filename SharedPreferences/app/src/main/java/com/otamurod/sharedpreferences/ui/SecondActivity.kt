package com.otamurod.sharedpreferences.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.otamurod.sharedpreferences.R
import com.otamurod.sharedpreferences.models.User
import com.otamurod.sharedpreferences.utils.MySharedPreference
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        MySharedPreference.init(this)
        gson = Gson()

        sign_up_btn.setOnClickListener {

            val username = edit1.text.toString()
            val password = edit1.text.toString()

            val user = User(username, password)

            val str = gson.toJson(user)
            MySharedPreference.user = str

            finish()
        }
    }


/*lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        sharedPreferences = getSharedPreferences("android_app", MODE_PRIVATE)
        val string = sharedPreferences.getString("key1", "")
        tv2.text = string

        close_btn.setOnClickListener {

            finish()
        }
    }*/
}