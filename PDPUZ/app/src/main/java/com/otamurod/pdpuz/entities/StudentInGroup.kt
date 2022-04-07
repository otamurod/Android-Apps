package com.otamurod.pdpuz.entities

import androidx.room.Embedded
import androidx.room.Relation

data class StudentInGroup(
    @Embedded
    val student: Student,
    @Relation(
        parentColumn = "id",
        entityColumn = "groupId"
    )
    val groups: List<Group>
)