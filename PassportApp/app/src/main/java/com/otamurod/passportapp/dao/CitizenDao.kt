package com.otamurod.passportapp.dao

import androidx.room.*
import com.otamurod.passportapp.entities.Citizen

@Dao
interface CitizenDao {

    //get all citizens
    @Query("select * from citizen")
    fun getAllCitizens(): List<Citizen>

    //insert new citizen
    @Insert
    fun addCitizen(citizen: Citizen)

    //delete certain citizen
    @Delete
    fun deleteCitizen(citizen: Citizen)

    //update particular citizen
    @Update
    fun updateCitizen(citizen: Citizen)

    //get citizen with id
    @Query("select * from citizen where id=:id")
    fun getCitizenById(id: Int): Citizen

    //search citizen
    @Query("select * from citizen where First_Name=:firstName and Last_Name=:lastName and Middle_Name=:middleName")
    fun getCitizenByFullName(firstName: String, lastName: String, middleName: String): Citizen

    @Query("select * from citizen where First_Name=:text ")
    fun getCitizenFromSearch(text:String):List<Citizen>

    //get id of citizen
    @Query("select id from citizen where First_Name=:firstName and Last_Name=:lastName and Middle_Name=:middleName")
    fun getCitizenId(firstName: String, lastName: String, middleName: String): Int


}