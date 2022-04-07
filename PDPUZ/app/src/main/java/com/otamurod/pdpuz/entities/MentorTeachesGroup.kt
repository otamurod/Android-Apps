package com.otamurod.pdpuz.entities

import androidx.room.Embedded
import androidx.room.Relation

data class MentorTeachesGroup(
    @Embedded
    val group: Group,
    @Relation(
        parentColumn = "id",
        entityColumn = "mentorId"
    )
    val mentors: List<Mentor>
)