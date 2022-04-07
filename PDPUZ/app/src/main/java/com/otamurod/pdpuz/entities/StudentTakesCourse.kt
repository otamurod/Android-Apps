package com.otamurod.pdpuz.entities

import androidx.room.Embedded
import androidx.room.Relation

data class StudentTakesCourse(
    @Embedded
    val student: Student,
    @Relation(
        parentColumn = "id",
        entityColumn = "courseId"
    )
    val courses: List<Course>
)