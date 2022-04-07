package com.otamurod.passportapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.otamurod.passportapp.dao.CitizenDao
import com.otamurod.passportapp.entities.Citizen


@Database(entities = [Citizen::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun citizenDao(): CitizenDao

    companion object {

        private var appDatabase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase =
                    Room.databaseBuilder(context, AppDatabase::class.java, "citizen.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            }
            return appDatabase!!
        }
    }
}