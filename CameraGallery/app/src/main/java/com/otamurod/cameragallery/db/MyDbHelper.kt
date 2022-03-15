package com.otamurod.cameragallery.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.otamurod.cameragallery.models.ImageModel

class MyDbHelper(context: Context):SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION), DbHelper {

    companion object{
        val DB_NAME = "img.db"
        val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table image_table(id integer primary key autoincrement not null, img_path text not null, image blob not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun insertImage(imageModel: ImageModel) {
        val database = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put("img_path", imageModel.imagePath)
        contentValues.put("image", imageModel.image)

        database.insert("image_table", null, contentValues)
        database.close()
    }

    override fun getAllImages(): List<ImageModel> {
        val list = ArrayList<ImageModel>()
        val query = "select * from image_table"

        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()){
            do {
                val imageModel = ImageModel()
                imageModel.id = cursor.getInt(0)
                imageModel.imagePath = cursor.getString(1)
                imageModel.image = cursor.getBlob(2)

                list.add(imageModel)
            }while (cursor.moveToNext())
        }

        return list
    }
}