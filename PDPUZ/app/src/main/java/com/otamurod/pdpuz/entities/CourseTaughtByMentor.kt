package com.otamurod.pdpuz.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CourseTaughtByMentor(
    @Embedded
    val mentor: Mentor,
    @Relation(
        parentColumn = "id",
        entityColumn = "courseId"
    )
    val course: List<Course>
)