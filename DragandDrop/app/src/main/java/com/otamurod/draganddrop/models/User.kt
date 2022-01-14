package com.otamurod.draganddrop.models

class User{
    constructor(id: Int?, username: String?, password: String?) {
        this.id = id
        this.username = username
        this.password = password
    }

    var id:Int? = null
    var username:String? = null
    var password:String? = null

}