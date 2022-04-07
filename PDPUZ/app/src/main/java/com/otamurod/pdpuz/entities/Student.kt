package com.otamurod.pdpuz.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Course::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("courseId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Group::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("groupId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
class Student {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

    @ColumnInfo(index = true) //FK
    var courseId: Int? = null

    @ColumnInfo(index = true) //FK
    var groupId: Int? = null

    @ColumnInfo(name = "lastName")
    var lastName: String? = null

    @ColumnInfo(name = "firstName")
    var firstName: String? = null

    @ColumnInfo(name = "middleName")
    var middleName: String? = null

    @ColumnInfo(name = "date")
    var date: String? = null

    @ColumnInfo(name = "mentorName")
    var mentorName: String? = null

    @ColumnInfo(name = "groupName")
    var groupName: String? = null

    constructor(
        courseId: Int?,
        groupId: Int?,
        lastName: String?,
        firstName: String?,
        middleName: String?,
        date: String?,
        mentorName: String?,
        groupName: String?
    ) {
        this.courseId = courseId
        this.groupId = groupId
        this.lastName = lastName
        this.firstName = firstName
        this.middleName = middleName
        this.date = date
        this.mentorName = mentorName
        this.groupName = groupName
    }

    constructor()
}