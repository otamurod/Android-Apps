package com.otamurod.flagquiz.models

class FlagData {

    var image: Int? = null
    var country:String? = null

    constructor(image: Int?, country: String?) {
        this.image = image
        this.country = country
    }
}