package com.tech.assignment

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


class TechApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //realm initialization
        initRealm()
    }

    fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("realmFile.db")
            .schemaVersion(1)
            .build()
        Realm.setDefaultConfiguration(config)

    }
}