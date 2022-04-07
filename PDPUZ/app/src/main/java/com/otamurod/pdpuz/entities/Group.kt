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
            entity = Mentor::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("mentorId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
class Group {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(index = true) //FK
    var courseId: Int? = null

    @ColumnInfo(index = true) //FK
    var mentorId: Int? = null

    @ColumnInfo(name = "groupName")
    var groupName: String? = null

    @ColumnInfo(name = "time")
    var time: String? = null

    @ColumnInfo(name = "day")
    var day: String? = null

    @ColumnInfo(name = "open")
    var open: Boolean? = null

    constructor()

    constructor(
        courseId: Int?,
        mentorId: Int?,
        groupName: String?,
        time: String?,
        day: String?,
        open: Boolean?
    ) {
        this.courseId = courseId
        this.mentorId = mentorId
        this.groupName = groupName
        this.time = time
        this.day = day
        this.open = open
    }

}