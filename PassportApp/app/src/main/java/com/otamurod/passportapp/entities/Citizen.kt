package com.otamurod.passportapp.entities

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.BLOB
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Citizen")
class Citizen {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "First_Name")
    var firstName: String

    @ColumnInfo(name = "Last_Name")
    var lastName: String

    @ColumnInfo(name = "Middle_Name")
    var middleName: String

    @ColumnInfo(name = "Region_Address")
    var regionAddress: String

    @ColumnInfo(name = "City_Address")
    var cityAddress: String

    @ColumnInfo(name = "Home_Address")
    var homeAddress: String

    @ColumnInfo(name = "Passport_No")
    var passportNo: String

    @ColumnInfo(name = "P_Taken_Date")
    var passportTakenDate: String

    @ColumnInfo(name = "P_Valid_Till")
    var passportValidTill: String

    @ColumnInfo(name = "Gender")
    var gender: String

    @ColumnInfo(typeAffinity = BLOB)
    var image: ByteArray
/*
    @ColumnInfo(name = "Image")
    var image: String*/

    constructor(
        firstName: String,
        lastName: String,
        middleName: String,
        regionAddress: String,
        cityAddress: String,
        homeAddress: String,
        passportNo: String,
        passportTakenDate: String,
        passportValidTill: String,
        gender: String,
        image: ByteArray
    ) {
        this.firstName = firstName
        this.lastName = lastName
        this.middleName = middleName
        this.regionAddress = regionAddress
        this.cityAddress = cityAddress
        this.homeAddress = homeAddress
        this.passportNo = passportNo
        this.passportTakenDate = passportTakenDate
        this.passportValidTill = passportValidTill
        this.gender = gender
        this.image = image
    }
}