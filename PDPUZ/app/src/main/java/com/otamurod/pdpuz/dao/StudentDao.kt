package com.otamurod.pdpuz.dao

import androidx.room.*
import com.otamurod.pdpuz.entities.Student

@Dao
interface StudentDao {

    @Transaction

    @Query("select * from Student")
    fun getAllStudents(): List<Student>

    @Query("select * from Student where id=:studentId")
    fun getStudentById(studentId: Int): Student

    @Query("select * from Student where courseId=:courseId")
    fun getStudentsByCourseId(courseId: Int): List<Student>

    @Query("select * from Student where courseId=:courseId and groupId=:groupId")
    fun getStudentsByGroupCourseId(courseId: Int, groupId: Int): List<Student>

    @Query("select count(*) from Student where groupName=:groupName")
    fun getStudentsInGroup(groupName: String): Int

    @Insert
    fun insertStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

}