package com.otamurod.multipletabledb.utils

object Constant {

    const val DATABASE_NAME = "order_db"
    const val DATABASE_VERSION = 1

    //first table
    const val CUSTOMER_TABLE = "customers"
    const val CUSTOMER_ID = "id"
    const val CUSTOMER_NAME = "name"
    const val ADDRESS = "address"
    const val POSTAL_CODE = "postal_code"
    const val COUNTRY = "country"

    //second table
    const val EMPLOYEE_TABLE = "employees"
    const val EMPLOYEE_ID = "id"
    const val EMPLOYEE_NAME = "name"

    //third table
    const val ORDERS_TABLE = "orders"
    const val ORDERS_ID = "id"
    const val CUSTOMER_ORDER_ID = "customer_id"
    const val EMPLOYEE_ORDER_ID = "employee_id"
    const val ORDER_DATE = "order_date"

}