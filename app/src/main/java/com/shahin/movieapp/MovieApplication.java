package com.shahin.movieapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MovieApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initRealmDB();
    }

    private void initRealmDB() {
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("Movies.db")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
