package com.otamurod.multipletabledb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.otamurod.multipletabledb.databinding.ActivityEmployeeBinding
import com.otamurod.multipletabledb.db.MyDbHelper
import com.otamurod.multipletabledb.models.Employee

class EmployeeActivity : AppCompatActivity() {

    lateinit var myDbHelper: MyDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)

        binding.employeeSaveBtn.setOnClickListener {

            val name = binding.employeeNameEt.text.toString()
            val employee = Employee(name)

            myDbHelper.insertEmployee(employee)
            finish()
        }

    }
}