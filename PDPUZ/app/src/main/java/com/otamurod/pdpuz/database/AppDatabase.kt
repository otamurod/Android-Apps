package com.otamurod.pdpuz.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.otamurod.pdpuz.dao.CourseDao
import com.otamurod.pdpuz.dao.GroupDao
import com.otamurod.pdpuz.dao.MentorDao
import com.otamurod.pdpuz.dao.StudentDao
import com.otamurod.pdpuz.entities.Course
import com.otamurod.pdpuz.entities.Group
import com.otamurod.pdpuz.entities.Mentor
import com.otamurod.pdpuz.entities.Student


@Database(entities = [Student::class, Course::class, Group::class, Mentor::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun mentorDao(): MentorDao
    abstract fun courseDao(): CourseDao
    abstract fun groupDao(): GroupDao

    companion object {

        private var appDatabase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase =
                    Room.databaseBuilder(context, AppDatabase::class.java, "pdp_academy.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            }
            return appDatabase!!
        }
    }
}