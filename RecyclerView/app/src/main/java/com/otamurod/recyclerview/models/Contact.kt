package com.otamurod.recyclerview.models

import java.io.Serializable

//data class Contact (val id:Int, val name:String, val number:String)


class Contact:Serializable{

    var id: Int? = null
    var name:String? = null
    var number:String? = null

    constructor(name: String?, number: String?) {
        this.name = name
        this.number = number
    }
}