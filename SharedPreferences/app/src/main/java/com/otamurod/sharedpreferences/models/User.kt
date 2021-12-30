package com.otamurod.sharedpreferences.models

class User {
    var username:String? = null
    var password:String? = null

    constructor(username: String?, password: String?) {
        this.username = username
        this.password = password
    }
}