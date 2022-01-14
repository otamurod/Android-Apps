package com.otamurod.expandablelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.otamurod.expandablelist.adapters.SpinnerAdapter
import com.otamurod.expandablelist.models.User
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

//    lateinit var arrayAdapter: ArrayAdapter<String>

    private lateinit var spinnerAdapter: SpinnerAdapter
    private lateinit var userList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//        val stringArray = resources.getStringArray(R.array.spinner_array)

        val stringArray = arrayOf("Kotlin", "Java", "Android", "Flutter")

//        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stringArray)
//        spinner.adapter = arrayAdapter

        loadUsers()

        spinnerAdapter = SpinnerAdapter(userList)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@SecondActivity, userList[position].name, Toast.LENGTH_SHORT).show()
                spinner_img.setImageResource(userList[position].img)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        button.setOnClickListener {
            val s = userList[spinner.selectedItemPosition]
            Toast.makeText(this, s.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadUsers() {
        userList = ArrayList()

        userList.add(User(R.drawable.android, "Android"))
        userList.add(User(R.drawable.kotlin, "Kotlin"))
        userList.add(User(R.drawable.java,"Java"))
        userList.add(User(R.drawable.ios, "iOS"))
        userList.add(User(R.drawable.swift, "Swift"))
        userList.add(User(R.drawable.flutter, "Flutter"))
        userList.add(User(R.drawable.dart, "Dart"))
        userList.add(User(R.drawable.js, "Javascript"))
        userList.add(User(R.drawable.python, "Python"))
        userList.add(User(R.drawable.ruby, "Ruby"))
    }
}