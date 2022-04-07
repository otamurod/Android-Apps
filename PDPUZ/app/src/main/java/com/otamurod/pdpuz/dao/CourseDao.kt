package com.otamurod.pdpuz.dao

import androidx.room.*
import com.otamurod.pdpuz.entities.Course

@Dao
interface CourseDao {

    @Query("select * from Course")
    fun getAllCourses(): List<Course>

    @Query("select id from Course where Name=:courseName")
    fun getCourseId(courseName: String): Int

    @Query("select * from Course where id=:courseId")
    fun getCourseById(courseId: Int): Course

    @Insert
    fun insertCourse(course: Course)

    @Update
    fun updateCourse(course: Course)

    @Delete
    fun deleteCourse(course: Course)

}