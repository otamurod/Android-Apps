package com.otamurod.multipletabledb.db

import com.otamurod.multipletabledb.models.Customer
import com.otamurod.multipletabledb.models.Employee
import com.otamurod.multipletabledb.models.Orders

interface DatabaseInterface {

    fun insertCustomer(customer: Customer)

    fun insertEmployee(employee: Employee)

    fun insertOrder(orders: Orders)

    fun getAllCustomers(): List<Customer>

    fun getAllEmployees(): List<Employee>

    fun getAllOrders(): List<Orders>

    fun getCustomerById(id: Int): Customer

    fun getEmployeeById(id: Int): Employee

}