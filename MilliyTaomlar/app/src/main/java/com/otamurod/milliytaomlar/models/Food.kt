package com.otamurod.milliytaomlar.models

class Food {

    var id: Int? = null
    var name: String? = null
    var products: String? = null
    var steps: String? = null


    constructor(id: Int?, name: String?, products: String?, steps: String?) {
        this.id = id
        this.name = name
        this.products = products
        this.steps = steps
    }

    constructor(name: String?, products: String?, steps: String?) {
        this.name = name
        this.products = products
        this.steps = steps
    }
}
