package com.otamurod.pdpuz.entities

import androidx.room.Embedded
import androidx.room.Relation

data class GroupOffersCourse(
    @Embedded
    val group: Group,
    @Relation(
        parentColumn = "id",
        entityColumn = "courseId"
    )
    val courses: List<Course>
)