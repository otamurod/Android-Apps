package com.otamurod.multipletabledb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.multipletabledb.adapters.CustomerAdapter
import com.otamurod.multipletabledb.adapters.EmployeeAdapter
import com.otamurod.multipletabledb.adapters.OrderAdapter
import com.otamurod.multipletabledb.databinding.ActivityOrderBinding
import com.otamurod.multipletabledb.db.MyDbHelper
import com.otamurod.multipletabledb.models.Customer
import com.otamurod.multipletabledb.models.Employee
import com.otamurod.multipletabledb.models.Orders

class OrderActivity : AppCompatActivity() {

    lateinit var myDbHelper: MyDbHelper
    lateinit var list1: List<Customer>
    lateinit var list2: List<Employee>
    lateinit var list3: ArrayList<Orders>

    lateinit var customerAdapter: CustomerAdapter
    lateinit var employeeAdapter: EmployeeAdapter
    lateinit var orderAdapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)


        list1 = myDbHelper.getAllCustomers()
        customerAdapter = CustomerAdapter(list1)
        binding.customerSpinner.adapter = customerAdapter

        list2 = myDbHelper.getAllEmployees()
        employeeAdapter = EmployeeAdapter(list2)
        binding.employeeSpinner.adapter = employeeAdapter

        list3 = myDbHelper.getAllOrders()
        orderAdapter = OrderAdapter(list3)
        binding.orderRv.adapter = orderAdapter

        binding.orderSaveBtn.setOnClickListener {

            val customer = list1[binding.customerSpinner.selectedItemPosition]
            val employee = list2[binding.employeeSpinner.selectedItemPosition]
            val orderDate = binding.orderDateEt.text.toString()

            val orders = Orders(customer, employee, orderDate)

            myDbHelper.insertOrder(orders)

            list3.add(orders)

            orderAdapter.notifyItemInserted(list3.size)

//            finish()
        }

    }
}