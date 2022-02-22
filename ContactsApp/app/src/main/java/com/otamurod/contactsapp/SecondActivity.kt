package com.otamurod.contactsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.contactsapp.databinding.ActivitySecondBinding
import com.otamurod.contactsapp.db.MyDbHelper

class SecondActivity : AppCompatActivity() {
    lateinit var myDbHelper: MyDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater, null, false)
        setContentView(activitySecondBinding.root)

        val id = intent.getIntExtra("id", 0)

        myDbHelper = MyDbHelper(this)
        val contact = myDbHelper.getContactById(id)

        activitySecondBinding.nameTv.text = contact.name
        activitySecondBinding.numberTv.text = contact.phoneNumber

    }
}