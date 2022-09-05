package com.otamurod.realmdatabase

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(applicationContext)


        val config = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .schemaVersion(1)
            .name("test.db")
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(config)

    }
}