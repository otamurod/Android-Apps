package com.otamurod.playmarket.models

class GeneralData {
    var title:String? = null
    var appList:List<Program>? =null

    constructor(title: String?, appList: List<Program>?) {
        this.title = title
        this.appList = appList
    }
}