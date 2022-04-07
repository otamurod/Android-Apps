package com.otamurod.pdpuz.dao

import androidx.room.*
import com.otamurod.pdpuz.entities.Mentor

@Dao
interface MentorDao {

    @Transaction

    @Query("select * from Mentor where id=:id")
    fun getMentorById(id: Int): Mentor

    @Query("select * from Mentor where courseId=:courseId")
    fun getMentorsByCourseId(courseId: Int): List<Mentor>

    @Query("select id from Mentor where firstName=:firstName and lastName=:lastName")
    fun getMentorIdByName(firstName: String, lastName: String): Int

    @Insert
    fun insertMentor(mentor: Mentor)

    @Update
    fun updateMentor(mentor: Mentor)

    @Delete
    fun deleteMentor(mentor: Mentor)

}