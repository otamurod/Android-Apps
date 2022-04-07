package com.otamurod.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryNews(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    )
    val newsList: List<News>
)
