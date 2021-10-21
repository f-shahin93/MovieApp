package com.shahin.movieapp.app

import android.app.Application
import com.shahin.movieapp.di.ApplicationGraph
import com.shahin.movieapp.di.DaggerApplicationGraph
import io.realm.Realm
import io.realm.RealmConfiguration

class MovieApplication : Application() {

    lateinit var applicationGraph: ApplicationGraph
        private set

    override fun onCreate() {
        super.onCreate()

        applicationGraph = DaggerApplicationGraph.builder()
            .application(this)
            .build()

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