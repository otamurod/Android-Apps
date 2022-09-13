package com.otamurod.yolgahamroh.models

import java.io.Serializable

class User : Serializable {

    var name: String? = null
    var phone: String? = null
    var type: String? = null //client or driver
    var fromRegion: String? = null //from which region
    var fromCity: String? = null //from which city
    var toRegion: String? = null //to which region
    var toCity: String? = null //to which city
    var date: String? = null //which time driving starts
    var time: String? = null //which time driving starts
    var seats: String? = null //payment amount
    var taxi_fare: String? = null //payment amount

    constructor()

    constructor(
        name: String?,
        phone: String?,
        type: String?,
        fromRegion: String?,
        fromCity: String?,
        toRegion: String?,
        toCity: String?,
        date: String?,
        time: String?,
        seats: String?,
        taxi_fare: String?
    ) {
        this.name = name
        this.phone = phone
        this.type = type
        this.fromRegion = fromRegion
        this.fromCity = fromCity
        this.toRegion = toRegion
        this.toCity = toCity
        this.date = date
        this.time = time
        this.seats = seats
        this.taxi_fare = taxi_fare
    }


}