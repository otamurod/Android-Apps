package com.otamurod.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.otamurod.room.dao.CategoryDao
import com.otamurod.room.dao.NewsDao
import com.otamurod.room.entity.Category
import com.otamurod.room.entity.News

@Database(entities = [News::class, Category::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
    abstract fun categoryDao(): CategoryDao

    companion object {

        private var appDatabase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase =
                    Room.databaseBuilder(context, AppDatabase::class.java, "news.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            }
            return appDatabase!!
        }
    }
}