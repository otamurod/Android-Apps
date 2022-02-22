package com.otamurod.multipletabledb.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.otamurod.multipletabledb.models.Customer
import com.otamurod.multipletabledb.models.Employee
import com.otamurod.multipletabledb.models.Orders
import com.otamurod.multipletabledb.utils.Constant

class MyDbHelper(context: Context) :
    SQLiteOpenHelper(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION),
    DatabaseInterface {

    override fun onCreate(db: SQLiteDatabase?) {
        val customerTableQuery =
            "CREATE TABLE ${Constant.CUSTOMER_TABLE} (${Constant.CUSTOMER_ID} INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, ${Constant.CUSTOMER_NAME} TEXT NOT NULL, ${Constant.ADDRESS} TEXT NOT NULL, ${Constant.POSTAL_CODE} TEXT NOT NULL, ${Constant.COUNTRY} TEXT NOT NULL)"

        val employeeTableQuery =
            "CREATE TABLE ${Constant.EMPLOYEE_TABLE} (${Constant.EMPLOYEE_ID} INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, ${Constant.EMPLOYEE_NAME} TEXT NOT NULL)"

        val ordersTableQuery =
            "CREATE TABLE ${Constant.ORDERS_TABLE} (${Constant.ORDERS_ID} INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, ${Constant.CUSTOMER_ORDER_ID} INTEGER NOT NULL, ${Constant.EMPLOYEE_ORDER_ID} INTEGER NOT NULL, ${Constant.ORDER_DATE} TEXT NOT NULL, FOREIGN KEY(${Constant.CUSTOMER_ORDER_ID}) REFERENCES ${Constant.CUSTOMER_TABLE} (${Constant.CUSTOMER_ID}), FOREIGN KEY(${Constant.EMPLOYEE_ORDER_ID}) REFERENCES ${Constant.EMPLOYEE_TABLE} (${Constant.EMPLOYEE_ID}))"

        db?.execSQL(customerTableQuery)
        db?.execSQL(employeeTableQuery)
        db?.execSQL(ordersTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    /*      Implement Interface     */

    override fun insertCustomer(customer: Customer) {

        val database = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(Constant.CUSTOMER_NAME, customer.name)
        contentValues.put(Constant.ADDRESS, customer.address)
        contentValues.put(Constant.POSTAL_CODE, customer.postalCode)
        contentValues.put(Constant.COUNTRY, customer.country)

        database.insert(Constant.CUSTOMER_TABLE, null, contentValues)
        database.close()

    }

    override fun insertEmployee(employee: Employee) {
        val database = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(Constant.EMPLOYEE_NAME, employee.name)

        database.insert(Constant.EMPLOYEE_TABLE, null, contentValues)
        database.close()
    }

    override fun insertOrder(orders: Orders) {
        val database = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(Constant.CUSTOMER_ORDER_ID, orders.customer?.id)
        contentValues.put(Constant.EMPLOYEE_ORDER_ID, orders.employee?.id)
        contentValues.put(Constant.ORDER_DATE, orders.orderDate)

        database.insert(Constant.ORDERS_TABLE, null, contentValues)
        database.close()
    }

    override fun getAllCustomers(): List<Customer> {
        val list = ArrayList<Customer>()
        val query = "select * from ${Constant.CUSTOMER_TABLE}"
        val database = this.readableDatabase

        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {

                val customer = Customer()
                customer.id = cursor.getInt(0)
                customer.name = cursor.getString(1)
                customer.address = cursor.getString(2)
                customer.postalCode = cursor.getString(3)
                customer.country = cursor.getString(4)
                list.add(customer)

            } while (cursor.moveToNext())
        }

        return list
    }

    override fun getAllEmployees(): List<Employee> {
        val list = ArrayList<Employee>()
        val query = "select * from ${Constant.EMPLOYEE_TABLE}"
        val database = this.readableDatabase

        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {

                val employee = Employee()
                employee.id = cursor.getInt(0)
                employee.name = cursor.getString(1)

                list.add(employee)

            } while (cursor.moveToNext())
        }

        return list
    }

    override fun getAllOrders(): ArrayList<Orders> {
        val list = ArrayList<Orders>()
        val query = "select * from ${Constant.ORDERS_TABLE}"
        val database = this.readableDatabase

        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {

                val orders = Orders()
                orders.id = cursor.getInt(0)
                orders.customer = getCustomerById(cursor.getInt(1))
                orders.employee = getEmployeeById(cursor.getInt(2))
                orders.orderDate = cursor.getString(3)

                list.add(orders)
            } while (cursor.moveToNext())
        }

        return list
    }

    override fun getCustomerById(id: Int): Customer {
        val database = this.readableDatabase
        val cursor = database.query(
            Constant.CUSTOMER_TABLE,
            arrayOf(
                Constant.CUSTOMER_ID,
                Constant.CUSTOMER_NAME,
                Constant.ADDRESS,
                Constant.POSTAL_CODE,
                Constant.COUNTRY
            ),
            "${Constant.CUSTOMER_ID}= ?",
            arrayOf(id.toString()),
            null, null, null
        )

        cursor?.moveToFirst()
        val customer = Customer()
        customer.id = cursor.getInt(0)
        customer.name = cursor.getString(1)
        customer.address = cursor.getString(2)
        customer.postalCode = cursor.getString(3)
        customer.country = cursor.getString(4)

        return customer
    }

    override fun getEmployeeById(id: Int): Employee {
        val database = this.readableDatabase
        val cursor = database.query(
            Constant.EMPLOYEE_TABLE,
            arrayOf(
                Constant.EMPLOYEE_ID,
                Constant.EMPLOYEE_NAME
            ),
            "${Constant.EMPLOYEE_ID}= ?",
            arrayOf(id.toString()),
            null, null, null
        )

        cursor?.moveToFirst()
        val employee = Employee()
        employee.id = cursor.getInt(0)
        employee.name = cursor.getString(1)

        return employee
    }

}