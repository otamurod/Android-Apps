package com.otamurod.cloudfirestore.models

class User {

    var name: String? = null
    var age: Int? = null
    var imgUrl: String? = null

    constructor()

    constructor(name: String?, age: Int?, imgUrl: String?) {
        this.name = name
        this.age = age
        this.imgUrl = imgUrl
    }
}