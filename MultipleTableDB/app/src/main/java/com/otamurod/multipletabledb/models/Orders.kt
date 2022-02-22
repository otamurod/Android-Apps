package com.otamurod.multipletabledb.models

class Orders {
    var id: Int? = null
    //aggregation
    var customer: Customer? = null
    var employee: Employee? = null

    var orderDate: String? = null

    constructor(customer: Customer?, employee: Employee?, orderDate: String?) {
        this.customer = customer
        this.employee = employee
        this.orderDate = orderDate
    }

    constructor()

}