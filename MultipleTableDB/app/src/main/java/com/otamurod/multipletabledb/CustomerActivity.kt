package com.otamurod.multipletabledb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.otamurod.multipletabledb.databinding.ActivityCustomerBinding
import com.otamurod.multipletabledb.db.MyDbHelper
import com.otamurod.multipletabledb.models.Customer

class CustomerActivity : AppCompatActivity() {

    lateinit var myDbHelper: MyDbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)

        binding.customerSaveBtn.setOnClickListener {
            val name = binding.customerNameEt.text.toString()
            val address = binding.customerAddressEt.text.toString()
            val postalCode = binding.customerPostalEt.text.toString()
            val country = binding.customerCountryEt.text.toString()

            val customer = Customer(name, address, postalCode, country)

            myDbHelper.insertCustomer(customer)
            finish()

        }

    }
}