package com.shahin.movieapp.app

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initRealmDB()

    }

    private fun initRealmDB() {
        Realm.init(applicationContext)
        val realmConfig = RealmConfiguration.Builder()
            .name("Movies.db")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}