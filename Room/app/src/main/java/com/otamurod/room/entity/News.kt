package com.otamurod.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class News {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "Title")
    var title: String? = null

    @ColumnInfo(name = "Info")
    var info: String? = null

    @ColumnInfo(name = "category_id")
    var categoryId:Int?=null

    constructor(title: String?, info: String?) {
        this.title = title
        this.info = info
    }

    constructor()

}