package com.otamurod.milliytaomlar.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.otamurod.contactsapp.utils.Constant
import com.otamurod.milliytaomlar.models.Food

class MyDbHelper(context: Context) :
    SQLiteOpenHelper(context, Constant.DB_NAME, null, Constant.DB_VERSION), DatabaseService {

    //done
    override fun onCreate(db: SQLiteDatabase?) {

        val query =
            "create table ${Constant.TABLE_NAME} (${Constant.ID} integer not null primary key autoincrement unique, ${Constant.NAME} text not null, ${Constant.PRODUCTS} text not null, ${Constant.STEPS} text not null)"

        db?.execSQL(query)

    }

    //done
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists ${Constant.TABLE_NAME}")
        onCreate(db)

    }

    //done
    override fun addFood(food: Food) {
        val database = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(Constant.NAME, food.name)
        contentValues.put(Constant.PRODUCTS, food.products)
        contentValues.put(Constant.STEPS, food.steps)

        database.insert(Constant.TABLE_NAME, null, contentValues)
        database.close()

    }

    //done
    override fun updateFood(food: Food):Int {
        val database = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(Constant.ID, food.id)
        contentValues.put(Constant.NAME, food.name)
        contentValues.put(Constant.PRODUCTS, food.products)
        contentValues.put(Constant.STEPS, food.steps)

        return database.update(
            Constant.TABLE_NAME,
            contentValues,
            "${Constant.ID} = ?",
            arrayOf("${food.id}")
        )

    }

    override fun deleteFood(food: Food) {
        val database = this.writableDatabase
        database.delete(Constant.TABLE_NAME, "${Constant.ID} = ?", arrayOf("${food.id}"))
        database.close()
    }

    //done
    override fun getFoodById(id: Int): Food {
        val database = this.readableDatabase

        val cursor = database.query(
            Constant.TABLE_NAME,
            arrayOf(Constant.ID, Constant.NAME, Constant.PRODUCTS, Constant.STEPS),
            "${Constant.ID} = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor?.moveToFirst()

        val food =
            Food(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3))

        return food
    }

    //done
    override fun getAllFoods(): ArrayList<Food> {
        val list = ArrayList<Food>()

        val query = "select * from ${Constant.TABLE_NAME}"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val products = cursor.getString(2)
                val steps = cursor.getString(3)

                val food = Food(id, name, products, steps)
                list.add(food)
            } while (cursor.moveToNext())
        }
        return list
    }


}