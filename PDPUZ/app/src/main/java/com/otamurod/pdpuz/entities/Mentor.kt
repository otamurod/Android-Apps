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
        )
    )
)
class Mentor {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(index = true) //FK
    var courseId: Int? = null

    @ColumnInfo(name = "lastName")
    var lastName: String? = null

    @ColumnInfo(name = "firstName")
    var firstName: String? = null

    @ColumnInfo(name = "middleName")
    var middleName: String? = null

    constructor(courseId: Int?, lastName: String?, firstName: String?, middleName: String?) {
        this.courseId = courseId
        this.lastName = lastName
        this.firstName = firstName
        this.middleName = middleName
    }

    constructor()

}