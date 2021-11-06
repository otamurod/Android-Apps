package com.otamurod.mynotes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

class DBManager {

    val dbName = "MyNotes"
    val dbTable = "Notes"
    val colID = "ID"
    val colTitle = "Title"
    val colContent = "Content"
    val dbVersion = 1
    //SQL statement
    val sqlCreateTable = "CREATE TABLE IF NOT EXISTS $dbTable ($colID INTEGER PRIMARY KEY, $colTitle TEXT, $colContent TEXT);"
    //instance of SQLite DB
    var sqlDB: SQLiteDatabase? = null

    //constructor
    constructor(context: Context){
        var DB = DatabaseHelperNotes(context)
        sqlDB = DB.writableDatabase

    }

    inner class DatabaseHelperNotes: SQLiteOpenHelper{
        var context: Context? = null
        //constructor
        constructor(context: Context):super(context, dbName, null, dbVersion){
            this.context = context

        }

        override fun onCreate(p0: SQLiteDatabase?) {
            //execute SQL
            p0!!.execSQL(sqlCreateTable)
            Toast.makeText(this.context, "Database Created", Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            p0!!.execSQL("DROP TABLE IF EXISTS $dbTable")
        }

    }

    //function to insert a note
    fun insert(contentValues: ContentValues): Long{
        val ID = sqlDB!!.insert(dbTable, "", contentValues)

        return ID
    }
    //function to select a note
    fun query(projection: Array<String>, selection: String, selectionArgs: Array<String>, sortOrder: String):Cursor{
        val qb = SQLiteQueryBuilder()
        qb.tables = dbTable
        val cursor = qb.query(sqlDB, projection, selection, selectionArgs, null, null, sortOrder)

        return cursor
    }
    //function to delete a note
    fun delete(selection: String, selectionArgs: Array<String>): Int{
        val count = sqlDB!!.delete(dbTable, selection, selectionArgs)
        return count
    }
    //function to update a note
    fun update(values: ContentValues, selection: String, selectionArgs: Array<String>): Int{
        val count = sqlDB!!.update(dbTable, values, selection, selectionArgs)
        return count
    }

}