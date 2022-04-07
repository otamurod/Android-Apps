package com.otamurod.pdpuz.dao

import androidx.room.*
import com.otamurod.pdpuz.entities.Group

@Dao
interface GroupDao {

    @Transaction

    @Query("select * from `Group` where id=:groupId")
    fun getGroupById(groupId: Int): Group

    @Query("select * from `Group` where open=:open and courseId=:courseId")
    fun getGroupsByType(courseId:Int, open: Boolean): List<Group>

    @Query("select * from `Group` where courseId=:courseId")
    fun getGroupsByCourseId(courseId: Int): List<Group>

    @Query("select * from `Group` where mentorId=:mentorId")
    fun getGroupsByMentorId(mentorId: Int): List<Group>

    @Query("select id from `Group` where groupName=:groupName")
    fun getGroupByName(groupName: String): Int

    @Insert
    fun insertGroup(group: Group)

    @Update
    fun updateGroup(group: Group)

    @Delete
    fun deleteGroup(group: Group)

}